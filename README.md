# ✈️ 항공 예약 시스템
- 비행기 항공편을 검색하고 예약할 수 있는 자바 애플리케이션 구현
- DTO/DAO 객체를 활용하여 데이터베이스에 접근함으로써, 데이터의 재사용성을 높이고 가장 최신의 데이터를 유지

<br />

## 개발 기간
> 2023.11.21 ~ 2023.12.14

<br />

## 개발 환경
- JAVA (JDK 17)
- Oracle 11g
- 활용 class
  - java.util.ArrayList
  - java.util.Scanner
  - java.sql.Connection
  - java.sql.DriverManager
  - java.sql.PreparedStatement
  - java.sql.ResultSet
  - java.sql.SQLException

<br />

## 구현 기능 및 기여도
- 기여도 : 100% (개인 프로젝트)
- 회원가입
- 로그인/로그아웃
  - 로그인 오류 처리 (아이디 틀림/비밀번호 틀림/DB 오류)
- 항공편 검색
- 예약
  - 항공번호로 예약
  - 로그인 후 이용 가능
- 예약 확인
  - 모든 예약 내역 출력
  - 로그인 후 이용 가능
- 예약 취소
  - 취소할 예약번호 확인
  - 비밀번호 확인 후 취소
  - 로그인 후 이용 가능
- 종료

<br />

## 데이터베이스 설계

### ERD
<img width="80%" alt="FlightDB" src="https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/779ece4f-b723-4524-90dd-b3a49c58b110">

<br />
<br />

### 논리 데이터 모델링
- **회원** (회원아이디(PK), 회원비밀번호)
- **항공편** (항공번호(PK), 항공출발날짜, 항공출발시각, 항공가격, 출발국가번호(FK), 도착국가번호(FK))
- **출발국가** (출발국가번호(PK), 국가명)
- **도착국가** (도착국가번호(PK), 국가명)
- **예약** (예약번호(PK), 예약가격, 회원아이디(FK), 항공번호(FK))

<br />

## 결과
![6](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/17678ffe-12fe-4ffe-af70-524775ad64e8)

![7](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/c7cdb841-5804-4c8f-bd02-93695872650a)

![8](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/4ce7e435-09a0-4a65-afef-ec8cec0a49c7)

![9](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/331806fe-b65a-4f54-82f2-75934ea2afb9)

![10](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/211e9fd9-265f-4ba8-8234-715d51ed4f66)

![11](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/7b5f359c-897a-4cbf-b9a8-97c24b483019)

![12](https://github.com/na-kyoung/FlightReservation_Java/assets/137421820/89f7dd78-3a30-42fe-941c-9ef76f6bca1f)
