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

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet() {
  }

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public UsersDataSet(String name) {
    this.setId(-1);
    this.setName(name);
  }

  public long getId() {
    return id;
  }

  @SuppressWarnings("UnusedDeclaration")
  public String getName() {
    return name;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UsersDataSet{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
