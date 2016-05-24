package com.attilapalfi.cassiopeiaekontroller.network

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.attilapalfi.commons.CommonUdpPacketReceiver
import com.attilapalfi.commons.UdpPacketReceiver

class DiscoveryReceiverService : IntentService("DiscoveryReceiverService") {

    private val udpPacketReceiver: UdpPacketReceiver = CommonUdpPacketReceiver(
            DiscoveryPacketProcessor(),
            { e -> Log.e(tag, e.message) }
    )

    override fun onHandleIntent(intent: Intent) {
        running = true
        startReceiving()
    }

    private fun startReceiving() {
        while (running) {

        }
    }

    companion object {
        private val tag = "DiscRecServ"

        @Volatile
        private var running = false

        fun stop() {
            running = false
        }
    }
}
