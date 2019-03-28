package edu.hunau.base.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.ProvinceAndCityDao;
import edu.hunau.base.model.ProvinceAndCity;

@Repository("provinceAndCityDao")
public class ProvinceAndCityDaoImpl extends BaseEntityDaoImpl implements ProvinceAndCityDao {

	public void addRecords(ProvinceAndCity entity) {
		this.getHibernateTemplate().save(entity);
	}

	public List<String> getProvinceList() {
		Session session = getSession();
		String sql = "select distinct province from province_and_city";
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = (List<String>) query.list();
		return list;
	}

	public List<String> getCityListByProvince(String province) {
		Session session = getSession();
		String sql = "select city from ProvinceAndCity where province=:province";
		Query query = session.createQuery(sql);
		query.setParameter("province", province);
		List<String> list = (List<String>) query.list();
		return list;
	}

}
