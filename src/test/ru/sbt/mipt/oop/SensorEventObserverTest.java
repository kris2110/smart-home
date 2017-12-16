package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SensorEventObserverTest {
    @Test
    public void test() {
        SmartHome smartHome = mock(SmartHome.class);
        LightEventProcessor lightEventProcessor = mock(LightEventProcessor.class);
        DoorEventProcessor doorEventProcessor = mock(DoorEventProcessor.class);
        AutoEventsProcessor autoEventsProcessor = mock(AutoEventsProcessor.class);

        SensorEventObserver sensorEventObserver = new SensorEventObserver(smartHome);
        sensorEventObserver.addHandler(lightEventProcessor);
        sensorEventObserver.addHandler(doorEventProcessor);
        sensorEventObserver.addHandler(autoEventsProcessor);

        SensorEvent sensorEvent = mock(SensorEvent.class);
        sensorEventObserver.onEvent(sensorEvent);

        verify(lightEventProcessor).handle(smartHome, sensorEvent);
        verify(doorEventProcessor).handle(smartHome, sensorEvent);
        verify(autoEventsProcessor).handle(smartHome, sensorEvent);
    }
}