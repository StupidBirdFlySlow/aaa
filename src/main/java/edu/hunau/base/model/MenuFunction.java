package edu.hunau.base.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_sys_caipu_funcs")
public class MenuFunction {


	@Id
	@GenericGenerator(name = "myUUID", strategy = "uuid")
	@GeneratedValue(generator = "myUUID")
	private String id;
	private String func;
	private Integer sort;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // 延迟加载，只是查询时不查找，当用到时才查找---问题如果一直没用到就是null
	@JoinColumn(name = "cpId")
	private MenuDetails menuDetails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public MenuDetails getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(MenuDetails menuDetails) {
		this.menuDetails = menuDetails;
	}
	
	
}
