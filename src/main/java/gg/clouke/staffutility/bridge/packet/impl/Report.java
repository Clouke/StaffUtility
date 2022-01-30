package gg.clouke.staffutility.bridge.packet.impl;

import gg.clouke.staffutility.bridge.AbstrBridge;
import gg.clouke.staffutility.bridge.packet.BridgeRequest;
import gg.clouke.staffutility.bridge.packet.Packet;

/**
 * @author Clouke
 * @since 30.01.2022 06:48
 * All Rights Reserved
 */

public class Report extends AbstrBridge {

    @Override
    public void handle(final Packet packet, final BridgeRequest request) {
        if (packet.equals(Packet.REPORT)) {

        }
    }
}
