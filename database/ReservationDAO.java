// 예약 관리 관련 DB (예약 / 예약 내역 확인 / 취소할 예약 확인 / 예약 취소)

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO {

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
    
    // 예약하기
    public void Reserve(String reservationId, String reservationPrice, String accountId, String flightId) {
//    	System.out.println("(예약 중..)");
    	
    	// DB 연결
    	connect();
    	
    	// SQL문
    	String SQL = "INSERT INTO RESERVATION VALUES (?,?,?,?)";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, reservationId);
    		pstmt.setString(2, reservationPrice);
    		pstmt.setString(3, accountId);
    		pstmt.setString(4, flightId);
    		pstmt.executeUpdate();
    		
    		System.out.println("✈︎ 예약 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    }
    
    // 예약 내역 확인 (아이디)
    public ArrayList<ReservationDTO> CheckReservation(String accountId){
//    	System.out.println("(예약 확인 시작)");

    	// DB 연결
    	connect();
    	
    	// 배열 생성
    	ArrayList<ReservationDTO> rList = new ArrayList<>();
    	
    	// SQL문
    	String SQL = "SELECT R.RESERVATION_ID, R.RESERVATION_PRICE, R.FLIGHT_ID, F.FLIGHT_DATE, F.FLIGHT_TIME, F.START_NATION_ID, F.END_NATION_ID "
    			+ "FROM RESERVATION R, FLIGHT F "
    			+ "WHERE R.FLIGHT_ID = F.FLIGHT_ID "
    			+ "AND ACCOUNT_ID = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, accountId);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			ReservationDTO rdto = new ReservationDTO();
    			
    			rdto.setReservation_id(rs.getString(1));
    			rdto.setReservation_price(rs.getString(2));
    			rdto.setFlight_id(rs.getString(3));
    			rdto.setFlight_date(rs.getString(4));
    			rdto.setFlight_time(rs.getString(5));
    			rdto.setStart_nation_id(rs.getString(6));
    			rdto.setEnd_nation_id(rs.getString(7));

    			rList.add(rdto);
    		}
//    		System.out.println("✈︎ 예약 내역 확인 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    	return rList;
    }
    
    // 취소하기 위한 예약 확인 (항공번호)
    public ReservationDTO ReservationForDel(String reservationId) {
//    	System.out.println("(예약 찾기 시작)");
    	
    	// DB 연결
    	connect();
    	
    	// 배열 생성
    	ReservationDTO rdto = new ReservationDTO();
    	
    	
    	// SQL문
    	String SQL = "SELECT R.RESERVATION_ID, R.RESERVATION_PRICE, R.FLIGHT_ID, F.FLIGHT_DATE, F.FLIGHT_TIME, F.START_NATION_ID, F.END_NATION_ID "
    			+ "FROM RESERVATION R, FLIGHT F "
    			+ "WHERE R.FLIGHT_ID = F.FLIGHT_ID "
    			+ "AND RESERVATION_ID = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, reservationId);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			
    			rdto.setReservation_id(rs.getString(1));
    			rdto.setReservation_price(rs.getString(2));
    			rdto.setFlight_id(rs.getString(3));
    			rdto.setFlight_date(rs.getString(4));
    			rdto.setFlight_time(rs.getString(5));
    			rdto.setStart_nation_id(rs.getString(6));
    			rdto.setEnd_nation_id(rs.getString(7));
    			
    		}
//    		System.out.println("✈︎ 예약 찾기 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    	return rdto;
    }
    
    // 예약 취소
    public void DeleteReservation(String reservationId, String accountPwd) {
//    	System.out.println("(예약 취소 시작)");
    	
    	// DB 연결
    	connect();
    	
    	// SQL문
    	String SQL = "DELETE FROM RESERVATION "
    			+ "WHERE RESERVATION_ID = ? "
    			+ "AND ACCOUNT_ID = ( "
    			+ "SELECT ACCOUNT_ID "
    			+ "FROM ACCOUNT "
    			+ "WHERE ACCOUNT_PWD = ?)";
    	
    	try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, reservationId);
    		pstmt.setString(2, accountPwd);
    		pstmt.executeUpdate();
    		
    		System.out.println("✈︎ 예약 취소 완료");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    }

}
