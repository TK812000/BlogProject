package myblog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
	// account_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;

//	username
	private String userName;

//	password
	private String email;

//	email
	private String password;

//	空のコンストラクタ
	public Account() {
	}

	public Account(String userName, String email, String password) {
		this.password = password;
		this.userName = userName;
		this.email = email;
	}

//	getter,setterの設定
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
