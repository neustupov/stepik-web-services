package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import java.sql.SQLException;

public class AccountService {

  private final DBService dbService;

  public AccountService() {
    this.dbService = new DBService();
  }

  public void addNewUser(UserProfile userProfile) throws SQLException, DBException {
    dbService.addUser(userProfile.getLogin());
  }

  public UserProfile getUserByLogin(String login) {
    try {
      UsersDataSet usersDataSet = dbService.getUser(login);
      if (usersDataSet != null) {
        return new UserProfile(usersDataSet.getName(), usersDataSet.getPassword());
      }
    } catch (SQLException | DBException e) {
      e.printStackTrace();
    }
    return null;
  }
}
