package edu.hunau.base.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.ShiCaiBaiKeDao;
import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.support.PageInfo;

@Repository("shiCaiBaiKeDao")
public class ShiCaiBaiKeDaoImpl extends BaseEntityDaoImpl implements ShiCaiBaiKeDao {

	public void addShiCaiBaiKe(ShiCaiBaiKe entity) {
		this.getHibernateTemplate().save(entity);
	}

	public ShiCaiBaiKe getEntityById(String id) {
		Session session = getSession();
		String hql = "from ShiCaiBaiKe where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ShiCaiBaiKe result = (ShiCaiBaiKe) query.uniqueResult();
		return result;
	}

	public List<ShiCaiBaiKe> getEntityList(int pageNum, int pageSize,String type) {
		Session session = getSession();
		StringBuilder hql =new StringBuilder("from ShiCaiBaiKe");
		if(type!=null){
			hql.append(" where type_short=:type order by content desc");
		}
		Query query = session.createQuery(hql.toString());
		if(type!=null){
			query.setParameter("type", type);
		}
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum - 1) * pageSize);
		List<ShiCaiBaiKe> list = (List<ShiCaiBaiKe>) query.list();
		return list;
	}

	public int getTotalCount(String type) {
		Session session = getSession();
		StringBuilder sql=new StringBuilder("select count(*) from t_sys_scbk");
		if(type!=null){
			sql.append(" where type_short=:type");
		}
		SQLQuery query = session.createSQLQuery(sql.toString());
		if(type!=null){
			query.setParameter("type", type);
		}
		Object result = query.uniqueResult();
		int totalCount = Integer.parseInt(result.toString());
		return totalCount;
	}
}
