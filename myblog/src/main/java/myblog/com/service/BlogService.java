package myblog.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myblog.com.models.dao.BlogDao;
import myblog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
//	商品の登録処理
	public boolean createBlog(String title,
			String category,
			String content,
			String image,
			Long accountId ) {
//		登録処理をしてtrueを返す
		blogDao.save(new Blog(title, category, content, image, accountId));
		return true;
	}
}
