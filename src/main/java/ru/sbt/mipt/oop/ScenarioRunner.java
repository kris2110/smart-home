package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeManager;

public class ScenarioRunner implements EventProcessor {
    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorClosedEvent(event)) {
            return;
        }

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        for (Room homeRoom : smartHome.getRooms()) {
                            for (Light light : homeRoom.getLights()) {
                                SmartHomeManager.turnOffLight(light);
                            }
                        }
                    }
                    System.out.println("All lights were turned off.");
                }
            }
        }
    }

    private boolean isDoorClosedEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
