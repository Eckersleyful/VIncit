package main.webapp.java.sql.connector;


import org.apache.catalina.servlet4preview.ServletContext;
import org.apache.tomcat.jni.File;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnector {

    public Connection getConnection() {

        //JDBC variant we are using
        String sqlVariant = "jdbc:sqlite:";
        //Current working dir
        String cwd = Paths.get(".").toAbsolutePath().normalize().toString();

        //Append the db name
        String dbPath = cwd + "\\iot_db.sqlite";
        //Initialize the connection variable
        Connection connection = null;

        try{
            //We get the driver through web.xml context manager
            Context context = new InitialContext();
            //This location was determined in the web.xml
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/sqlite");

            //Get the new connection
            connection = ds.getConnection();
            System.out.println("Connection to SQLite DB established.");
        }
        catch(SQLException e){
            System.out.println("Connection to SQLite db failed.");
            System.out.println(e.getMessage() + e.getStackTrace());
        }
        catch (NamingException e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
