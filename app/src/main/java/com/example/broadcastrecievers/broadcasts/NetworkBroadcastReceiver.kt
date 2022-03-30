package com.example.broadcastrecievers.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import kotlin.math.log


class NetworkBroadcastReceiver : BroadcastReceiver() {

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(
                isConnectedOrConnecting(context!!)
            )

            Log.d("TAG", "onReceive: xxxxxx")
        }
    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capability =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

}

