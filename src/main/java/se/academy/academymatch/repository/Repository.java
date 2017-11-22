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


    public Queue<Person> createPool(String preference)  {
        Queue<Person> pool = new LinkedList<>();
        Connection dbconn = null;
        PreparedStatement sth;
        for (int level =1; level<=3; level++) {
            String query =
                    "SELECT  *" +
                            "FROM Academy_Projekt2.dbo.person " +
                            "WHERE Preference" + level + " = ? " +
                            "ORDER BY NEWID()";

            try {
                dbconn = dataSource.getConnection();
                sth = dbconn.prepareStatement(query);
                sth.setString(1, preference);
                ResultSet rs = sth.executeQuery();
                while (rs.next()) {
                    Person person = new Person
                            (rs.getInt("id"),
                                    rs.getString("firstname"),
                                    rs.getString("lastname"),
                                    Calendar.getInstance().get(Calendar.YEAR) - rs.getInt("born"),
                                    rs.getString("class"),
                                    rs.getString("presentation"),
                                    rs.getString("imglink"),
                                    rs.getString("status"),
                                    rs.getString("preference1"),
                                    rs.getString("preference2"),
                                    rs.getString("preference3"));
                    pool.add(person);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (dbconn != null)
                    try {
                        dbconn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }

        String query4 =
                "SELECT  *" +
                        "FROM Academy_Projekt2.dbo.person " +
                        "WHERE Preference1 != ? " +
                        "AND Preference2 != ? " +
                        "AND Preference3 != ? " +
                        "ORDER BY NEWID()";

        try {
            dbconn = dataSource.getConnection();
            sth = dbconn.prepareStatement(query4);
            sth.setString(1, preference);
            sth.setString(2, preference);
            sth.setString(3, preference);
            ResultSet rs = sth.executeQuery();
            while (rs.next()) {
                Person person = new Person
                        (rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                Calendar.getInstance().get(Calendar.YEAR)-rs.getInt("born"),
                                rs.getString("class"),
                                rs.getString("presentation"),
                                rs.getString("imglink"),
                                rs.getString("status"),
                                rs.getString("preference1"),
                                rs.getString("preference2"),
                                rs.getString("preference3"));
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
