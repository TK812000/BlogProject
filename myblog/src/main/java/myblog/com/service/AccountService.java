package myblog.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myblog.com.models.dao.AccountDao;
import myblog.com.models.entity.Account;

@Service
public class AccountService {
//	Daoクラスの呼び出し
	@Autowired
	private AccountDao accountDao;

//	保存、登録処理
	public Boolean createAccount(String userName, String email, String password) {
		// もしemailが存在しないなら保存をしてtrueを返す
		if (accountDao.findByEmail(email) == null) {
			accountDao.save(new Account(userName, email, password));
			return true;
//		emailが存在する場合falseを返す
		} else {
			return false;
		}
	}

//	ログイン処理
//	メールアドレスとパスワードが一致するアカウントがあればaccountを返す
	public Account loginCheck(String email, String password) {
		Account account = accountDao.findByEmailAndPassword(email, password);
		// アカウントが存在しなければnullを返す
		if (account == null) {
			return null;
		} else {
			return account;
		}
	}

}
