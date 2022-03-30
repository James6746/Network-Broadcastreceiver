package com.example.broadcastrecievers

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastrecievers.broadcasts.NetworkBroadcastReceiver


class MainActivity : AppCompatActivity() {

    lateinit var receiver: NetworkBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        receiver = NetworkBroadcastReceiver()

        NetworkBroadcastReceiver.connectivityReceiverListener =
            object : NetworkBroadcastReceiver.ConnectivityReceiverListener {
                override fun onNetworkConnectionChanged(isConnected: Boolean) {
                    Toast.makeText(this@MainActivity, isConnected.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

            }

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
