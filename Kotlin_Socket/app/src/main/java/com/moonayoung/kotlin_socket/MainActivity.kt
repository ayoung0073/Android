package com.moonayoung.kotlin_socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton.setOnClickListener {
            var data : String = input1.text.toString()

            Thread(Runnable {
                kotlin.run {
                    send(data)
                }
            })
        }
    }

    fun send(data : String){
        var port = 5001

        try {
            var sock : Socket = Socket("localhost", port)

            var outStream : ObjectOutputStream(sock.getInputStream)
            outStream.writeObject(data)
            outStream.flush()

            var inputStream : ObjectInputStream(sock)
        }
    }

}