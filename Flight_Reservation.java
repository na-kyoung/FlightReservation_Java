// 항공편 예약 서비스 메뉴 애플리케이션
// 메뉴 번호를 입력하여 해당 메뉴 서비스 실행

import java.util.ArrayList;
import java.util.Scanner;

import database.FlightDTO;
import database.ReservationDTO;

public class Flight_Reservation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in); // 스캐너 객체
//		Account account = new Account(); // 회원 클래스 객체
		Flight flight = new Flight(); //  항공편 클래스 객체
		Reservation reservation = new Reservation(); // 예약 클래스 객체
		
		int menu; // 메뉴
		String id, pwd; // 회원가입, 로그인, 로그아웃
		String result; // 로그인 결과(로그인 성공 시 id와 동일)
		String accountId = null; // 로그인 시 아이디 저장, 로그아웃 시 초기화
		String accountPwd = null; // 로그인 시 비밀번호 저장, 로그아웃 시 초기화
		String date, start, end; // 항공 검색 조건
		String flightId; // 예약시 항공번호 확인
		String answer; // 예약 및 예약 취소 확인
		String reservationId; // 예약 번호 (계정아이디 + 항공번호)
		String reservationPrice; // 예약 결제 가격
		boolean cnd = true; // 프로그램 활성화 및 종료

		
		do {
			// 메뉴 구성
			System.out.println("✈︎---✈︎---✈︎---✈︎");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인/로그아웃");
			System.out.println("3. 항공편 검색");
			System.out.println("4. 예약");
			System.out.println("5. 예약 확인");
			System.out.println("6. 예약 취소");
			System.out.println("7. 종료");
			System.out.println("✈︎---✈︎---✈︎---✈︎");
			
			System.out.print("✈︎ 메뉴 번호 : ");
			menu = scan.nextInt(); // 메뉴 번호
			
			// 메뉴 번호에 따라 서비스 실행
			switch(menu) {
			case 1 -> { // 회원가입
				System.out.print("✈︎ 아이디 : ");
				id = scan.next(); // 아이디 입력
				System.out.print("✈︎ 비밀번호 : ");
				pwd = scan.next(); // 비밀번호 입력
				
//				account.Join(id, pwd); // DB 회원가입
				
				Account Ajoin = new Account(id, pwd); // 생성자로 정보 출력
				Ajoin.Join(id, pwd); // DB 회원가입
				
			}
			case 2 -> { // 로그인/로그아웃
				if(accountId == null) { // 로그인
					System.out.print("✈︎ 아이디 : ");
					id = scan.next(); // 아이디 입력
					System.out.print("✈︎ 비밀번호 : ");
					pwd = scan.next(); // 비밀번호 입력
					
//					result = account.Login(id, pwd); // DB 로그인
					
					Account Alogin = new Account(id, pwd); // 생성자로 정보 출력
					result = Alogin.Login(id, pwd); // DB 로그인

					if(result.equals(id)) { // 로그인 성공 - 정보 저장
						accountId = id; // 아이디 저장
						accountPwd = pwd; // 비밀번호 저장
					}
				}
				else { // 로그아웃 - 정보 초기화
					accountId = null;
					accountPwd = null;
					System.out.println("✈︎ 로그아웃 완료");
				}
			}
			case 3 -> { // 항공편 검색
				System.out.print("✈︎ 출발 국가 : ");
				start = scan.next(); // 출발 국가 입력
				System.out.print("✈︎ 도착 국가 : ");
				end = scan.next(); // 도착 국가 입력
				System.out.print("✈︎ 날짜 : ");
				date = scan.next(); // 날짜 입력
				
				ArrayList<FlightDTO> fList = flight.SearchFlight(start, end, date); // DB 항공편 검색
				
				if(fList.size() == 0) { // 항공편이 없을 때
					System.out.println("✈︎ 조건에 해당하는 항공편이 존재하지 않습니다.");
				}
				else { // 항공편이 있을 때
					// 항공 정보 출력
					System.out.println("항공번호" + "\t\t" + "날짜" + "\t\t" + "출발시각" + "\t" + "출발국가" + "\t" + "도착국가" + "\t" + "가격");
					for(FlightDTO f:fList) {
						String fdate = f.getFlight_date().substring(0,10);
						System.out.println(f.getFlight_id() + "\t\t" + fdate + "\t" + f.getFlight_time() + "\t" + f.getStart_nation_id() + "\t" + f.getEnd_nation_id() + "\t" + f.getFlight_price());
					}
//					for(int i=0;i<fList.size();i++) {
//						String fdate = flist.get(i).getFlight_date().substring(0,10);
//						System.out.println(flist.get(i).getFlight_id() + "\t" + fdate + "\t" + flist.get(i).getFlight_time() + "\t" + flist.get(i).getFlight_price() + "\t" + flist.get(i).getStart_nation_id() + "\t" + flist.get(i).getEnd_nation_id());
//					}
				}
			}
			case 4 -> { // 예약
				if(accountId == null) { // 로그인 X
					System.out.println("✈︎ 로그인 후 이용할 수 있습니다.");
				}
				else { // 로그인 O
					System.out.print("✈︎ 예약을 원하는 항공번호 입력 : ");
					flightId = scan.next(); // 항공번호 입력
					
					FlightDTO fdata = flight.GetFlight(flightId); // DB 항공번호로 항공편 찾기
					
					// 항공편 출력
					System.out.println("항공번호" + "\t" + "출발날짜" + "\t\t" + "출발시각" + "\t" + "가격" + "\t" + "출발국가" + "\t" + "도착국가");
					String fdate = fdata.getFlight_date().substring(0,10); // 날짜 형식 설정
					System.out.println(fdata.getFlight_id() + "\t" + fdate + "\t" + fdata.getFlight_time() + "\t" + fdata.getFlight_price() + "\t" + fdata.getStart_nation_id() + "\t" + fdata.getEnd_nation_id());

					// 예약 확인 질문
					System.out.print("✈︎ 위 항공편으로 예약하시겠습니까?(Yes/No) : ");
					answer = scan.next(); // 대답 입력

					// 예약
					if(answer.equalsIgnoreCase("yes")) { // yes (예약함)
						reservationId = accountId + flightId; // 예약번호 = 아이디+항공번호
						reservationPrice = fdata.getFlight_price(); // 예약가격 = 항공가격
						
						reservation.Reserve(reservationId, reservationPrice, accountId, flightId); // DB 예약
						
//						Reservation reserve = new Reservation(reservationId, reservationPrice, accountId, flightId); // 생성자로 예약 정보 출력
//						reserve.Reserve(reservationId, reservationPrice, accountId, flightId); // DB 예약
					}
					else { System.out.println("✈︎ 감사합니다."); } // no (예약 안함)
				}
			}
			case 5 -> { // 예약 내역 확인
				if(accountId == null) { // 로그인 X
					System.out.println("✈︎ 로그인 후 이용할 수 있습니다.");
				}
				else { // 로그인 O
					ArrayList<ReservationDTO> rList = reservation.CheckReservation(accountId); // DB 예약 내역 확인
					
					if(rList.size() == 0) { // 예약 내역이 없을 때
						System.out.println("✈︎ 예약 내역이 존재하지 않습니다.");
					}
					else { // 예약 내역이 있을 떄
						// 예약 내역 출력
						System.out.println("✈︎ " + accountId + "님의 예약 내역");
						System.out.println("예약번호" + "\t\t" + "항공번호" + "\t\t" + "출발날짜" + "\t\t" + "출발시각" + "\t" + "출발국가" + "\t" + "도착국가" + "\t" + "가격");
						for(ReservationDTO r:rList) {
							String rdate = r.getFlight_date().substring(0,10); // 날짜 형식 설정
							System.out.println(r.getReservation_id() + "\t" + r.getFlight_id() + "\t\t" + rdate + "\t" + r.getFlight_time() + "\t" + r.getStart_nation_id() + "\t" + r.getEnd_nation_id() + "\t" + r.getReservation_price());
						}
					}	
				}
			}
			case 6 -> { // 예약 취소
				if(accountId == null) { // 로그인 X
					System.out.println("로그인 후 이용할 수 있습니다.");
				}
				else { // 로그인 O
					System.out.print("✈︎ 취소하고 싶은 예약번호 : ");
					reservationId = scan.next(); // 예약번호 입력
					
					ReservationDTO rdata = reservation.ReservationForDel(reservationId); // DB 예약번호로 예약 찾기
					
					// 취소할 예약 내역 출력
					System.out.println("예약번호" + "\t\t" + "항공번호" + "\t\t" + "출발날짜" + "\t\t" + "출발시각" + "\t" + "출발국가" + "\t" + "도착국가" + "\t" + "가격");
					String rdate = rdata.getFlight_date().substring(0,10); // 날짜 형식 설정
					System.out.println(rdata.getReservation_id() + "\t" + rdata.getFlight_id() + "\t\t" + rdate + "\t" + rdata.getFlight_time() + "\t" + rdata.getStart_nation_id() + "\t" + rdata.getEnd_nation_id() + "\t" + rdata.getReservation_price());
					
					// 예약 취소 확인 질문
					System.out.print("✈︎ 위 예약을 취소하시겠습니까?(Yes/No) : ");
					answer = scan.next(); // 대답 입력
					
					// 예약 취소
					if(answer.equalsIgnoreCase("yes")) { // yes (예약 취소 함)
						System.out.print("✈︎ 비밀번호 입력 : ");
						pwd = scan.next(); // 비밀번호 입력
						
						if(accountPwd.equals(pwd)) { // 비밀번호 일치
							reservation.DeleteReservation(reservationId, accountPwd); // DB 예약 취소
						}
						else { System.out.println("✈︎ 비밀번호가 일치하지 않습니다.");} // 비밀번호 불일치
					}
					else { System.out.println("✈︎ 감사합니다."); } // no (예약 취소 안함)
				}
			}
			case 7 -> { // 종료
				System.out.println("✈︎ 프로그램을 종료합니다.");
				cnd = false;
			}
			default -> System.out.println("✈︎ 해당 번호는 메뉴가 존재하지 않습니다.");
			} // switch문
		}while(cnd);

	}
}
