package ru.sbt.mipt.oop;

import org.jetbrains.annotations.Nullable;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        SensorEventObserver sensorEventObserver = (SensorEventObserver) ctx.getBean("sensorEventObserver");
        sensorEventObserver.runEventCycle();
    }

    private static void configureHandlers(SensorEventObserver sensorEventObserver) {
        List<EventHandler> handlers = new ArrayList<>();
        sensorEventObserver.setHandlers(handlers);
        sensorEventObserver.addHandler(new LightEventProcessor());
        sensorEventObserver.addHandler(new DoorEventProcessor());
        sensorEventObserver.addHandler(new AutoEventsProcessor());
    }

    static void sendCommand(SensorCommand command) {
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
