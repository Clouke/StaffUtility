package gg.clouke.staffutility.util.command;

import gg.clouke.staffutility.StaffUtility;

/**
 * @author Clouke
 * @since 30.01.2022 06:03
 * All Rights Reserved
 */

public abstract class AbstrCommand {

    public final StaffUtility plugin = StaffUtility.getInstance();

    public AbstrCommand() {
        this.plugin.getCommandFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs cmd);

}
