// 회원 관련 DB 멤버 (account)

package database;

public class AccountDTO {
	private String account_id;
	private String account_pwd;
	
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getAccount_pwd() {
		return account_pwd;
	}
	public void setAccount_pwd(String account_pwd) {
		this.account_pwd = account_pwd;
	}
	

}
