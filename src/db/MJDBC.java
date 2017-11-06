package db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.sqlite.JDBC;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteJDBCLoader;


//為了要實現foreignkey constraint
public class MJDBC extends JDBC
{

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		
		SQLiteConfig sqlconfig = new SQLiteConfig();  
		sqlconfig.enforceForeignKeys(true); 
		
		//Properties base = sqlconfig;
		 if (!super.acceptsURL(url)) {
	            return null;
		 }

	     url = url.trim();
	    
	     Properties p = sqlconfig.toProperties();
	     Enumeration e =  info.propertyNames();

	     while (e.hasMoreElements()) {
	       String key = (String) e.nextElement();
	       p.setProperty(key, info.getProperty(key));
	     }
		
		return DriverManager.getConnection(url,sqlconfig.toProperties());
	}
   
}