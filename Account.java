// 회원 클래스 (회원가입 / 로그인 / 로그아웃)

import database.AccountDAO;

public class Account {
	String account_id; // 아이디
	String account_pwd; // 비밀번호
	
	Account(){} // default
	
	// 생성자 (아이디, 비밀번호 출력)
	Account(String id, String pwd){
		this.account_id = id;
		this.account_pwd = pwd;
		System.out.println("✈︎ id : " + account_id + ", pwd : " + account_pwd);
	}
	
	@Override
	public String toString() {
		return "id : " + this.account_id + ", pwd : " + this.account_pwd;
	}
	
	// 로그인
	String Login (String id, String pwd) {
		AccountDAO adao = new AccountDAO();
		String result = adao.Login(id, pwd);
		return result;
	}
	
	// 회원가입
	void Join (String id, String pwd) {
		AccountDAO adao = new AccountDAO();
		adao.Join(id, pwd);
	}

}
