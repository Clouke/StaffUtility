package gg.clouke.staffutility.bridge;

import gg.clouke.staffutility.StaffUtility;
import gg.clouke.staffutility.bridge.packet.BridgeRequest;
import gg.clouke.staffutility.bridge.packet.Packet;

/**
 * @author Clouke
 * @since 30.01.2022 06:31
 * All Rights Reserved
 */

public abstract class AbstrBridge {

    public abstract void handle(final Packet packet, final BridgeRequest request);
    protected final StaffUtility plugin = StaffUtility.getInstance();

}
