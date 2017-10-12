package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventManager {
    public static boolean isTurnOnLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON;
    }

    public static boolean isTurnOffLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_OFF;
    }

    public static boolean isOpenDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN;
    }

    public static boolean isCloseDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_CLOSED;
    }
}
