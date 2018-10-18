package netgloo.models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.transaction.Transactional;
import netgloo.models.DynamicTable;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the User entity.
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
public class UserDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
 
	private static final String ANNOTATION_METHOD = "annotationData";
    private static final String ANNOTATION_FIELDS = "declaredAnnotations";
    private static final String ANNOTATIONS = "annotations";
    
	@SuppressWarnings("unchecked")
    public static void alterAnnotationValueJDK8(Class<?> targetClass, Class<? extends Annotation> targetAnnotation, Annotation targetValue) {
        try {
            Method method = Class.class.getDeclaredMethod(ANNOTATION_METHOD, null);
            method.setAccessible(true);

            Object annotationData = method.invoke(targetClass);
            
            Field annotations = annotationData.getClass().getDeclaredField(ANNOTATIONS);
            annotations.setAccessible(true);

            Map<Class<? extends Annotation>, Annotation> map = (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
            map.put(targetAnnotation, targetValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<User> getAll(String org) {
	  
//	  Table table = User.class.getAnnotation(Table.class);
//	  System.out.println("default cons " + table.schema());
//	  User emp = entityManager.find(User.class, "272");
//	  System.out.println("user figured at 0 " +  emp.toString());
//	  Table table2 = new DynamicTable("users","public");
//	  alterAnnotationValueJDK8(User.class, Table.class, table2);
//	  table = User.class.getAnnotation(Table.class);
//	  System.out.println("chnaged cons " + table.schema());
//	  emp = entityManager.find(User.class, "272");
//	  System.out.println("user figured at 1 " +  emp.toString());

	  try {
		  User emp = entityManager.find(User.class, 272);
		  System.out.println("accountDao find"+ emp.toString());
	} catch (Exception e) {
		System.out.println("userdao exception" + e.toString());
	}
    return entityManager.createQuery("from User where organization_id='" + org + "'").getResultList();
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class UserDao
