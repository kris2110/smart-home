package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class LightEventProcessorTest {
    @Test
    public void testHandleLightOn() {
        LightEventProcessor lightEventProcessor = new LightEventProcessor();
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", false);
        smartHome.addRoom(new Room(Arrays.asList(light), Collections.emptyList(), "room"));
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, light.getId());

        assertFalse(light.isOn());
        lightEventProcessor.handle(smartHome, event);
        assertTrue(light.isOn());
    }

    @Test
    public void testHandleLightOff() {
        LightEventProcessor lightEventProcessor = new LightEventProcessor();
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        smartHome.addRoom(new Room(Arrays.asList(light),
                Collections.emptyList(),
                "room"));
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, light.getId());

        assertTrue(light.isOn());
        lightEventProcessor.handle(smartHome, event);
        assertFalse(light.isOn());
    }
}