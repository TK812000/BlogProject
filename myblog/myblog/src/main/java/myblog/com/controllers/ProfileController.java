package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;

@Controller
public class ProfileController {
	@Autowired
	private HttpSession session;
	
	@GetMapping("/blog/profile")
	public String getProfilePage() {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		accountがNULLならログイン画面へ遷移
//		そうでないなら名前を取得してプロフィール画面へ
		if(account == null) {
		return "redirect:/login";
		}else {
			return "myprofile.html";
		}
	}
}
