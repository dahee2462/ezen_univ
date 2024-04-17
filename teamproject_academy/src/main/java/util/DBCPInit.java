package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class DBCPInit extends HttpServlet {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void init() throws ServletException {
        loadJDBCDriver();
        initConnectionPool();
    }
 
	
	
	// driver 로딩
    private void loadJDBCDriver()  {
    	//web.xml에 미리 선언해 놓은 서블릿 초기화 파라미터
        String driverClass = getInitParameter("jdbcDriver");
        try {
        	// connection pool 내부에서 사용할 jdbc 드라이버 로딩
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
        }
    }
 
    //connection pool 생성
    private void initConnectionPool() {
 
        String url = getInitParameter("jdbcUrl"); //
        String uid = getInitParameter("dbUser"); 
        String upw = getInitParameter("dbPassword"); 
 
        //ConnectionFactory - pool이 connetion을 생성할때 사용
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url,uid,upw);

        //PoolableConnectionFactory - DBCP가 pool에 connection 보관시 사용
        PoolableConnectionFactory poolableConnectionFactory =
        		new PoolableConnectionFactory(connectionFactory,null);
        poolableConnectionFactory.setValidationQuery("select 1");
        
        // connection pool 설정 정보들
        GenericObjectPoolConfig<PoolableConnection> poolConfig = new GenericObjectPoolConfig<PoolableConnection>();
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinIdle(4);
        poolConfig.setMaxTotal(50); //풀(Pool)에서 동시에 사용할 수 있는 최대 커넥션의 수
        //PostgreSQL이 추천하는 Connection Pool Size 공식
        //Connection Pool Size = (core_count * 2) + effective_spindle_count
        //- core_count: CPU 코어 개수.
        //- effective_spindle_count: 하드 디스크의 개수. spindle은 DB 서버가 관리할 수 있는 동시 I/O 요청의 개수를 의미한다.
        
        // connection pool 생성
        GenericObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory,poolConfig);
        poolableConnectionFactory.setPool(connectionPool);
        try {
        	// 커넥션 풀을 제공하는 jdbc 드라이버 등록
            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            
            // 커넥션 풀 드라이버에 커넥션 풀 등록 및 이름 설정
            String poolName = getInitParameter("poolName");
            driver.registerPool(poolName,connectionPool);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}