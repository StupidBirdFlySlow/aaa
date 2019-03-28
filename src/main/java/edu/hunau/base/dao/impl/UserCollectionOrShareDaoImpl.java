package edu.hunau.base.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.UserCollectionOrShareDao;
import edu.hunau.base.model.UserCollectionOrShare;


@Repository("userCollectionOrShareDao")
public class UserCollectionOrShareDaoImpl extends BaseEntityDaoImpl implements UserCollectionOrShareDao {

	public void addRecords(UserCollectionOrShare entity) {
		this.getHibernateTemplate().save(entity);
	}

	public List<UserCollectionOrShare> getEntityListByUserId(String userId) {
		Session session = getSession();
		String hql = "from UserCollectionOrShare where userId=:userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		List<UserCollectionOrShare> list = (List<UserCollectionOrShare>) query.list();
		return list;
	}

	/**
	 * �����û�Id���ղر��еĻ�ȡ�û��ղػ����Ĳ��׵�����
	 */
	public int getTotalCountOfUsers(String userId,int type) {
		Session session= getSession();
		String sql="select count(*) from t_user_collectionOrShare_cp where userId=:userId and type= :type";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		Object uniqueResult = query.uniqueResult();
		int result = Integer.parseInt(uniqueResult.toString());
		return result;
	}

	/**
	 * �����ղر��еĲ���Id���õ������ղ���
	 */
	public int getCollectionCountByCpId(String cpId) {
		Session session= getSession();
		String sql="select count(*) from t_user_collectionOrShare_cp where cpId=:cpId and type='1'";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("cpId", cpId);
		Object uniqueResult = query.uniqueResult();
		int resut = Integer.parseInt(uniqueResult.toString());
		return resut;
	}

	/**
	 * ����û��ղصĲ��׵ı��
	 */
	public List<String> getCollectionCpIdsByUserId(String userId,int type) {
		Session session = getSession();
		String sql = "select cpId from t_user_collectionOrShare_cp where userId=:userId and type= :type";// 1��ʾ�ղ�
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		List<String> list = (List<String>) query.list();
		return list;
	}


	public void deleteEntityByUserIdAndCpId(String userId,String cpId) {
		Session session = getSession();
		String sql="delete from t_user_collectionOrShare_cp where userId=:userId and cpId=:cpId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("cpId", cpId);
		query.executeUpdate();
	}

	public List<UserCollectionOrShare> getUserCollectionOrShareList(String userId, int type) {
		Session session = getSession();
		String hql="from UserCollectionOrShare where userId=:userId and type=:type";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		List<UserCollectionOrShare> list= (List<UserCollectionOrShare>)query.list();
		return list;
	}

	public void deleteById(String id) {
		Session session = getSession();
		String sql="delete from t_user_collectionOrShare_cp  where id=:id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
