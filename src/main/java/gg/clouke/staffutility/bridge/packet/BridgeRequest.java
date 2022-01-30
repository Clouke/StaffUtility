package gg.clouke.staffutility.bridge.packet;

import com.google.gson.Gson;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 30.01.2022 06:44
 * All Rights Reserved
 */

public class BridgeRequest {

    @Getter
    private final Packet packet;
    private final Map<String, String> params;

    public BridgeRequest(Packet packet) {
        this.packet = packet;
        params = new HashMap<>();
    }

    public BridgeRequest setParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public String getParam(String key) {
        if (containsParam(key)) {
            return params.get(key);
        }
        return null;
    }

    public boolean containsParam(String key) {
        return params.containsKey(key);
    }

    public void removeParam(String key) {
        if (containsParam(key)) {
            params.remove(key);
        }
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}
