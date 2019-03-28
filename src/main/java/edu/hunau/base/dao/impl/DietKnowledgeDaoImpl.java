package edu.hunau.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.DietKnowledgeDao;
import edu.hunau.base.model.DietKnowledge;

@Repository("dietKnowledgeDao")
public class DietKnowledgeDaoImpl extends BaseEntityDaoImpl implements DietKnowledgeDao {

	public void addDietKnowledge(DietKnowledge entity) {
		this.getHibernateTemplate().save(entity);
	}

	public DietKnowledge getEntityById(String id) {
		Session session = getSession();
		String hql = "from DietKnowledge where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		DietKnowledge result = (DietKnowledge) query.uniqueResult();
		return result;
	}

	
	/**
	 * 获取饮食知识的数据，简单版，待Page
	 * @return
	 */
	public List<DietKnowledge> getEntityList(int pageSize,int pageNum,String type){
		List<DietKnowledge> list=new ArrayList<DietKnowledge>();
		StringBuilder hql= new StringBuilder("from DietKnowledge");
		if(type!=null){
			hql.append(" where type_short=:type");
		}
		Session session = getSession();
		Query query = session.createQuery(hql.toString());
		if(type!=null){
			query.setParameter("type", type);
		}
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum-1)*pageSize);
		list =(List<DietKnowledge>) query.list();
		return list;
	}

	public int getTotalCount(String type) {
		Session session=getSession();
		StringBuilder sql=new StringBuilder("select count(*) from t_sys_dietknowledge");
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
