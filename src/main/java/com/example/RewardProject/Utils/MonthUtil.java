package com.example.RewardProject.Utils;


import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class MonthUtil {

    private static final Map<Integer, String> monthMap = new HashMap<>();


    static {
        for (Month month : Month.values()) {
            monthMap.put(month.getValue(), month.name());
        }
    }

    public static String getMonthName(int monthNumber) {
        return monthMap.getOrDefault(monthNumber, "Unknown");
    }
}
