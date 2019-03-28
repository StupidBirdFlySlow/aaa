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
@Table(name = "t_sys_caipu_materials")
public class Materials {
	
	@Id
	@GenericGenerator(name = "myUUID", strategy = "uuid")
	@GeneratedValue(generator = "myUUID")
	private String id;
	private String clmc;// ��������
	private Integer type;// ��� 0��ʾ���� 1��ʾ����
	private String imgName;// ͼƬ��
	private String imgSrc;// ͼƬ·��
	private String quantity;// ����
	private Integer sort;// ��ʾ˳��

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // �ӳټ��أ�ֻ�ǲ�ѯʱ�����ң����õ�ʱ�Ų���---�������һֱû�õ�����null
	@JoinColumn(name = "cpId")
	private MenuDetails menuDetails;
	
	public Materials() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClmc() {
		return clmc;
	}

	public void setClmc(String clmc) {
		this.clmc = clmc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
