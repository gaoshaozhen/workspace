package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class User {
	ConnectSql getConn=null;	
	public User(){
		getConn=ConnectSql.getInstance();
	}
	private  Connection getConnection(){
		return getConn.getConnection();
	}
	public boolean isUser(String id,String password){//验证该用户是否存在，存在返回true，不存在返回false
		boolean is=false;
		Connection conn=null;
		Statement stat=null;
		ResultSet result=null;
		int number=0;
		try{
			conn=getConnection();
			stat=conn.createStatement();
			result=stat.executeQuery("select * from user where id="+id+" and  password=\'"+password+"\';");			
			while(result.next()){
				number++;				
			}
			if(number>0){//用户存在
				is=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll(conn,stat,result);
		return is;
	}
	public String  addUser(String name,String password){//增加新用户,成功则返回id，失败则返回null
		String newId=null;
		Connection conn=null;
		Statement stat=null;
		ResultSet result=null;
		String sql=null;
		try{
			conn=getConnection();
			stat=conn.createStatement();
			result=stat.executeQuery("select id from user;");
			int f=0;
			int num=0;
			while(result.next()){
				 f=result.getInt("id");
				 num++;
			}
			f++;
			num++;
			sql="insert into user values("+num+","+f+",\'"+name+"\',\'"+password+"\',\'\')";
			stat.executeUpdate(sql);
			newId=String.valueOf(f);
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll(conn,stat,result);
		return newId;
	}
	public boolean addFriends(String id,String friendId){//为id增加新朋友friendId，成功返回true，失败返回false
		boolean is=false;
		Connection conn=null;
		Statement stat=null;
		ResultSet result=null;
		String sql="select friends from user where id="+id+";";
		try{
			conn=getConnection();
			stat=conn.createStatement();
			result=stat.executeQuery(sql);	
			if(result.next()){
				String str=result.getString("friends");
				if(str.indexOf(friendId)==-1){//还没有该好友
					sql="update user set friends=\'"+str+"#"+friendId+"\' where id="+id+";";
					stat.executeUpdate(sql);
					is=true;
				}
			}
		}catch(SQLException e){
			System.out.println(sql);
			e.printStackTrace();
		}
		closeAll(conn,stat,result);
		return is;
	}
	public String[] getAllFriends(String id){//获得id的所有朋友id,一个也没有则返回null
		String[] allFriends=null;
		Connection conn=null;
		Statement stat=null;
		ResultSet result=null;
		String sql="select friends from user where id="+id+";";
		try{
			conn=getConnection();
			stat=conn.createStatement();
			result=stat.executeQuery(sql);
			if(result.next()){
				String str=result.getString("friends");
				if(str!=null){
					allFriends=str.split("#");
				}
				else{
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll(conn,stat,result);
		int leng=allFriends.length;
		String[]t=new String[leng-1];
		if(allFriends!=null){
			
			for(int i=1;i<leng;i++){
				t[i-1]=allFriends[i];
			}
		}
		return t;
	}
	public boolean deleteFriend(String id,String friendId){//删除朋友
		boolean is=false;		
		Connection conn=null;
		Statement stat=null;
		ResultSet result=null;
		String sql="select friends from user where id="+id+";";
		try{
			conn=getConnection();
			stat=conn.createStatement();
			result=stat.executeQuery(sql);
			if(result.next()){
				String str=result.getString("friends");
				str=str.replace("#"+friendId, "");
				sql="update user set friends=\'"+str+"\' where id="+id+";";
				stat.executeUpdate(sql);
				sql="select friends from user where id="+id+";";
				close(result);
				result=stat.executeQuery(sql);//检查是否成功删除
				if(result.next()){
					String str1=result.getString("friends");
					if(str1.indexOf("#"+friendId)==-1){
						is=true;
					}
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll(conn,stat,result);
		return is;
	}
	public boolean existId(String id){//验证该id是否存在
		boolean is=false;
		Connection conn=null;
		Statement stat=null;
		ResultSet result=null;
		int number=0;
		try{
			conn=getConnection();
			stat=conn.createStatement();
			result=stat.executeQuery("select * from user where id="+id+";");			
			while(result.next()){
				number++;				
			}
			if(number>0){//用户存在
				is=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll(conn,stat,result);
		return is;
	}
	private void closeAll(Connection conn,Statement stat,ResultSet result){//关闭连接
		try{
			if(result!=null){
				result.close();
			}
			if(stat!=null){
				stat.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	private void close(ResultSet result){
		try{
			if(result!=null){
				result.close();
				result=null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
