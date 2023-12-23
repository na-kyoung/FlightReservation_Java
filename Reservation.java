// 예약 클래스 (예약 / 예약 내역 확인 / 예약 취소)

import java.util.ArrayList;

import database.ReservationDAO;
import database.ReservationDTO;

public class Reservation {
	String reservation_id; // 예약 번호
	String reservation_price; // 예약 가격
	String account_id; // 예약 계정 아이디
	String flight_id; // 예약 항공번호
	
	Reservation(){} // default
	
	// 생성자 (예약 정보 출력)
	Reservation(String reservationId, String reservationPrice, String accountId, String flightId){
		this.reservation_id = reservationId;
		this.reservation_price = reservationPrice;
		this.account_id = accountId;
		this.flight_id = flightId;
		System.out.println("예약 정보 : " + reservation_id + " / " + reservation_price + " / " + account_id + " / " + flight_id);
	}

	// 예약하기 (예약번호, 예약가격, 아이디, 항공번호)
	void Reserve (String reservationId, String reservationPrice, String accountId, String flightId) {
		ReservationDAO rdao = new ReservationDAO();
		rdao.Reserve(reservationId, reservationPrice, accountId, flightId);
	}
	
	// 예약 내역 확인 (아이디)
	ArrayList<ReservationDTO> CheckReservation(String accountId){
		ReservationDAO rdao = new ReservationDAO();
		ArrayList<ReservationDTO> rList = new ArrayList<>();
		rList = rdao.CheckReservation(accountId);
		return rList;
	}
	
	// 취소하기 위한 예약 확인 (예약번호)
	ReservationDTO ReservationForDel(String reservationId){
		ReservationDAO rdao = new ReservationDAO();
		ReservationDTO rdto = rdao.ReservationForDel(reservationId);
		return rdto;
	}
	
	// 예약 취소 (예약번호, 아이디, 비밀번호)
	void DeleteReservation(String reservationId, String accountPwd) {
		ReservationDAO rdao = new ReservationDAO();
		rdao.DeleteReservation(reservationId, accountPwd);
	}
}