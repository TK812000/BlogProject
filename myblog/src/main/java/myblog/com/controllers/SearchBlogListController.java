package myblog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;
import myblog.com.models.entity.Blog;
import myblog.com.service.BlogService;

@Controller
public class SearchBlogListController {
//	セッション呼び出し
	@Autowired
	private HttpSession session;

//	クラス呼び出し
	@Autowired
	private BlogService blogService;

//	検索結果画面表示
	@GetMapping("/blog/search")
	public String getSearchBlogPage(String keyword, Model model) {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		List<Blog> blogList;
		// アカウントがnullならログイン画面へ
//		そうでなければ検索結果を表示
		if (account == null) {
			return "redirect:/login";
		} else {
			// 検索が空でないなら部分一致検索	
			if (keyword != null && !keyword.isEmpty()) {
				blogList = blogService.searchBlogList(account.getAccountId(), keyword);
			// 空なら全記事取得
			} else {
				blogList = blogService.selectAllBloglist(account.getAccountId());
			}
			// ユーザーネームを渡す
			model.addAttribute("userName", account.getUserName());
			// 検索結果のブログ記事を渡す
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}

	// カテゴリーを押したときに同じカテゴリーが表示されるようにする
	@GetMapping("/blog/category")
	public String getBlogsByCategory(@RequestParam("name") String category, Model model) {
		// sessionの保持
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// セッションが切れていたらログイン画面へ
		// そうでなければ商品の情報を取得して表示
		if (account == null) {
			return "redirect:/login";
		} else {
			List<Blog> blogList = blogService.searchBlogCategory(category);
			// ユーザーネームを渡す
			model.addAttribute("userName", account.getUserName());
			// 検索結果を渡す
			model.addAttribute("blogList", blogList);
			return "blog_list";
		}
	}
}
