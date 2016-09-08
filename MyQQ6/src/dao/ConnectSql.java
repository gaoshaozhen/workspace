 package dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectSql{
	static String database;
	static String driver;
	static String username;
	static String password;
	static ConnectSql c=null;
	private ConnectSql() throws IOException, ClassNotFoundException{
//		InputStream in=Object.class.getResourceAsStream("/config/dao.properties");
//		Properties p=new Properties();
//		System.out.println(in);
//		p.load(in);
		File file=new File(Object.class.getResource("/config/dao.properties").getPath());
		InputStream in2=new FileInputStream(file);
//		InputStream in1= Object.class.getResource("/config/dao.properties").openStream();
		if(in2==null){
			System.out.println("未发现文件");
		}
		Properties p=new Properties();
		InputStream in=Object.class.getResourceAsStream("/config/dao.properties");
		try {
			p.load(in2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		driver=p.getProperty("driver");
		database=p.getProperty("database");
		username=p.getProperty("username");
		password=p.getProperty("password");
		Class.forName(driver);
	}
	public synchronized static ConnectSql getInstance(){
		if(c==null){
			try {
				c=new ConnectSql();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();				
			} catch (IOException e) {
				e.printStackTrace();				
			} 
		}
		return c;
	}
	public Connection getConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
