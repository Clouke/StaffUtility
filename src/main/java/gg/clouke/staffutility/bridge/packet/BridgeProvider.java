package gg.clouke.staffutility.bridge.packet;

import com.google.gson.Gson;
import gg.clouke.staffutility.StaffUtility;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.JedisPubSub;

/**
 * @author Clouke
 * @since 30.01.2022 06:37
 * All Rights Reserved
 */

public class BridgeProvider extends JedisPubSub {

    private final StaffUtility plugin = StaffUtility.getInstance();

    /**
     * <p>
     * Handles Bridge packet on {@link JedisPubSub} message
     *
     * @param channel Jedis Channel
     * @param message Obeying Bridge Packet
     */
    @Override
    public void onMessage(String channel, String message) {
        try {
            new BukkitRunnable() {
                @Override
                public void run() {
                    final BridgeRequest bridgeRequest = new Gson().fromJson(message, BridgeRequest.class);
                    plugin.getBridge().getPackets().forEach(packet -> packet.handle(bridgeRequest.getPacket(), bridgeRequest));
                }
            }.runTask(plugin);
        } catch (final Exception e) {
            plugin.getLogger().info("Error occurred whilst sending Bridge packet: " + e.getMessage());
        }
    }

}
