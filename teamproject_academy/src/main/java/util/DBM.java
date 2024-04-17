package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBM {
	
	// [싱글톤]
	private DBM() {}
	
	private static DBM dbm = new DBM();
	
	public static DBM getInstance() {
		return dbm;
	}
	
	
//	[필드] *동시성 문제 주의 : 클라이언트에 의존할 수 있는 필드 or 싱글톤 객체를 사용하는 메소드가 있으면 동시성 문제가 발생한다. 해결방법- 스레드로컬에 필드넣기
//	private Connection conn=null;
//	private PreparedStatement psmt = null;
//	private ResultSet rs = null;
	// 내부 카운터 : psmt 숫자++
//  private int orderCount = 1; 
	
	//스레드 로컬을 사용한다.
	private static ThreadLocal<Connection> localConnection 
    = new ThreadLocal<Connection>(); 
	private static ThreadLocal<PreparedStatement> localPreparedStatement
    = new ThreadLocal<PreparedStatement>();
	private static ThreadLocal<ResultSet> localResultSet 
    = new ThreadLocal<ResultSet>();
	private static ThreadLocal<Integer> localorderCount
    = new ThreadLocal<Integer>();
	
	
	
	
	
	public DBM prepare(String sql) {
		localorderCount.set(1);
		try {
			//정적 블록에 커넥션 객체를 생성하면 닫힌 후 사용할 수 없음.  -> 커넥션 풀 적용하기 완료
			Connection conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:ezenuniv");
			localConnection.set(conn); 
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			localPreparedStatement.set(psmt);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return this;
	}
	
	public DBM setString(String value) {
		try {
			localPreparedStatement.get().setString(localorderCount.get(), value);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		localorderCount.set(localorderCount.get()+1);
		return this;
	}
	
	
	public DBM setInt(int value) {
		try {
			localPreparedStatement.get().setInt(localorderCount.get(), value);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		localorderCount.set(localorderCount.get()+1);
		return this;
	}
	
	public int update() {
		int result = 0;
		try {
			result = localPreparedStatement.get().executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void select(){
		try {
			localResultSet.set(localPreparedStatement.get().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean next() {
		try {
			return localResultSet.get().next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getInt(String column) {
		try {
			return localResultSet.get().getInt(column);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getString(String column) {
		try {
			return localResultSet.get().getString(column);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public boolean close() {
		try {
			if(localResultSet.get() != null) localResultSet.get().close();
			if(localPreparedStatement.get() != null) localPreparedStatement.get().close();
			if(localConnection.get() != null) localConnection.get().close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		localConnection.remove();
		localPreparedStatement.remove();
		localResultSet.remove();
		localorderCount.remove();
		//스레드 변수로 인한 메모리 누수 방지
		return true;
	}
	
}