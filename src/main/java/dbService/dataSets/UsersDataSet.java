package dbService.dataSets;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {

  private static final long serialVersionUID = -8706689714326132798L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name", unique = true, updatable = false)
  private String name;

  @Column(name = "password")
  private String password;

  public UsersDataSet() {
  }

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet(long id, String name, String password) {
    this.id = id;
    this.name = name;
    this.password = password;
  }

  public UsersDataSet(String name) {
    this.setId(-1);
    this.setName(name);
    this.setPassword(name);
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UsersDataSet{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
