package gg.clouke.staffutility.bridge.packet.impl;

import gg.clouke.staffutility.bridge.AbstrBridge;
import gg.clouke.staffutility.bridge.packet.BridgeRequest;
import gg.clouke.staffutility.bridge.packet.Packet;
import gg.clouke.staffutility.util.CC;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 30.01.2022 06:47
 * All Rights Reserved
 */

public class EmployeeChat extends AbstrBridge {

    @Override
    public void handle(final Packet packet, final BridgeRequest request) {
        if (packet.equals(Packet.EMPLOYEE_CHAT)) {

            String message = request.getParam("MESSAGE");
            if (message == null) return;

            for (final Player player : plugin.getStaffPlayer().getStaffPlayers()) {
                player.sendMessage(CC.translate(message));
            }

        }

    }
}
