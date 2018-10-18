package netgloo.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ServiceLoader.Provider;

import javax.sql.DataSource;

import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import utils.TenantContextHolder;

public class UserSchemaAwareRoutingDataSource extends AbstractDataSource {
//
//@Autowired
//Provider<CustomUserDetails> customUserDetails;

@Autowired
private Environment env;
//private LoadingCache<String, DataSource> dataSources = createCache();
private DataSource DataSource;

//@Autowired
//private TenantContextHolder TenantContextHolder;

@Bean
private DataSource dataSource() {
  DriverManagerDataSource dataSource = new DriverManagerDataSource();
  dataSource.setDriverClassName(env.getProperty("db.driver"));
  dataSource.setUrl(env.getProperty("db.url"));
  dataSource.setUsername(env.getProperty("db.username"));
  dataSource.setPassword(env.getProperty("db.password"));
  System.out.println("im in ds");
  return dataSource;
}

@Override
public Connection getConnection() throws SQLException {
	try {
//    	Connection connection = DataSource.getConnection();
//		if (DataSource == null) {
		Connection	connection = dataSource().getConnection();
//		}
		  Statement statement = connection.createStatement();
		  String schema = "public";
	      System.out.println("im here inside getConnection");
	      System.out.println("passed in schema" + TenantContextHolder.getTenant());
		  if (TenantContextHolder.getTenant() != null) {
			  schema = TenantContextHolder.getTenant();
		  }
		  statement.execute("SET search_path TO " +  schema);
		  return connection;
	} catch(Exception e) {
		System.out.println("error in gerCOnnnection"+ e.toString());
		return null;
	}	
//    try {
//        return determineTargetDataSource().getConnection();
//    } catch (ExecutionException e){
//        e.printStackTrace();
//
//        return null;
//    }
}

//public UserSchemaAwareRoutingDataSource(DataSource dataSource) {
//	super();
//	DataSource = dataSource;
//}

@Override
public Connection getConnection(String username, String password) throws SQLException {
    System.out.println("getConnection" + username);
    System.out.println("getConnection2" + password);
    try {
        return determineTargetDataSource().getConnection(username, password);
    } catch (ExecutionException e) {
        e.printStackTrace();
        return null;
    }
}


private DataSource determineTargetDataSource() throws SQLException, ExecutionException {

    try {
    	System.out.println("im here inside determineTargetDataSource");
    	System.out.println("passed in schema" + TenantContextHolder.getTenant());
    	String schema = "?currentSchema=" + TenantContextHolder.getTenant();
    	return DataSourceBuilder.create()
        .username(env.getProperty("db.username"))
        .password(env.getProperty("db.password"))
        .url(env.getProperty("db.url") + schema)
        .driverClassName(env.getProperty("db.driver"))
        .build();
    } catch (NullPointerException e) {
        e.printStackTrace();
        return null;

//        return dataSources.get("fooooo");
    }
}

    
//private LoadingCache<String, DataSource> createCache() {
//    return CacheBuilder.newBuilder()
//       .maximumSize(100)
//       .expireAfterWrite(10, TimeUnit.MINUTES)
//       .build(
//           new CacheLoader<String, DataSource>() {
//             public DataSource load(String key) throws AnyException {
//               return buildDataSourceForSchema(key);
//             }
//           });
//}

//private DataSource buildDataSourceForSchema(String schema) {
//    // e.g. of property: "jdbc:postgresql://localhost:5432/mydatabase?currentSchema="
//    String url = env.getRequiredProperty("spring.datasource.url") + schema;
//    return DataSourceBuilder.create()
//        .driverClassName(env.getRequiredProperty("spring.datasource.driverClassName"))
//        [...]
//        .url(url)
//        .build();
//}

}