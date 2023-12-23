// 회원 관련 DB (로그인 / 회원가입)

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	
	// DB 연결 정보
	String driver = "oracle.jdbc.driver.OracleDriver"; 
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbid = "flight";
    String dbpwd = "tiger";
    
    Connection conn = null; // DB 연결 객체
    PreparedStatement pstmt = null; // Connection로 SQL 명령 실행 객체
    ResultSet rs = null; // SQL 실행 결과 저장 객체

    // DB 연결
    public void connect() {
        try {
        	Class.forName(driver);
        	conn = DriverManager.getConnection(url, dbid, dbpwd);
        }
        catch(Exception e){ 
	    	e.printStackTrace();
	    }
    }
    
    // DB 연결 해제
    public void disconnect() {
    	if(conn != null) {
    		try {
    			conn.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	if(pstmt != null) {
    		try {
    			pstmt.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	if(rs != null) {
    		try {
    			rs.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    // 로그인
    public String Login(String id, String pwd) {
//    	System.out.println("(로그인 중..)");
    	
    	// DB 연결
    	connect();

    	// 변수 생성
    	String result = null;
    	
    	// SQL문
    	String SQL = "SELECT ACCOUNT_PWD FROM ACCOUNT WHERE ACCOUNT_ID = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			if(rs.getString(1).equals(pwd)) {
    	    		System.out.println("✈︎ 로그인 완료");
    				result = id;
    				return result;
    			}
    			else {
    				System.out.println("✈︎ 비밀번호 틀림");
    				result = "비밀번호 틀림";
    				return result;
    			}
    		}
    		System.out.println("✈︎ 아이디 틀림");
    		result = "아이디 틀림";
    		return result;
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    	System.out.println("✈︎ DB 오류");
    	result = "DB 오류";
    	return result;
    }
    
    // 회원가입
    public void Join(String id, String pwd) {
//    	System.out.println("(회원가입 중..)");
    	
    	// DB 연결
    	connect();
    	
    	// SQL문
    	String SQL = "INSERT INTO ACCOUNT VALUES (?,?)";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, id);
    		pstmt.setString(2, pwd);
    		pstmt.executeUpdate();
    		
    		System.out.println("✈︎ 회원가입 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    }

}
