package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;

public class ScenarioRunnerTest {
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
}
