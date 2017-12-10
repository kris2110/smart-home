package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AutoEventsProcessorTest {
    @Test
    public void testHallDoorClose() {
        SmartHome smartHome = new SmartHome();
        Door door = new Door(true, "1");
        Light light = new Light("1", true);
        Door hall_door = new Door(true, "2");
        Light hall_light = new Light("2", true);
        smartHome.addRoom(new Room(Arrays.asList(light), Arrays.asList(door), "room"));
        smartHome.addRoom(new Room(Arrays.asList(hall_light), Arrays.asList(hall_door), "hall"));

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hall_door.getId());
        AutoEventsProcessor autoEventsProcessor = new AutoEventsProcessor();
        autoEventsProcessor.handle(smartHome, event);

        for (Room room: smartHome.getRooms()) {
            for (Light l: room.getLights())
                assertFalse(l.isOn());
            for (Door d: room.getDoors())
                assertTrue(d.isOpen());
        }
    }

    @Test
    public void testHallDoorOpen() {
        SmartHome smartHome = new SmartHome();
        Door door = new Door(false, "1");
        Light light = new Light("1", false);
        Door hall_door = new Door(false, "2");
        Light hall_light = new Light("2", false);
        smartHome.addRoom(new Room(Arrays.asList(light), Arrays.asList(door), "room"));
        smartHome.addRoom(new Room(Arrays.asList(hall_light), Arrays.asList(hall_door), "hall"));

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, hall_door.getId());
        AutoEventsProcessor autoEventsProcessor = new AutoEventsProcessor();
        autoEventsProcessor.handle(smartHome, event);

        for (Room room: smartHome.getRooms()) {
            for (Light l: room.getLights())
                assertFalse(l.isOn());
            for (Door d: room.getDoors())
                assertFalse(d.isOpen());
        }
    }
}
