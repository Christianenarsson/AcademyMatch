package se.academy.academymatch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import se.academy.academymatch.domain.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Queue;

public class Repository {
    private Queue<Person> pool;

    @Autowired
    private DataSource dataSource;

    public Queue<Person> createPool()  {
        Connection dbconn = null;
        Statement stmt;
        String query =
                "SELECT  *" +
                        "FROM Academy_Projekt2.dbo.person";

        try {dbconn = dataSource.getConnection();
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
                try {
                    dbconn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return pool;

    }
}
