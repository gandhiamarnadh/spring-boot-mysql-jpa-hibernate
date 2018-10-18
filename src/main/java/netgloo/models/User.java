package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "users",schema="public")
public class User {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @NotNull
  private String email;
  
  @NotNull
  private String username;
  

  private String organiation_id;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public User() { 

	  Table table = User.class.getAnnotation(Table.class);
//	  System.out.println("default cons" + table);
  }

  public User(int id) { 
    this.id = id;
	  Table table = User.class.getAnnotation(Table.class);
//	  System.out.println("id cons" + table);
  }

  public User(String email, String username,String organiation_id) {
    this.email = email;
    this.username = username;
    this.organiation_id = organiation_id;
	  Table table = User.class.getAnnotation(Table.class);
	  System.out.println("all cons" + table);
  }

  public String organiation_id() {
    return organiation_id;
  }

  public void organiation_id(String value) {
    this.organiation_id = value;
  }
  
  public int getId() {
    return id;
  }

  public void setId(int value) {
    this.id = value;
  }

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String value) {
    this.username = value;
  }
  
} // class User
