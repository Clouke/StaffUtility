package gg.clouke.staffutility.bridge.packet.impl;

import gg.clouke.staffutility.bridge.AbstrBridge;
import gg.clouke.staffutility.bridge.packet.BridgeRequest;
import gg.clouke.staffutility.bridge.packet.Packet;
import gg.clouke.staffutility.user.User;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Clouke
 * @since 30.01.2022 06:55
 * All Rights Reserved
 */

public class SynchronizedData extends AbstrBridge {

    @Override
    public void handle(final Packet packet, final BridgeRequest request) {
        if (packet.equals(Packet.DATA_SYNC)) {
            final UUID uuid = UUID.fromString(request.getParam("UUID"));
            final Player player = plugin.getServer().getPlayer(uuid);

            if (player == null) return;

            final User user = User.getUser(uuid);
            user.initialize();
        }
    }
}
