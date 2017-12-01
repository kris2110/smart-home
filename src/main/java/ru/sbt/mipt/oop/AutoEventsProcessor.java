package ru.sbt.mipt.oop;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class AutoEventsProcessor implements EventHandler {
    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (!isDoorClosedEvent(event)) {
            return;
        }

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (!(door.getId().equals(event.getObjectId()))) {
                        return;
                    }
                    if (!(event.getType() == DOOR_CLOSED)) {
                        return;
                    }
                    if (!(room.getName().equals("hall"))) {
                        return;
                    }
                    switchOffLightWhenHallDoorClosed(smartHome, room);
                }
            }
        }
    }

    private void switchOffLightWhenHallDoorClosed(SmartHome smartHome, Room room) {
        if (room.getName().equals("hall")) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    Application.sendCommand(command);
                }
            }
        }
        System.out.println("All lights were turned off.");
    }

    private void turnOfHomeLights(SmartHome smartHome) {
        smartHome.executeAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
            }
        });
    }

    private boolean isDoorClosedEvent(SensorEvent event) {
        return event.getType() == DOOR_CLOSED;
    }
}
