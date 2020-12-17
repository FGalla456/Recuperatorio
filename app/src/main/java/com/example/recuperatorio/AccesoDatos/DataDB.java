package com.example.recuperatorio.AccesoDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataDB {
    public static String host="db4free.net";
    public static String port="3306";
    public static String nameBD="sql10368678";
    public static String user="sql10368678";
    public static String pass="abHgxGTpKc";


    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";


    public Statement AccesoDatos()
    {
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            st = con.createStatement();
        }
        catch (Exception e)
        {

        }
        return st;
    }
}
