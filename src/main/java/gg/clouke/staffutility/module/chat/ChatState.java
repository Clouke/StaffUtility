package gg.clouke.staffutility.module.chat;

import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author Clouke
 * @since 30.01.2022 06:15
 * All Rights Reserved
 */

@Getter
public enum ChatState {

    NORMAL("Normal", "", ChatColor.GREEN, ""),
    STAFF("Staff", "[S]", ChatColor.DARK_AQUA, "chat.staff"),
    ADMIN("Admin", "[A]", ChatColor.RED, "chat.admin"),
    MANAGER("Manager", "[M]", ChatColor.DARK_RED, "chat.manager");

    private final String name;
    private final String prefix;
    private final ChatColor color;
    private final String permission;

    ChatState(String name, String prefix, ChatColor color, String permission) {
        this.name = name;
        this.prefix = prefix;
        this.color = color;
        this.permission = permission;
    }

}
