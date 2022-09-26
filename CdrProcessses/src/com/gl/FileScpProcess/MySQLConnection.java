/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.FileScpProcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 


/**
 *
 * @author maverick
 */
public class MySQLConnection {
    
     Logger logger =LogManager.getLogger(MySQLConnection.class);

     public static PropertyReader propertyReader;

     public Connection getConnection() {
    	 System.out.println("in connection method.");
          if (Objects.isNull(propertyReader)) {
               propertyReader = new PropertyReader();
          }
          Connection conn = null;
          try {
               final String JDBC_DRIVER = propertyReader.getPropValue("jdbc_driver").trim();
               final String DB_URL =propertyReader.getPropValue("db_url").trim();
               final String USER = propertyReader.getPropValue("db_username").trim();
               final String PASS = propertyReader.getPropValue("db_password").trim();
               
               System.out.println("DB_URL==("+DB_URL+") USER ("+USER+") PASS("+PASS);
               
               logger.debug(JDBC_DRIVER + " :: " + DB_URL + " :: " + USER + " :: " + PASS);
               logger.debug("Connnection  Init " + java.time.LocalDateTime.now());
               Class.forName(JDBC_DRIVER);
               conn = DriverManager.getConnection(DB_URL, USER, PASS);
               conn.setAutoCommit(true);
               System.out.println("Connnection created successfully");
               logger.info("Connnection created successfully " + conn + " .. " + java.time.LocalDateTime.now());
               return conn;
          } catch (Exception e) {
               logger.error(" Error : : " + e + " :  " + java.time.LocalDateTime.now());
               try {
                    conn.close();
               } catch (SQLException ex) {
                    logger.error(" SQLException : " + ex + " :  " + java.time.LocalDateTime.now());
               }
               System.exit(0);
               return null;
          }
     }
}
