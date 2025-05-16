package myblog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import myblog.com.models.entity.Account;
import myblog.com.service.BlogService;

@Controller
public class BlogRegisterController {
	@Autowired
	private HttpSession session;
	@Autowired
	private BlogService blogService;

//ブログ登録画面の表示
	@GetMapping("/blog/register")
	private String getBlogRegsterPage(Model model) {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		accountがNULLならログイン画面へ遷移
//		そうでないなら名前を取得して登録画面へ
		if (account == null) {
			return "redirect:/login";
		} else {
//			ログイン名を表示するのに名前を取得
			model.addAttribute("userName", account.getUserName());
			return "blog_register.html";
		}
	}

//	商品の登録
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String title, @RequestParam String category,
			@RequestParam String content, @RequestParam MultipartFile image) {
//		ログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		account ==nullならリダイレクト
//		そうでなければファイル名取得と画像のアップロード
		if (account == null) {
			return "redirect:/login";
		} else {
//			ファイル名の取得
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ image.getOriginalFilename();
//			ファイルの保存
			try {
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/img/" + fileName));
//			エラーが発生しても止めずに、エラー処理
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			ブログを作ることができればブログ一覧画面へ
//			そうでなければアカウント作成画面へ
			if (blogService.createBlog(title, category, content, fileName, account.getAccountId())) {
				return "redirect:/blog/list";
			}else {
				return "/register.html";
			}
		}
	}
//	更新処理をする
	@PostMapping
	("/blog/edit/process")
	public String blogUpdate(@RequestParam String title, 
			@RequestParam String category,
			@RequestParam String content, 
			@RequestParam MultipartFile image,
			@RequestParam Long blogId) {
		// セッションからログインしている人をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		accountがNULLならリダイレクト
//		そうでなければ更新処理を進める
		if(account ==null) {
			return "redirect:/login";
		}else {
//			画像の保存処理
//			ファイル名の取得
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ image.getOriginalFilename();
//			ファイルの保存
			try {
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/img/" + fileName));
//			エラーが発生しても止めずに、エラー処理
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//もし更新に成功していれば記事一覧へ遷移
//			そうでなければその画面にとどまる
			if(blogService.blogUpdate(blogId,title,category,fileName,content,account.getAccountId())){
				return "redirect:/blog/list";
			}else {
				return"redirect:/blog/edit/"+blogId;
			}
		}
	}
}
