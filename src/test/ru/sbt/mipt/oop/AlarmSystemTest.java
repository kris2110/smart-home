package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlarmSystemTest {
    SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

    @Test
    public void testFromOffState(){
        AlarmSystem alarmSystem = new AlarmSystem("1234");
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.enterPassword("1234");
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.enterPassword("4444");
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.onSensorEvent(event);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void testFromOnState(){
        AlarmSystem alarmSystem = new AlarmSystem("1234");
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.enterPassword("1234");
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.enterPassword("4444");
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.onSensorEvent(event);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem = new AlarmSystem("1234");
        alarmSystem.turnOn();

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testFromWaitForPasswordState() {
        AlarmSystem alarmSystem = new AlarmSystem("1234");
        alarmSystem.turnOn();
        alarmSystem.onSensorEvent(event);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.onSensorEvent(event);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.enterPassword("1234");
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem = new AlarmSystem("1234");
        alarmSystem.turnOn();
        alarmSystem.onSensorEvent(event);

        alarmSystem.enterPassword("4444");
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());
    }

    @Test
    public void testFromAlarmState() {
        AlarmSystem alarmSystem = new AlarmSystem("1234");
        alarmSystem.turnOn();
        alarmSystem.onSensorEvent(event);
        alarmSystem.enterPassword("4444");
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.onSensorEvent(event);
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.enterPassword("4444");
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.enterPassword("1234");
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }
}
