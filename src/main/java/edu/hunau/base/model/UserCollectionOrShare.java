package edu.hunau.base.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_user_collectionOrShare_cp")
public class UserCollectionOrShare {


	@Id
	@GenericGenerator(name = "myUUID", strategy = "uuid")
	@GeneratedValue(generator = "myUUID")
	private String id;
	private String userId;
	private String username;
	private String cpId;
	private String cpname;
	private Date createTime;
	private Integer type;//1表示收藏   0表示分享
	private Integer clickCount;//菜谱点击量
	private String cpImg_url;
	private String cpImg_name;

	public UserCollectionOrShare() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getCpImg_url() {
		return cpImg_url;
	}

	public void setCpImg_url(String cpImg_url) {
		this.cpImg_url = cpImg_url;
	}

	public String getCpImg_name() {
		return cpImg_name;
	}

	public void setCpImg_name(String cpImg_name) {
		this.cpImg_name = cpImg_name;
	}

}
