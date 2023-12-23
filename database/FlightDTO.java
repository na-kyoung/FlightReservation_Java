// 항공편 관련 DB 멤버 (flight / start_nation / end_nation)

package database;

public class FlightDTO {
	private String flight_id;
	private String flight_date;
	private String flight_time;
	private String flight_price;
	private String start_nation_id;
	private String end_nation_id;
	private String start_nation_name;
	private String end_nation_name;
	
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
	public String getFlight_price() {
		return flight_price;
	}
	public void setFlight_price(String flight_price) {
		this.flight_price = flight_price;
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
	public String getStart_nation_name() {
		return start_nation_name;
	}
	public void setStart_nation_name(String start_nation_name) {
		this.start_nation_name = start_nation_name;
	}
	public String getEnd_nation_name() {
		return end_nation_name;
	}
	public void setEnd_nation_name(String end_nation_name) {
		this.end_nation_name = end_nation_name;
	}
	
}
