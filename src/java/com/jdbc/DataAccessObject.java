package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataAccessObject {
	private static Connection connection = null;

	public static Connection getConnection() {
		try  {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection
                            ("jdbc:mysql://localhost:3306/ESOLSA",
                            "root","Abraham24+1");
                    return con;
                }
                catch(Exception ex) {
                    System.out.println("Database.getConnection() Error -->" + ex.getMessage());
                    return null;
                }
            }
 
            public static void close(Connection con) {
                try  {
                    con.close();
                }
                    catch(Exception ex) {
                }
            }
}