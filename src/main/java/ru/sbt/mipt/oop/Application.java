package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = SmartHomeParser.parse(json);
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (EventManager.isTurnOnLightEvent(event) || EventManager.isTurnOffLightEvent(event)) {
                // событие от источника света
                for (Room room : smartHome.getRooms()) {
                    for (Light light : room.getLights()) {
                        if (light.getId().equals(event.getObjectId())) {
                            if (EventManager.isTurnOnLightEvent(event)) {
                                SmartHomeManager.turnOnLight(light);
                                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                            } else {
                                SmartHomeManager.turnOffLight(light);
                                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                            }
                        }
                    }
                }
            }
            if (EventManager.isOpenDoorEvent(event) || EventManager.isCloseDoorEvent(event)) {
                // событие от двери
                for (Room room : smartHome.getRooms()) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            if (EventManager.isOpenDoorEvent(event)) {
                                SmartHomeManager.openDoor(door);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                            } else {
                                SmartHomeManager.closeDoor(door);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                                if (room.getName().equals("hall")) {
                                    for (Room homeRoom : smartHome.getRooms()) {
                                        for (Light light : homeRoom.getLights()) {
                                            SmartHomeManager.turnOffLight(light);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            sendCommand(command);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
