package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;
import myblog.com.service.BlogService;

@Controller
public class BlogDeleteController {
	
	@Autowired
	private BlogService blogService;
//	セッションの宣言
	@Autowired
	private HttpSession session;

	@PostMapping("/blog/delete")
	public String blogDelete(@RequestParam Long blogId) {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//	アカウントがnullならログイン画面へ
//	そうでなければ削除処理
		if (account == null) {
			return "redirect:/login";
		} else {
//			trueを受け取ったら削除してブログ一覧画面へ
//			そうでないなら編集画面へ
			if (blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit/" + blogId;
			}
		}
	}
}
