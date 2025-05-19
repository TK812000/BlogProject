package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myblog.com.service.AccountService;

@Controller
public class AccountRegisterController {
	@Autowired
	private AccountService accountService;

//	アカウント登録画面の表示
	@GetMapping("/register")
	public String GetRegisterPage() {
		return "/register.html";
	}

// 登録処理
//	成功したらログインページに飛ぶ
//	失敗したら登録ページに飛ぶ
	@PostMapping("/register/process")
	public String accountRegisterProcess(@RequestParam String userName, @RequestParam String email,
			@RequestParam String password, Model model) {
		if (accountService.createAccount(userName, email, password)) {
			return "login.html";
		} else {
			model.addAttribute("error", "メールアドレスまたはパスワードが違います");
			return "/register.html";
		}
	}
}
