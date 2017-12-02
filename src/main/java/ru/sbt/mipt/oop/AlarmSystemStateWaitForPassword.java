package ru.sbt.mipt.oop;

public class AlarmSystemStateWaitForPassword implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemStateWaitForPassword(AlarmSystem system) {
        alarmSystem = system;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.WAIT_FOR_PASSWORD;
    }

    @Override
    public void turnOn() {
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {
    }

    @Override
    public void enterPassword(String password) {
        if (alarmSystem.isPasswordCorrect(password)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOn(alarmSystem));
        } else {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystem));
        }
    }
}
