package se.academy.academymatch.repository;

import se.academy.academymatch.domain.Person;

import java.sql.*;
import java.util.Queue;

public class Repository {
    private Queue<Person> pool;

    public Queue<Person> createPool() throws SQLException{
        String connstr = "jdbc:sqlserver://185.21.146.24;databasename=Northwind;user=AcademyProject2;password=SdfrewrxEW3sdqqw";
        Connection dbconn = null;
        Statement stmt = null;
        String query =
                "SELECT  *" +
                        "FROM Academy_Projekt2.dbo.person";

        try { dbconn = DriverManager.getConnection(connstr);
            stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Person person = new Person
                        (rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("born"),
                        rs.getString("class"),
                        rs.getString("presentation"),
                        rs.getString("imglink"),
                        rs.getString("status"));
                pool.add(person);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(dbconn!=null)
                dbconn.close();
        }
        return pool;

    }
}
