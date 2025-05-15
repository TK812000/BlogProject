package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;

@Controller
public class BlogListController {

	@Autowired
	private HttpSession session;

	@GetMapping("/blog/list")
	public String getBlogListPage(Model model) {
//		sessionの保持
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		セッションが切れていたらログイン画面へ
		if (account == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("userName", account.getUserName());
			return "blog_list.html";
		}
	}
}
