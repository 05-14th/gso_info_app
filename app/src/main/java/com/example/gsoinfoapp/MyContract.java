package com.example.gsoinfoapp;

public final class MyContract {
    private MyContract(){}
    public static class Entry{
        public static final String TableName = "themes";
        public static final String columnId = "theme_id";
        public static final String columnBackground = "main_background";
        public static final String columnSubBg = "sub_background";
        public static final String buttonColor = "button_color";

        public static final String CREATE_TABLE = "CREATE TABLE " + TableName + "(" +
                columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                columnBackground + " TEXT," +
                columnSubBg + " TEXT," +
                buttonColor + " TEXT);";

        public static final String DELETE_TABLE = "DELETE FROM " + TableName;
    }
}
