package ru.sbt.mipt.oop;

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
                }
            }
        }
        System.out.println("All lights were turned off.");
    }

    private boolean isDoorClosedEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
