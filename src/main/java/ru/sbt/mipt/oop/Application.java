package ru.sbt.mipt.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHomeFileReader.read();
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        Collection<EventProcessor> processors = new ArrayList<>();
        processors.add(new DoorEventProcessor());
        processors.add(new LightEventProcessor());
        processors.add(new ScenarioRunner());
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor processor : processors) {
                processor.processEvent(smartHome, event);
            }
            event = getNextSensorEvent();
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
