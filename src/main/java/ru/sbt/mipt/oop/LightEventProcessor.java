package ru.sbt.mipt.oop;

public class LightEventProcessor {
    static void process(SmartHome smartHome, SensorEvent event) {
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
}
