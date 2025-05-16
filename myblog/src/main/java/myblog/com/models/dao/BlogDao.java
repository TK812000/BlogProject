package myblog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myblog.com.models.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Long> {
	// 保存、更新処理
	Blog save(Blog blog);

//	同じタイトル名は表示しない
	Blog findByTitle(String title);
	// 編集画面の表示
	Blog findByBlogId(Long blogId);

	// ブログの削除
	void deleteByBlogId(Long blogId);

	// 一覧の表示
	List<Blog> findAll();

}
