package com.kazimirov.financemanagement.enums;

public enum MonthName {
    JANUARY(1, "Январь"),
    FEBRUARY(2, "Февраль"),
    MARCH(3, "Март"),
    APRIL(4, "Апрель"),
    MAY(5, "Май"),
    JUNE(6, "Июнь"),
    JULY(7, "Июль"),
    AUGUST(8, "Август"),
    SEPTEMBER(9, "Сентябрь"),
    OCTOBER(10, "Октябрь"),
    NOVEMBER(11, "Ноябрь"),
    DECEMBER(12, "Декабрь");

    private final int monthNumber;
    private final String monthName;

    MonthName(int monthNumber, String monthName) {
        this.monthNumber = monthNumber;
        this.monthName = monthName;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public static String getMonthNameByNumber(int monthNumber) {
        for (MonthName month : values()) {
            if (month.getMonthNumber() == monthNumber) {
                return month.getMonthName();
            }
        }
        throw new IllegalArgumentException("Неверный номер месяца: " + monthNumber);
    }

    public static int getMonthNumberByName(String monthName) {
        for (MonthName month : values()) {
            if (month.getMonthName().equalsIgnoreCase(monthName)) {
                return month.getMonthNumber();
            }
        }
        throw new IllegalArgumentException("Неверное название месяца: " + monthName);
    }
}

