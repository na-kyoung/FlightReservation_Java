// 항공편 클래스 (항공편 검색)

import java.util.ArrayList;

import database.FlightDAO;
import database.FlightDTO;

public class Flight {
	String flight_id; // 항공 번호
	String flight_date; // 항공 출발 날짜
	String flight_time; // 항공 출발 시각
	String filght_price; // 항공편 가격
	String start_nation_id; // 항공 출발 국가
	String end_nation_id; // 항공 도착 국가
	
	Flight(){} // default
	
	@Override
	public String toString() {
		return "Date : " + this.flight_date + " | Time : " + this.flight_time + " | Price : " + this.filght_price;
	}
	
	// 항공편 검색 (출발국가, 도착국가, 날짜)
	ArrayList<FlightDTO> SearchFlight (String start, String end, String date){
		FlightDAO fdao = new FlightDAO();
		ArrayList<FlightDTO> fList = new ArrayList<>();
		fList = fdao.SearchFlight(start, end, date);		
		return fList;
	}
	
	// 예약을 위한 항공편 찾기 (항공번호)
	FlightDTO GetFlight (String flightId){
		FlightDAO fdao = new FlightDAO();
		FlightDTO fdto = fdao.GetFlight(flightId);
		return fdto;
		
	}

}
