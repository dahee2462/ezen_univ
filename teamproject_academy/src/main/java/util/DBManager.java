package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	// [필드]
	private String url;
	private String user;
	private String pass;
	
	private Connection conn=null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	// 내부 카운터 : psmt 숫자++
	private int orderCount = 1; 

	// [생성자]
	public DBManager() {
		this.url= "jdbc:mysql://localhost:3306/board";
		this.user = "tester";
		this.pass = "1234";
	}
	
	// [게터세터]
	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Connection getConn() {
		return conn;
	}

	// [메소드]
	//DB 연결
	public boolean connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, pass);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//DB 종료
	public boolean disconnect() {
		try {
			//쿼리닫음
			release();
			if(conn != null) conn.close();
				
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//psmt
	public DBManager prepare(String sql) {
		
		if(conn == null) return null;
		
		try {
			//쿼리닫음
			release(); // rs, state 모두 초기화.?
			
			psmt = conn.prepareStatement(sql);

			orderCount = 1;

		}catch (Exception e) {

			e.printStackTrace();
			return null;
		}
		
		return this;
	}
	
	//파라미터 비우기
	public DBManager clearParameter() {
		
		orderCount=1;
		
		if(psmt!=null){
			try {
				psmt.clearParameters();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}
	
	// set 파라미터

	public DBManager setInt(int val) {
		if(psmt != null) {
			try {
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
		if(psmt != null) {
			try {
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
	

	//update,insert,delete -> 트랜잭션 필요
	public int update() throws SQLException{
		try {
			//트랜잭션 시작
			conn.setAutoCommit(false);
			if(psmt!=null) {
				return psmt.executeUpdate();
			}
			//커밋
			conn.commit();
		}catch(Exception e) {
			//롤백
			conn.rollback();
			e.printStackTrace();
		}finally {
			//트랜잭션 원복
			conn.setAutoCommit(true);
		}
		return 0;
	}
	
	//select
	public boolean select(){
		try {
			if(psmt!=null) {
				if(rs != null) {
					rs.close();
				}
				rs = psmt.executeQuery();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//close query
	public boolean release(){
		try {
			
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			
		}catch(Exception e) {
			
			return false;
		}
		return true;
	}
	
	
	// rs의 next() 실행할 메소드
	public boolean getNext() {
		if(rs == null)
			return false;
		
		try {	
			return this.rs.next();
		}catch(Exception e) {
			return false;
		}
	}
	
	//-get 파라미터
	// result로부터 값을 받아올 메소드: int, String 등등
	public String getString(String colName ) {
		try {
			return this.rs.getString(colName);
		}catch(Exception e) {
			
			return null;
		}
	}
	public int getInt(String colName ) {
		try {
			return this.rs.getInt(colName);
		}catch(Exception e) {
			return 0;
		}
	}
	
	// 예외처리 메소드 - '를 처리하는 메소드
	protected String _R(String value) {
		//return value.replace("'","''").replace(" ",""));
		return value.replace("'","''");
	}
	

}
