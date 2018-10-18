package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.Account;
import netgloo.models.UserDao;
import utils.TenantContextHolder;
import netgloo.models.AccountDao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class UserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
	
	 /**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	
//	@Autowired
//	private utils.TenantContextHolder TenantContextHolder;


    
  @RequestMapping(value="/userAll")
  @ResponseBody
  public List<User> getAllUsers(String org) {
	  List<User> users = null;
    try {
    	TenantContextHolder.setTenantId(org);
    	users = userDao.getAll(org.substring(4, org.length()));
    }
    catch (Exception ex) {
//      return "Error getting the users: " + ex.toString();
    }
    return users;
  }
  
  //org_00D1N000001trTWUAY
  //org_00Df40000037j2kEAA
  
  @RequestMapping(value="/accountAll")
  @ResponseBody
  public List<Account> getAllAccounts(String org) {
	  List<Account> accounts = null;
	  System.out.println("log org passed" + org);
    try {
    	TenantContextHolder.setTenantId(org);
    	accounts = AccountDao.getAll();
    }
    catch (Exception ex) {
    	System.out.println((ex.toString()));
//      return "Error getting the users: " + ex.toString();
    }
    return accounts;
  }
  
  @RequestMapping(value="/getAll")
  @ResponseBody
  public List<Account> getAll(String org) {
	  List<Account> accounts = null;
	  List<User> users = null;
    try {

    	TenantContextHolder.setTenantId(org);
    	accounts = AccountDao.getAll();
    	users = userDao.getAll(org.substring(4, org.length()));
    	System.out.println(users);
    }
    catch (Exception ex) {
//      return "Error getting the users: " + ex.toString();
    }
    return accounts;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;
  
  @Autowired
  private AccountDao AccountDao;
  
  
} // class UserController
