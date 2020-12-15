package com.example.recuperatorio.AccesoDatos;

public class DataDB {
    public static String host="sql10.freesqldatabase.com";
    public static String port="";
    public static String nameBD="";
    public static String user="";
    public static String pass="";


    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";
}
