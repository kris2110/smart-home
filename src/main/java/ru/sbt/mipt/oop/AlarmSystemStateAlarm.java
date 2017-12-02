package ru.sbt.mipt.oop;

import com.sun.org.apache.regexp.internal.RE;

public class AlarmSystemStateAlarm implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemStateAlarm(AlarmSystem system) {
        alarmSystem = system;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALARM;
    }

    @Override
    public void turnOn() {
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {
        return;
    }

    @Override
    public void enterPassword(String password) {
        if (alarmSystem.isPasswordCorrect(password)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOn(alarmSystem));
        }
    }
}
