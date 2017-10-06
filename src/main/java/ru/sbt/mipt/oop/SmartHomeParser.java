package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeParser {
    static SmartHome parse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, SmartHome.class);
    }
}
