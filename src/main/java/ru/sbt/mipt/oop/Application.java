package ru.sbt.mipt.oop;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHomeFileReader.read();
        // начинаем цикл обработки событий
        SensorEventObserver sensorEventObserver = new SensorEventObserver(smartHome);
        configureHandlers(sensorEventObserver);
        sensorEventObserver.runEventCycle();
    }

    private static void configureHandlers(SensorEventObserver sensorEventObserver) {
        sensorEventObserver.addHandler(new LightEventProcessor());
        sensorEventObserver.addHandler(new DoorEventProcessor());
        sensorEventObserver.addHandler(new AutoEventsProcessor());
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    @Nullable
    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
