package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

  private final Connection connection;

  public void cleanUp(){

  }

  public void printConnectInfo() {
    try {
      System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
      System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
      System.out.println("Driver: " + connection.getMetaData().getDriverName());
      System.out.println("Autocommit: " + connection.getAutoCommit());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Connection getMySqlConnection() {
    try {
      DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

      StringBuilder url = new StringBuilder();
      url.append("jdbc:mysql://")
          .append("localhost:")
          .append("3306/")
          .append("db_example?")
          .append("user=tully&")
          .append("password=tully");

      System.out.println("URL: " + url + "\n");

      return DriverManager.getConnection(url.toString());
    } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

}
