package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DoorEventProcessorTest {
    @Test
    public void testHandle() {
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        SmartHome smartHome = new SmartHome();

        Door door = new Door(true, "2");
        Light light = new Light("2", true);
        smartHome.addRoom(new Room(Arrays.asList(light), Arrays.asList(door), "room"));
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, door.getId());

        doorEventProcessor.handle(smartHome, event);
        assertFalse(door.isOpen());
    }


    @Test
    public void testEventWithHallDoorClosed() {
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        SmartHome smartHome = new SmartHome();

        Door hall_door = new Door(true, "1");
        Light hall_light = new Light("1", true);
        Door door = new Door(true, "2");
        Light light = new Light("2", true);
        smartHome.addRoom(new Room(Arrays.asList(hall_light), Arrays.asList(hall_door), "hall"));
        smartHome.addRoom(new Room(Arrays.asList(light), Arrays.asList(door), "room"));
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hall_door.getId());

        assertTrue(hall_light.isOn());
        assertTrue(light.isOn());

        doorEventProcessor.handle(smartHome, event);

        assertFalse(hall_door.isOpen());
        assertTrue(hall_light.isOn());
        assertTrue(light.isOn());
    }
}