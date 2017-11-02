package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    changeDoorStatus(event, room, door);
                }
            }
        }
    }

    private void changeDoorStatus(SensorEvent event, Room room, Door door) {
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else if (event.getType() == DOOR_CLOSED) {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
