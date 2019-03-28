package edu.hunau.base.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.MaterialDao;

@Repository("materialDao")
public class MaterialDaoImpl extends BaseEntityDaoImpl implements MaterialDao{

	public List<String> getCpIdByName(String name,int pageSize,int pageNum) {
		Session session=getSession();
		String sql="select cpId from t_sys_caipu_materials where clmc=:clmc";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("clmc", name);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum-1)*pageSize);
		List<String> list = (List<String>)query.list();
		return list;
	}

	
}
