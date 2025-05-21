package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogLogoutController {
//	セッションの呼び出し
	@Autowired
	private HttpSession session;

	@GetMapping("/blog/logout")
//	セッションを無効化してログイン画面へ遷移
	public String blogLogout() {
		session.invalidate();
		return "redirect:/login";
	}
}
