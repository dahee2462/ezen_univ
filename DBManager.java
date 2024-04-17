package ateam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

//import java.sql.*;

public class DBManager {
	/// 필드 멤버
	// DB 접속용 데이터
	private String host;	// 서버주소, 포트, DB이름, 세팅들
	private String userID;	// DB 접속 계정
	private String userPW;	// DB 계정 비번
	
	// DB 연결 개체들
	private Connection conn=null;
	private PreparedStatement psmt = null;
	private ResultSet result = null;
	
	// 내부 카운터
	private int orderCount = 1; // psmt setInt등에 쓰임
	
	/// 메소드
	// 생성자
	public DBManager() {
		
		this("allkeyboard", "keytester", "1234");
	}
	
	public DBManager(String dbName) {
		
		this(dbName, "keytester", "1234");
	}
	
	public DBManager(String dbName, String id, String pw) {
		this.host= "jdbc:mysql://127.0.0.1:3306/" + dbName;
		this.host+= "?useUnicode=true";
		this.host+= "&characterEncoding=utf-8";
		this.host+= "&serverTimezone=UTC";
		
		this.userID = id;
		this.userPW = pw;
	}
	
	// getter and setter
	public Connection getConn() {
		return conn;
	}
	
	public PreparedStatement getStatement() {
		return this.psmt;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	
	// DB 연결 메소드
	public boolean connect() {
		try {
			// 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			this.conn =DriverManager.getConnection(host,userID,userPW);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	// DB 종료 메소드
	public boolean disconnect() {
		try {
			
			release();
			
			if(conn != null) 
				conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public DBManager prepare(String sql) {
		if(conn == null)
			return null;
		
		try {
			release(); // rs, state 모두 초기화.
			
			psmt = conn.prepareStatement(sql);
			orderCount = 1;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return this;
	}
	
	public DBManager clearParameter() {
		
		orderCount=1;
		if(psmt!=null) 
		{
			try {
				psmt.clearParameters();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}
	
/*	Statement의 setXXX 함수들
   // 작업완료
    void setBoolean(int parameterIndex, boolean x) throws SQLException; 
    void setInt(int parameterIndex, int x) throws SQLException; 
    void setString(int parameterIndex, String x) throws SQLException;
    void setTimestamp(int parameterIndex, java.sql.Timestamp x)throws SQLException;
    
    // 보류
    void setByte(int parameterIndex, byte x) throws SQLException;
    void setShort(int parameterIndex, short x) throws SQLException;
    void setLong(int parameterIndex, long x) throws SQLException;
    void setFloat(int parameterIndex, float x) throws SQLException;
    void setDouble(int parameterIndex, double x) throws SQLException;
    void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException;
    void setBytes(int parameterIndex, byte x[]) throws SQLException;
    void setDate(int parameterIndex, java.sql.Date x) throws SQLException;
    void setTime(int parameterIndex, java.sql.Time x)throws SQLException;
    
*/
	public DBManager setBoolean(boolean v) {
		if(psmt != null) 
		{
			try 
			{
				psmt.setBoolean(orderCount, v);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			orderCount++;
			return this;
		}
		
		return null;
	}
	
	public DBManager setInt(int val) {
		if(psmt != null) 
		{
			try 
			{
				psmt.setInt(orderCount, val);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			orderCount++;
			return this;
		}
		
		return null;
	}
	
	public DBManager setString(String s) {
		if(psmt != null) 
		{
			try 
			{
				psmt.setString(orderCount, s);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			orderCount++;
			return this;
		}
		
		return null;
	}
	
	public DBManager setTimestamp(java.sql.Timestamp ts) {
		if(psmt != null) 
		{
			try 
			{
				psmt.setTimestamp(orderCount, ts);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			orderCount++;
			return this;
		}
		
		return null;
	}
	
	public int update()
	{
		try {
			if(psmt!=null) {
				return psmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean read()
	{
		try {
			if(psmt!=null) {
				if(result != null) {
					result.close();
				}
				result = psmt.executeQuery();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean release()
	{
		try {
			
			if(result != null)
				result.close();
		
			//문맥 객체 닫음
			if(psmt != null) 
				psmt.close();
			
		}catch(Exception e) {
			
			return false;
		}
		return true;
	}
	
	
	// result next() 실행할 메소드
	public boolean getNext() {
		if(result == null)
			return false;
		
		try {	
			return this.result.next();
		}catch(Exception e) {
			return false;
		}
	}
	// result로부터 값을 받아올 메소드 / int, String 등등
	public String getString(String colName ) {
		try {
			return this.result.getString(colName);
		}catch(Exception e) {
			
			return null;
		}
	}
	public int getInt(String colName ) {
		try {
			return this.result.getInt(colName);
		}catch(Exception e) {
			return 0;
		}
	}
	public boolean getBoolean(String colName) {
		try {
			return this.result.getBoolean(colName);
		}catch(Exception e) {
			return false;
		}
	}
	public java.sql.Timestamp getTimestamp(String colName){
		try {
			return this.result.getTimestamp(colName);
		}catch(Exception e) {
			return null;
		}
	}
	
	// 예외처리 메소드 - '를 처리하는 메소드
	protected String _R(String value) {
		//return value.replace("'","''").replace(" ",""));
		return value.replace("'","''");
	}
	
	
}
