package myblog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.dao.BlogDao;
import myblog.com.models.entity.Account;
import myblog.com.models.entity.Blog;
import myblog.com.service.BlogService;

@Controller
public class BlogEditController {
	@Autowired
	private HttpSession session;
	@Autowired
	private BlogDao Blogdao;
	@Autowired
	private BlogService blogService;

//	画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditpage(@PathVariable Long blogId, Model model) {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//	データを取得して表示する情報を格納する
		Blog blog = blogService.blogEditCheck(blogId);
//	accountがnullならリダイレクト
//	そうでないなら編集画面へ
		if (account == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("blog", blog);
			model.addAttribute("userName", account.getUserName());
			return "blog_edit.html";
		}
	}
}
