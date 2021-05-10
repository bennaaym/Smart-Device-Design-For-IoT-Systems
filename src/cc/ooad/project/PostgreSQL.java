package cc.ooad.project;

import cc.ooad.project.helper.Tools;
import java.sql.*;

public class PostgreSQL implements IDBMS
{
    @Override
    public Connection connect() {
        final int PORT = 5432;
        final String DB_NAME = "SMART_DEVICE_IOT";
        final String jdbcURL = "jdbc:postgresql://localhost:" + PORT + "/" + DB_NAME;
        final String DB_USER = "postgres";
        final String DB_PASSWORD = "root";
        try
        {
            return DriverManager.getConnection(jdbcURL, DB_USER, DB_PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean login(String username, String password) {

        String query = "SELECT *  FROM \"users\" WHERE \"username\"='" + username + "' and \"password\"='" + password + "'";
        Statement statement;
        ResultSet resultSet;

        Connection connection = this.connect();
        if(connection != null)
        {
            System.out.println("successfully  connected to the database");
            Tools.delay(500);
            System.out.println("checking user's credentials ...");
            Tools.delay(500);
            //fetch the user
            try
            {
                boolean isLogged = false;
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                connection.close();

                if (!resultSet.next())
                {
                    System.out.println("incorrect credentials, please try again ! ");
                }
                else
                {
                    isLogged = true;
                    System.out.println("user {"+username+","+password+"} "+"  has been successfully  authenticated ");
                }

                statement.close();
                resultSet.close();
                return isLogged;

            }
            catch (SQLException e) {
                return false;
            }
        }
        else
        {
            System.out.println("cannot connect to the database, try again");
        }

        return false;
    }
}
