package gg.clouke.staffutility.bukkit;

import gg.clouke.staffutility.StaffUtility;
import gg.clouke.staffutility.bridge.packet.BridgeRequest;
import gg.clouke.staffutility.bridge.packet.Packet;
import gg.clouke.staffutility.module.player.StaffPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Clouke
 * @since 30.01.2022 07:26
 * All Rights Reserved
 */

public class StaffListener implements Listener {

    private final StaffUtility plugin = StaffUtility.getInstance();
    private final StaffPlayer staffPlayer = plugin.getStaffPlayer();

    @EventHandler
    public void handleStaffJoin(final PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.hasPermission("staffutility.staff")) {
            staffPlayer.addStaffPlayer(player);

            final String server = plugin.getName();
            String json = new BridgeRequest(Packet.EMPLOYEE_CHAT)
                    .setParam(
                            "MESSAGE",
                            "&b[S] &c" + player.getName() + " &3has joined &b" + server + "&3."
                    ).toJson();

            plugin.getBaseRedis().publish(json);
        }
    }

    @EventHandler
    public void handleStaffQuit(final PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (player.hasPermission("staffutility.staff")) {
            staffPlayer.addStaffPlayer(player);

            final String server = plugin.getName();
            String json = new BridgeRequest(Packet.EMPLOYEE_CHAT)
                    .setParam(
                            "MESSAGE",
                            "&b[S] &c" + player.getName() + " &3has left from &b" + server + "&3."
                    ).toJson();

            plugin.getBaseRedis().publish(json);
        }
    }


}
