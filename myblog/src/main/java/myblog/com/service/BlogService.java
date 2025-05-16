package myblog.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import myblog.com.models.dao.BlogDao;
import myblog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

//	ブログの一覧チェック
	public List<Blog> selectAllBloglist(Long accountId) {
//			もしアカウントIDが存在しなければnull
//		そうでなければ一覧を表示
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

//	商品の登録処理
	public boolean createBlog(String title, String category, String content, String image, Long accountId) {
//		タイトルが重複していたらnull
//		タイトルが重複していなければ登録処理をしてtrueを返す
		if (blogDao.findByTitle(title) == null) {
			blogDao.save(new Blog(title, category, content, image, accountId));
			return true;
		} else {
			return false;
		}
	}

//	編集画面の表示チェック
//	blogIdが存在しないならnull
//	そうでないならblogIdを返す
	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

//		更新処理のチェック
//	blogIdがnullなら更新はしない(false)
//	そうでない場合、更新処理(true)
	public boolean blogUpdate(Long blogId, String title, String category, String image, String content,
			Long accountId) {
		
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
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}

}
