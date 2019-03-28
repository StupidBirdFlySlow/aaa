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
@Table(name="t_sys_caipu_steps")
public class Steps {

	@Id
	@GenericGenerator(name = "myUUID", strategy = "uuid")
	@GeneratedValue(generator = "myUUID")
	private String id;
	private Integer step_sort;
	private String step_desc;
	private String step_img;
	private String step_img_name;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "cpId")
	private MenuDetails menuDetails;

	public Steps() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStep_sort() {
		return step_sort;
	}

	public void setStep_sort(Integer step_sort) {
		this.step_sort = step_sort;
	}

	public String getStep_desc() {
		return step_desc;
	}

	public void setStep_desc(String step_desc) {
		this.step_desc = step_desc;
	}

	public String getStep_img() {
		return step_img;
	}

	public void setStep_img(String step_img) {
		this.step_img = step_img;
	}

	public String getStep_img_name() {
		return step_img_name;
	}

	public void setStep_img_name(String step_img_name) {
		this.step_img_name = step_img_name;
	}

	public MenuDetails getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(MenuDetails menuDetails) {
		this.menuDetails = menuDetails;
	}

}
