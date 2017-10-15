package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScenarioRunnerTest {
    //Close a hall door (one room, one door, one light).
    //Initial state: all lights are on
    //Desired ultimate state: all lights are off
    @Test
    public void testProcessLightEvent() {
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        String hallDoorId = "1";
        Room hall = new Room(
                Collections.singletonList(light),
                Collections.singletonList(new Door(true, hallDoorId)),
                "hall");
        smartHome.addRoom(hall);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);

        ScenarioRunner scenarioRunner = new ScenarioRunner();
        scenarioRunner.processEvent(smartHome, event);

        assertFalse(light.isOn());
    }

    //Close a hall door.
    //Initial state: all lights are on
    //Desired ultimate state: all lights are off
    @Test
    public void testTurnOffAllLightsWhenHallDoorIsClosed() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }
        String hallDoorId = "999999";
        rooms.add(new Room(lights, Collections.singletonList(new Door(true, hallDoorId)), "hall"));

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);

        ScenarioRunner scenarioRunner = new ScenarioRunner();
        scenarioRunner.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    //Close a door, which isn't a hall door.
    //Initial state: all lights are on
    //Desired ultimate state: all lights are on
    @Test
    public void testCloseNotHallDoor() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }
        String hallDoorId = "999999";
        rooms.add(new Room(lights, Collections.singletonList(new Door(true, hallDoorId)), "hall"));

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "0");

        ScenarioRunner scenarioRunner = new ScenarioRunner();
        scenarioRunner.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        event = new SensorEvent(SensorEventType.DOOR_CLOSED, "Room #1");
        scenarioRunner.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        event = new SensorEvent(SensorEventType.DOOR_CLOSED, "Room #4");
        scenarioRunner.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }
    }

    //Turn off a light.
    //Initial state: all lights are on
    //Desired ultimate state: all lights are on
    @Test
    public void testReceiveNotCloseDoorSignal() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }
        String hallDoorId = "999999";
        rooms.add(new Room(lights, Collections.singletonList(new Door(true, hallDoorId)), "hall"));

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");

        ScenarioRunner scenarioRunner = new ScenarioRunner();
        scenarioRunner.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }
    }

    //Open a hall door.
    //Initial state: all lights are on
    //Desired ultimate state: all lights are on
    @Test
    public void testOpenHallDoor() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }
        String hallDoorId = "999999";
        rooms.add(new Room(lights, Collections.singletonList(new Door(true, hallDoorId)), "hall"));

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }


        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, hallDoorId);

        ScenarioRunner scenarioRunner = new ScenarioRunner();
        scenarioRunner.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }
    }
}
