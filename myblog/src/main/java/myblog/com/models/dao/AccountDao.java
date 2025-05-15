package myblog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import myblog.com.models.entity.Account;

public interface AccountDao extends JpaRepository<Account, Long> {
	//保存、更新処理
	Account save(Account account);
	
	//重複するemailがないか探す
	Account findByEmail(String email);
	
	//ログイン処理
	//emailとpassword両方一致するものを探す
	Account findByEmailAndPassword(String email, String password);
}
