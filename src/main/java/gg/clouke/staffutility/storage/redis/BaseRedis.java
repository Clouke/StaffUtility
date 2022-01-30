package gg.clouke.staffutility.storage.redis;

import gg.clouke.staffutility.StaffUtility;
import gg.clouke.staffutility.bridge.packet.BridgeProvider;
import gg.clouke.staffutility.util.config.ConfigFile;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Clouke
 * @since 30.01.2022 06:21
 * All Rights Reserved
 */

@Getter
public class BaseRedis {

    private final StaffUtility plugin = StaffUtility.getInstance();
    private final ConfigFile config = plugin.getConfig();

    private JedisPool pool;
    private BridgeProvider provider;
    private boolean active;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final String host = config.getString("redis.host");
    private final int port = config.getInt("redis.port");
    private final transient String password = config.getString("redis.password");

    public void setup() {
        this.pool = new JedisPool(host, port);

        final Jedis jedis = this.pool.getResource();
        jedis.auth(password);

        this.provider = new BridgeProvider();
        executor.submit(() -> jedis.subscribe(provider, "Bridge"));
        jedis.connect();

        active = true;

        plugin.getLogger().info("Connected to redis.");
    }

    /**
     * @param json Parse string to json, writes to jedis
     */
    public void publish(String json){
        final Jedis jedis = this.pool.getResource();
        try {
            jedis.auth(password);
            jedis.publish("Bridge", json);
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

}
