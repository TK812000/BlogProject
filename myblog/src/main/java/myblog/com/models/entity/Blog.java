
package myblog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {

	// blog_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;

	// title
	private String title;

	// category
	private String category;

	// content
	private String content;

	// image
	private String image;

	// account_id
	private Long accountId;

	// 空のコンストラクタ
	private Blog() {

	}

	public Blog(String title, String category, String content, String image, Long accountId) {
		this.title = title;
		this.category = category;
		this.content = content;
		this.image = image;
		this.accountId = accountId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
