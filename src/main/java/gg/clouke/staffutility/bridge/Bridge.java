package gg.clouke.staffutility.bridge;

import gg.clouke.staffutility.bridge.packet.impl.EmployeeChat;
import gg.clouke.staffutility.bridge.packet.impl.Report;
import gg.clouke.staffutility.bridge.packet.impl.SynchronizedData;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Clouke
 * @since 30.01.2022 06:31
 * All Rights Reserved
 */

public class Bridge {

    @Getter
    private final List<AbstrBridge> packets;

    public Bridge() {
        this.setup();
        this.packets = loadPackets();
    }

    public static final Class<?>[] PACKETS = new Class[] {
            EmployeeChat.class, Report.class, SynchronizedData.class
    };

    private static final List<Constructor<?>> CONSTRUCTORS = new ArrayList<>();

    /**
     * <p>
     * Initialize packet classes
     *
     * @return Returns a list of classes
     */
    public final List<AbstrBridge> loadPackets() {
        final List<AbstrBridge> checkList = new ArrayList<>();
        for (Constructor<?> constructor : CONSTRUCTORS) {
            try {
                checkList.add((AbstrBridge) constructor.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return checkList;
    }

    public void setup() {
        for (final Class<?> clazz : PACKETS) {
            try {
                CONSTRUCTORS.add(clazz.getConstructor());
            } catch (NoSuchMethodException exception) {
                exception.printStackTrace();
            }
        }
    }

}
