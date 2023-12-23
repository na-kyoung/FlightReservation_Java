// 항공편 관련 DB (항공편 검색 / 예약을 위한 항공편 찾기)

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class FlightDAO {
	
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
    
    // 항공편 검색 (출발국가, 도착국가, 날짜)
    public ArrayList<FlightDTO> SearchFlight(String start, String end, String date) {
//    	System.out.println("(항공 정보 검색 시작)");
    	
    	// DB 연결
    	connect();
    	
    	// 배열 생성
    	ArrayList<FlightDTO> fList = new ArrayList<>();
    	
    	// SQL문
    	String SQL = "SELECT F.FLIGHT_ID, F.FLIGHT_DATE, F.FLIGHT_TIME, F.FLIGHT_PRICE, F.START_NATION_ID, F.END_NATION_ID "
    			+ "FROM FLIGHT F, START_NATION SN, END_NATION EN "
    			+ "WHERE F.START_NATION_ID = SN.START_NATION_ID "
    			+ "AND F.END_NATION_ID = EN.END_NATION_ID "
    			+ "AND SN.START_NATION_NAME = ? "
    			+ "AND EN.END_NATION_NAME = ? "
    			+ "AND F.FLIGHT_DATE = ? "
    			+ "ORDER BY F.FLIGHT_ID";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, start);
    		pstmt.setString(2, end);
    		pstmt.setString(3, date);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			FlightDTO fdto = new FlightDTO();
    			
    			fdto.setFlight_id(rs.getString(1));
    			fdto.setFlight_date(rs.getString(2));
    			fdto.setFlight_time(rs.getString(3));
    			fdto.setFlight_price(rs.getString(4));
    			fdto.setStart_nation_id(rs.getString(5));
    			fdto.setEnd_nation_id(rs.getString(6));
    			
    			fList.add(fdto);
    		}
//    		System.out.println("✈︎ 항공 정보 검색 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    	return fList;
    }
    
    // 예약을 위한 항공편 찾기 (항공번호)
    public FlightDTO GetFlight(String flightId) {
//    	System.out.println("(항공편 찾기 시작)");
    	
    	// DB 연결
    	connect();
    	
    	// 배열 생성
    	FlightDTO fdto = new FlightDTO();
    	
    	
    	// SQL문
    	String SQL = "SELECT * "
    			+ "FROM FLIGHT "
    			+ "WHERE FLIGHT_ID = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, flightId);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			
    			fdto.setFlight_id(rs.getString(1));
    			fdto.setFlight_date(rs.getString(2));
    			fdto.setFlight_time(rs.getString(3));
    			fdto.setFlight_price(rs.getString(4));
    			fdto.setStart_nation_id(rs.getString(5));
    			fdto.setEnd_nation_id(rs.getString(6));
    			
    		}
//    		System.out.println("✈︎ 항공 정보 검색 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    	return fdto;
    }


}
