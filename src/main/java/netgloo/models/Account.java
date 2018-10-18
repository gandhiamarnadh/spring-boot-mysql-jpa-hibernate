package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an Account for this web application.
 */
@Entity
//@Table(name = "account",schema = "org_00D1N000001trTWUAY")
@Table(name = "account")
public class Account {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  
  @NotNull
  private String billingcountry;
  
  @NotNull
  private String name;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public Account() {

	  Table table = Account.class.getAnnotation(Table.class);
//	  System.out.println("default cons" + table);
  }

  public Account(String id) { 
    this.id = id;
  }

  public Account(String billingcountry, String name) {
    this.billingcountry = billingcountry;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public String getBillingcountry() {
    return billingcountry;
  }
  
  public void setBillingcountry(String value) {
    this.billingcountry = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }
  
} // class Account
