package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

  private final Connection connection;

  public DBService() {
    this.connection = getMySqlConnection();
  }

  public UsersDataSet getUser(long id) throws DBException {
    try {
      return (new UsersDAO(connection).get(id));
    } catch (SQLException e) {
      throw new DBException(e);
    }
  }

  public long addUser(String name) throws DBException {
    try {
      connection.setAutoCommit(false);
      UsersDAO dao = new UsersDAO(connection);
      dao.createTable();
      dao.insertUser(name);
      connection.commit();
      return dao.getUserId(name);
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ignore) {
      }
      throw new DBException(e);
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException ignore) {
      }
    }
  }

  public void cleanUp() throws DBException {
    UsersDAO dao = new UsersDAO(connection);
    try {
      dao.dropTable();
    } catch (SQLException e) {
      throw new DBException(e);
    }
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

  @SuppressWarnings("UnusedDeclaration")
  public static Connection getMySqlConnection() {
    try {
      DriverManager
          .registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

      StringBuilder url = new StringBuilder();
      url.append("jdbc:mysql://")
          .append("localhost:")
          .append("3306/")
          .append("db_example?")
          .append("user=root&")
          .append("password=root")
          .append("&serverTimezone=UTC");

      System.out.println("URL: " + url + "\n");

      return DriverManager.getConnection(url.toString());
    } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

}
