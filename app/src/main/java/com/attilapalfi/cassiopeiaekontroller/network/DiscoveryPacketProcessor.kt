package com.attilapalfi.cassiopeiaekontroller.network

import com.attilapalfi.commons.UdpPacketProcessor
import java.net.DatagramPacket

/**
 * Created by palfi on 2016-05-24.
 */
class DiscoveryPacketProcessor : UdpPacketProcessor {

    override fun process(packet: DatagramPacket) {
        throw UnsupportedOperationException()
    }
}