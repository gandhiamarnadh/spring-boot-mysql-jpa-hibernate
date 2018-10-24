package netgloo.models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import netgloo.repository.EmployeeRepository;
/**
 * This class is used to access data for the Account entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class AccountDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
	
//	private static final String ANNOTATION_METHOD = "annotationData";
//    private static final String ANNOTATION_FIELDS = "declaredAnnotations";
//    private static final String ANNOTATIONS = "annotations";
    
//	@SuppressWarnings("unchecked")
//    public static void alterAnnotationValueJDK8(Class<?> targetClass, Class<? extends Annotation> targetAnnotation, Annotation targetValue) {
//        try {
//            Method method = Class.class.getDeclaredMethod(ANNOTATION_METHOD, null);
//            method.setAccessible(true);
//
//            Object annotationData = method.invoke(targetClass);
//            
//            Field annotations = annotationData.getClass().getDeclaredField(ANNOTATIONS);
//            annotations.setAccessible(true);
//
//            Map<Class<? extends Annotation>, Annotation> map = (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
//            map.put(targetAnnotation, targetValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
  
  /**
   * Return all the accounts stored in the database.
 * @throws SQLException 
   */
  @SuppressWarnings("unchecked")
  public List<Account> getAll() throws SQLException {
//	  Table table2 = new DynamicTable("account","org_00Df40000037j2kEAA");
//	  alterAnnotationValueJDK8(Account.class, Table.class, table2);
	  

//	  try {
//
//		  Account emp = entityManager.find(Account.class, "001f400000W1AFdAAN");
//		  System.out.println("custom query" + emp);
//	} catch (Exception e) {
//		System.out.println("account dao error"+ e.toString());
//	}
//	  
//	  try {
//		  final Connection connection = dataSource.getConnection();
//		  Statement statement = connection.createStatement();
//		  statement.execute("show search_path");
//		  statement.execute("SET search_path TO " + "org_00Df40000037j2kEAA");
//		  statement.execute("show search_path");
//		  statement.close();
//		
//	} catch (Exception e) {
//		System.out.println("account dao connection error"+ e.toString());
//	}
//	  
//	  try {
//		  Account emp = entityManager.find(Account.class, "001f400000W1AFdAAN");
//		  System.out.println("custom query" + emp);
//	} catch (Exception e) {
//		System.out.println("account dao error2"+ e.toString());
//	}
	  return repository.findAll();
//	  return entityManager.createQuery("from Account").getResultList();
  }
  
  

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private EmployeeRepository repository;
  
} // class AccountDao
