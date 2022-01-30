package gg.clouke.staffutility;

import gg.clouke.staffutility.bridge.Bridge;
import gg.clouke.staffutility.module.player.StaffPlayer;
import gg.clouke.staffutility.storage.mongo.BaseMongo;
import gg.clouke.staffutility.storage.redis.BaseRedis;
import gg.clouke.staffutility.util.command.CommandFramework;
import gg.clouke.staffutility.util.config.ConfigFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class StaffUtility extends JavaPlugin {

    @Getter
    private static StaffUtility instance;
    private CommandFramework commandFramework;
    private ConfigFile config;
    private Bridge bridge;
    private BaseMongo baseMongo;
    private BaseRedis baseRedis;
    private StaffPlayer staffPlayer;

    private String name;

    @Override
    public void onEnable() {
        this.loadFirst();

        baseMongo.setup();
        baseRedis.setup();

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadFirst() {
        instance = this;
        commandFramework = new CommandFramework(this);
        config = new ConfigFile(this, "config.yml");
        this.name = config.getString("settings.server");
        bridge = new Bridge();
        baseMongo = new BaseMongo();
        baseRedis = new BaseRedis();
        staffPlayer = new StaffPlayer();
    }

}
