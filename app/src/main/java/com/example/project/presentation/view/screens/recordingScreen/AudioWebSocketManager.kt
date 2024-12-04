package com.example.project.presentation.view.screens.recordingScreen

import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioManager
import android.util.Base64
import okhttp3.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class AudioStreamManager(private val context: Context) {
    private val sampleRate = 8000 // Lower sample rate for efficient streaming
    private val bufferSize = AudioRecord.getMinBufferSize(
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )

    private var audioRecorder1: AudioRecord? = null
    private var audioPlayer1: AudioTrack? = null

    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    fun startStreaming() {
        if (!hasRecordAudioPermission()) {
            throw SecurityException("RECORD_AUDIO permission not granted")
        }

        // Use earphone mic for input and earphone speakers for output
        audioRecorder1 = createAudioRecorder()
        audioPlayer1 = createAudioPlayer()

        openWebSocket(audioRecorder1!!, audioPlayer1!!, "wss://abdallamohammed--nabbra-ai-api.modal.run/stream/audio/amplify?token=dd")
    }

    private fun openWebSocket(
        recorder: AudioRecord,
        player: AudioTrack,
        url: String
    ) {
        val request = Request.Builder().url(url).build()
        val websocketListener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                recorder.startRecording()
                player.play()
                val buffer = ByteArray(bufferSize)

                println("Buffer Size: ${buffer.size}")

                Thread {
                    try {
                        while (true) {
                            val read = recorder.read(buffer, 0, buffer.size)
                            if (read > 0) {
                                val ear = "left" // Specify which ear (left/right) is being used
                                val audioBase64 = Base64.encodeToString(buffer, 0, read, Base64.NO_WRAP)

                                webSocket.send(createJsonMessage(ear, audioBase64))
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }.start()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val jsonResponse = JSONObject(text)

                    val amplifiedAudioBase64 = jsonResponse.getString("audio")
                    val amplifiedAudio = Base64.decode(amplifiedAudioBase64, Base64.NO_WRAP)

                    player.write(amplifiedAudio, 0, amplifiedAudio.size)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                t.printStackTrace()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                recorder.stop()
                player.stop()
            }
        }

        client.newWebSocket(request, websocketListener)
    }

    private fun createJsonMessage(ear: String, audioBase64: String): String {
        val jsonObject = JSONObject()
        jsonObject.put("ear", ear)
        jsonObject.put("audio", audioBase64)
        return jsonObject.toString()
    }

    @SuppressLint("MissingPermission")
    private fun createAudioRecorder(): AudioRecord {
        if (!hasRecordAudioPermission()) {
            throw SecurityException("RECORD_AUDIO permission not granted")
        }

        // Use the VOICE_COMMUNICATION audio source to prioritize the earphone mic
        return AudioRecord(
            MediaRecorder.AudioSource.VOICE_COMMUNICATION, // Preferred for earphone mic
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )
    }

    private fun createAudioPlayer(): AudioTrack {
        return AudioTrack(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA) // Media output
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH) // Speech output
                .build(),
            AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .build(),
            bufferSize,
            AudioTrack.MODE_STREAM,
            AudioManager.AUDIO_SESSION_ID_GENERATE
        ).apply {
            // Route output to earphone speakers
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.isSpeakerphoneOn = false
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
        }
    }

    private fun hasRecordAudioPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }
}