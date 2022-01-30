package gg.clouke.staffutility.module.player;

import lombok.Data;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Clouke
 * @since 30.01.2022 06:08
 * All Rights Reserved
 */

@Data
public class StaffPlayer {

    private final Map<Player, StaffPlayer> staffPlayers = new HashMap<>();

    public void addStaffPlayer(Player player) {
        staffPlayers.putIfAbsent(player, this);
    }

    public void removeStaffPlayer(Player player) {
        staffPlayers.remove(player);
    }

    public boolean isStaffPlayer(Player player) {
        return this.staffPlayers.containsKey(player);
    }

    /**
     * @return Returns a list of staff players
     */
    public List<Player> getStaffPlayers() {
        List<Player> staffList = new ArrayList<>();

        for (final Map.Entry<Player, StaffPlayer> staffEntry : staffPlayers.entrySet()) {
            staffList.add(staffEntry.getKey());
        }

        return staffList;
    }
}
