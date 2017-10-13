package ru.sbt.mipt.oop;

public class DoorEventProcessor {
    static void process(SmartHome smartHome, SensorEvent event) {
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
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
