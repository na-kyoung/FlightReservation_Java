// 예약 관리 관련 DB 멤버 (reservation / flight / account)

package database;

public class ReservationDTO {
	private String reservation_id;
	private String reservation_price;
	private String account_id;
	private String flight_id;
	private String flight_date;
	private String flight_time;
	private String start_nation_id;
	private String end_nation_id;
	private String accound_pwd;
	
	public String getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getReservation_price() {
		return reservation_price;
	}
	public void setReservation_price(String reservation_price) {
		this.reservation_price = reservation_price;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public String getFlight_date() {
		return flight_date;
	}
	public void setFlight_date(String flight_date) {
		this.flight_date = flight_date;
	}
	public String getFlight_time() {
		return flight_time;
	}
	public void setFlight_time(String flight_time) {
		this.flight_time = flight_time;
	}
	public String getStart_nation_id() {
		return start_nation_id;
	}
	public void setStart_nation_id(String start_nation_id) {
		this.start_nation_id = start_nation_id;
	}
	public String getEnd_nation_id() {
		return end_nation_id;
	}
	public void setEnd_nation_id(String end_nation_id) {
		this.end_nation_id = end_nation_id;
	}
	public String getAccound_pwd() {
		return accound_pwd;
	}
	public void setAccound_pwd(String accound_pwd) {
		this.accound_pwd = accound_pwd;
	}
	
}
