package se.academy.academymatch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.academy.academymatch.domain.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;


@Component
public class Repository {
    @Autowired
    private DataSource dataSource;

    public Queue<Person> createPool()  {
        Queue<Person> pool = new LinkedList<>();
        Connection dbconn = null;
        Statement stmt;
        String query =
                "SELECT  *" +
                        "FROM Academy_Projekt2.dbo.person ORDER BY NEWID()";

        try {
            dbconn = dataSource.getConnection();
            stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Person person = new Person
                        (rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        Calendar.getInstance().get(Calendar.YEAR)-rs.getInt("born"),
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
