package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;

@Controller
public class ProfileController {
//	セッションの宣言
	@Autowired
	private HttpSession session;
	
//	プロフィール画面の表示
	@GetMapping("/blog/profile")
	public String getProfilePage(Model model) {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		accountがNULLならログイン画面へ遷移
//		そうでないなら名前を取得してプロフィール画面へ
		if(account == null) {
//			ログイン画面へ
		return "redirect:/login";
		}else {
//			ユーザーネームを渡す
			model.addAttribute("userName", account.getUserName());
//			プロフィール画面へ
			return "myprofile.html";
		}
	}
}
