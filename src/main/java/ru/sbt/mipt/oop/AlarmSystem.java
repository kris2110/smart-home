package ru.sbt.mipt.oop;

public class AlarmSystem implements AlarmSystemState {
    private final String password;
    private AlarmSystemState alarmSystem;

    public AlarmSystem(String password){
        this.password = password;
        alarmSystem = new AlarmSystemStateOff(this);
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return alarmSystem.getState();
    }

    @Override
    public void turnOn() {
        alarmSystem.turnOn();
    }

    @Override
    public void turnOff() {
        alarmSystem.turnOff();
    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {
        alarmSystem.onSensorEvent(sensorEvent);
    }

    @Override
    public void enterPassword(String password) {
        alarmSystem.enterPassword(password);
    }

    public void setAlarmSystemState(AlarmSystemState newSystemState){
        this.alarmSystem = newSystemState;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
}
