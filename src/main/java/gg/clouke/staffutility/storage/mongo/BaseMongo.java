package gg.clouke.staffutility.storage.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gg.clouke.staffutility.StaffUtility;
import gg.clouke.staffutility.util.config.ConfigFile;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.Collections;

/**
 * @author Clouke
 * @since 30.01.2022 06:20
 * All Rights Reserved
 */

@Getter
public class BaseMongo {

    private final StaffUtility plugin = StaffUtility.getInstance();

    private final String authShort = "mongodb.authentication.";
    private final String mongoShort = "mongodb.";

    private ConfigFile config;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> user;

    public void setup() {
        config = plugin.getConfig();

        try {
            if (this.config.getBoolean(authShort + "enabled")) {
                final MongoCredential credential = MongoCredential.createCredential(
                        this.config.getString(authShort + "username"),
                        this.config.getString(authShort + "database"),
                        this.config.getString(authShort + "password").toCharArray()
                );

                mongoClient = new MongoClient(new ServerAddress(this.config.getString(mongoShort + "address"),
                        this.config.getInt(mongoShort + "port")), Collections.singletonList(credential));
            }
            else {
                mongoClient = new MongoClient(this.config.getString(mongoShort + "address"),
                        this.config.getInt(mongoShort + "port"));
            }
            mongoDatabase = mongoClient.getDatabase(this.config.getString(mongoShort + "database"));
            user = mongoDatabase.getCollection("User");
        }
        catch (final Exception e) {
            e.printStackTrace();
            plugin.getLogger().warning("Failed to connect to Mongo.");
            Bukkit.shutdown();
        }
    }
}
