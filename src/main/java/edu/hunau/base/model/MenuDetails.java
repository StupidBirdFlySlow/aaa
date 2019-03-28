package edu.hunau.base.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜谱实体
 * 
 * @author leim
 *
 */
@Entity
@Table(name="t_sys_caipu")
public class MenuDetails{

	@Id
	@GenericGenerator(name = "myUUID", strategy = "uuid")
	@GeneratedValue(generator = "myUUID")
	private String id;
	
	private String cpname;
	private String gy;// 工艺
	private String nd;// 难度
	private String ycrs;// 用餐人数
	private String prsj;// 烹饪时间
	private String zbsj;// 准备时间
	private String kw;// 口味
	private String func;// 功能
	private String img_name;
	private String img_src;
	private String descr;// 菜谱描述
	private String userId;
	private String username;
	private Date createTime;
	private Date modifyTime;
	private Integer status;
	private Integer clickCount;// 点击量
	private String type;// 分类
	private String type_short;// 分类简写
	
	@JsonIgnore
	@OneToMany(mappedBy = "menuDetails",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER) // 表明由Materials维护关联关系
	private List<Materials> materials;
	@JsonIgnore
	@OneToMany(mappedBy = "menuDetails",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER) // 表明由Step维护关联关系
	private List<Steps> steps;
	@JsonIgnore
	@OneToMany(mappedBy = "menuDetails",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER) // 表明由Step维护关联关系
	private List<MenuFunction> funcs;
	
	public MenuDetails() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	public String getGy() {
		return gy;
	}

	public void setGy(String gy) {
		this.gy = gy;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getYcrs() {
		return ycrs;
	}

	public void setYcrs(String ycrs) {
		this.ycrs = ycrs;
	}

	public String getPrsj() {
		return prsj;
	}

	public void setPrsj(String prsj) {
		this.prsj = prsj;
	}

	public String getZbsj() {
		return zbsj;
	}

	public void setZbsj(String zbsj) {
		this.zbsj = zbsj;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_short() {
		return type_short;
	}

	public void setType_short(String type_short) {
		this.type_short = type_short;
	}

	public List<Materials> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Materials> materials) {
		this.materials = materials;
	}

	public List<Steps> getSteps() {
		return steps;
	}

	public void setSteps(List<Steps> steps) {
		this.steps = steps;
	}

	public List<MenuFunction> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<MenuFunction> funcs) {
		this.funcs = funcs;
	}

	@Override
	public String toString() {
		return "{cpname:'" + cpname + "', gy:'" + gy + "', nd:'" + nd + "', prsj:'" + prsj + "', zbsj:'" + zbsj
				+ "', kw'" + kw + "', username:'" + username + "', clickCount:'" + clickCount + "', type:'" + type + "'}";
	}

}
