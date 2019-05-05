package dbService.dao;

import dbService.executor.Executor;
import java.sql.Connection;

public class UsersDao {

  private Executor executor;

  public UsersDao(Connection connection) {
    this.executor = new Executor(connection);
  }
}
