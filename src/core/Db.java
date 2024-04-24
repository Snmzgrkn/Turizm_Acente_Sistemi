package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {
    //Singleton Design Pattern
    private  static Db instance = null;
    private Connection connection;
    private final String DB_URL="jdbc:postgresql://localhost:5432/turizmacente";
    private final String DB_USERNAME="postgres";
    private final String DB_PASS = "1";

    public Db(){
        try{
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try {
            if(instance ==null || instance.getConnection().isClosed()){
                instance = new Db();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return instance.getConnection();
    }
}
