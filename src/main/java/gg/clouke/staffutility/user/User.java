package gg.clouke.staffutility.user;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import gg.clouke.staffutility.StaffUtility;
import gg.clouke.staffutility.bridge.packet.BridgeRequest;
import gg.clouke.staffutility.bridge.packet.Packet;
import gg.clouke.staffutility.module.chat.ChatState;
import gg.clouke.staffutility.storage.mongo.BaseMongo;
import lombok.Data;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Clouke
 * @since 30.01.2022 06:16
 * All Rights Reserved
 */

@Data
public class User {

    private final StaffUtility plugin = StaffUtility.getInstance();
    private static Map<UUID, User> users = new HashMap<>();

    private UUID uuid;
    private String name;

    private ChatState chatState = ChatState.NORMAL;
    private boolean staffMessages = true;
    private int reports = 0;

    private boolean initialized;

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.initialize();
        users.put(this.uuid, this);
    }

    public void initialize() {
        final BaseMongo mongo = plugin.getBaseMongo();
        final Document document = mongo.getUser().find(Filters.eq("uuid", this.uuid.toString())).first();

        if (document != null) {

            if (document.containsKey("chatState")) {
                this.chatState = ChatState.valueOf(document.getString("chatState"));
            }

            this.staffMessages = document.getBoolean("staffMessages");
            this.reports = document.getInteger("reports");

        }

        this.initialized = true;
    }


    public void store() {
        final Document document = new Document();

        document.put("name", this.name);
        document.put("uuid", this.uuid);

        if (this.chatState != ChatState.NORMAL) {
            document.put("chatState", this.chatState.name());
        }

        document.put("staffMessages", this.staffMessages);
        document.put("reports", this.reports);

        final BaseMongo mongo = plugin.getBaseMongo();
        mongo.getUser().replaceOne(Filters.eq("uuid", this.uuid.toString()), document, new UpdateOptions().upsert(true));

        final String json = new BridgeRequest(Packet.DATA_SYNC)
                .setParam("UUID", uuid.toString())
                .toJson();

        plugin.getBaseRedis().publish(json);
        users.remove(this.uuid);
    }

    public static User getUser(UUID uuid) {
        return users.get(uuid);
    }

    public static void createUser(UUID uuid, String name) {
        if (users.containsKey(uuid)) {
            getUser(uuid);
            return;
        }
        users.put(uuid, new User(uuid, name));
        getUser(uuid);
    }


}
