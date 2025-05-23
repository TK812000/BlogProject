package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;
import myblog.com.service.AccountService;

@Controller

public class AccountLoginController {
	// セッションの宣言
	@Autowired
	private HttpSession session;

	// サービスクラスの呼び出し
	@Autowired
	private AccountService accountService;

	// ログイン画面の表示
	@GetMapping("/login")
	public String getAccountLogin() {
		return "login.html";
	}

	// ログイン処理
	@PostMapping("/login/process")
	public String AccountloginController(@RequestParam String email, @RequestParam String password, Model model) {
	// 一致するアカウントが存在するか確認
		Account account = accountService.loginCheck(email, password);
	// accountがnullならログインページに飛ぶ
	// accountがあるならセッションを保持し記事一覧画面へ
		if (account == null) {
	// メールアドレス、パスワードが一致しなければエラー文を出す
			model.addAttribute("error", "メールアドレスまたはパスワードが違います");
			return "login.html";
		} else {
			session.setAttribute("loginAccountInfo", account);
			return "redirect:/blog/list";
		}
	}
}
