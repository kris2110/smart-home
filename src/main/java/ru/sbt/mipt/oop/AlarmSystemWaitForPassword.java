package ru.sbt.mipt.oop;


import java.util.Scanner;

public class AlarmSystemWaitForPassword implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;
    private static int wrongAttemptsNumber =0;

    public AlarmSystemWaitForPassword(AlarmSystemState system, int password) {
        alarmSystemState =system;
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.WAIT_FOR_PASSWORD;
    }

    @Override
    public void turnOn() {
        return;
    }

    @Override
    public void enterPassword() {
        Scanner in = new Scanner(System.in);
        int pinCode = in.nextInt();
        boolean rightPassword = (pinCode == password);
        if(!rightPassword){
            if (++wrongAttemptsNumber >= 3){
                wrongAttemptsNumber = 0;
                alarmSystemState.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystemState, password));
            }
        }
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        return;
    }
}
