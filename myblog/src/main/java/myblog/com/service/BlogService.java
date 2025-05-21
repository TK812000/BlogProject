package myblog.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.transaction.Transactional;
import myblog.com.models.dao.BlogDao;
import myblog.com.models.entity.Blog;

@Service
public class BlogService {
//	daoクラスの呼び出し
	@Autowired
	private BlogDao blogDao;

//	ブログの一覧チェック
	public List<Blog> selectAllBloglist(Long accountId) {
//		もしアカウントIDが存在しなければnullを返す
//		そうでなければ一覧を表示
//		表示するブログは自分のアカウントのみにする
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findByAccountId(accountId);
		}
	}

//	ブログの登録処理
	public boolean createBlog(String title, String category, String content, String image, Long accountId) {
//		タイトルが重複していたらfalse
//		タイトルが重複していなければ登録処理をしてtrueを返す
		if (blogDao.findByTitle(title) == null) {
			blogDao.save(new Blog(title, category, content, image, accountId));
			return true;
		} else {
			return false;
		}
	}

//	編集画面の表示チェック
	public Blog blogEditCheck(Long blogId) {
//		blogIdが存在しないならnull
//		そうでないならblogIdを返す
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

//	更新処理のチェック
	public boolean blogUpdate(Long blogId, String title, String category, String image,
							String content, Long accountId) {
//		blogIdがnullなら更新はしない(false)
//		そうでない場合、更新処理(true)
		if (blogId == null) {
			return false;
		} else {
//			一致するblogIdを探して項目を更新する
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setTitle(title);
			blog.setCategory(category);
			blog.setContent(content);
			blog.setImage(image);
			blogDao.save(blog);
			return true;
		}
	}

//	削除処理
//	productIdを受け取ってnullならfalse
//	そうでないなら削除（true）
	@Transactional
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}

//	検索処理
	public List<Blog> searchBlogList(Long accountId, String keyword) {
//		タイトルにキーワードを含むブログ記事を検索
		return blogDao.findByAccountIdAndTitleContaining(accountId, keyword);
	}
}
