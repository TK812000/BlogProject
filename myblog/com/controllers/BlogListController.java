package myblog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;
import myblog.com.models.entity.Blog;
import myblog.com.service.BlogService;

@Controller
public class BlogListController {
//	セッションの呼び出し
	@Autowired
	private HttpSession session;
//	サービスクラスの呼び出し
	@Autowired
	private BlogService blogService;

//	ブログ一覧を画面に表示
	@GetMapping("/blog/list")
	public String getBlogListPage(Model model) {
//		sessionの保持
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		セッションが切れていたらログイン画面へ
//		そうでなければ商品の情報を取得して表示
		if (account == null) {
			return "redirect:/login";
		} else {
			List<Blog> blogList = blogService.selectAllBloglist(account.getAccountId());
//			ユーザーネームを渡す
			model.addAttribute("userName", account.getUserName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
}
