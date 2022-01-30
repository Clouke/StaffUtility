package gg.clouke.staffutility.bukkit;

import gg.clouke.staffutility.user.User;
import gg.clouke.staffutility.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * @author Clouke
 * @since 30.01.2022 07:22
 * All Rights Reserved
 */


public class UserListener implements Listener {

    @EventHandler
    public void handleAsyncPreConnection(final AsyncPlayerPreLoginEvent e) {
        UUID uuid = e.getUniqueId();
        User user = User.getUser(uuid);

        if (user == null) {
            User.createUser(uuid, e.getName());
        }
    }

    @EventHandler
    public void handleConnection(final PlayerLoginEvent e) {
        Player player = e.getPlayer();
        User user = User.getUser(player.getUniqueId());
        if (user == null || !user.isInitialized()) {
            e.setKickMessage(CC.translate("&cFailed to initialize your data."));
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
        }
    }

    @EventHandler
    public void handleQuit(final PlayerQuitEvent e) {
        User user = User.getUser(e.getPlayer().getUniqueId());
        user.store();
    }

}
