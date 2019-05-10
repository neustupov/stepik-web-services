package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;
import java.sql.SQLException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {

  private Session session;

  private Executor executor;

  public UsersDAO(Session session) {
    this.session = session;
  }

  public UsersDataSet get(long id) throws SQLException {
    return (UsersDataSet) session.get(UsersDataSet.class, id);
  }

  public long getUserId(String name) throws SQLException {
    Criteria criteria = session.createCriteria(UsersDataSet.class);
    return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult())
        .getId();
  }

  public long insertUser(String name) throws SQLException {
    return (Long) session.save(new UsersDataSet(name));
  }
}
