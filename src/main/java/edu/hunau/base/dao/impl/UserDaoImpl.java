package edu.hunau.base.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.UserDao;
import edu.hunau.base.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseEntityDaoImpl implements UserDao {

	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	public void deleteUserById(String id) {
	}

	public void updateUser(User user) {
		Session session = getSession();
		session.update(user);
	}

	public User queryUserById(String id) {
		Session session = getSession();
		String hql="from User where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		User user =(User) query.uniqueResult();
		return user;
	}

	public boolean checkEmail(String email) {
		Session session = getSession();
		String hql="select email from User where email=:email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		Object result = query.uniqueResult();
		if(result!=null&&result!=""){
			return true;
		}
		return false;
	}

	public boolean checkPhone(String phone) {
		Session session = getSession();
		String hql="select phone from User where phone=:phone";
		Query query = session.createQuery(hql);
		query.setParameter("phone", phone);
		Object result = query.uniqueResult();
		System.out.println(result);
		if(result!=null&&result!=""){
			return true;
		}
		return false;
	}

	public User getUser(User user) {
		Session session = getSession();
		String hql="from User where username=:username and pwd=:pwd";
		Query query = session.createQuery(hql);
		query.setParameter("username", user.getUsername());
		query.setParameter("pwd", user.getPwd());
		User result =(User) query.uniqueResult();
		return result;
	}

	public List<User> getEntityList(int pageNum, int pageSize) {
		Session session = getSession();
		String hql="from User";
		Query query = session.createQuery(hql);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum-1)*pageSize);
		List<User> list = (List<User>)query.list();
		return list;
	}

	public int getTotalCountOfUser() {
		Session session = getSession();
		String sql= "select count(*) from t_sys_user";
		SQLQuery query = session.createSQLQuery(sql);
		Object uniqueResult = query.uniqueResult();
		int result = Integer.parseInt(uniqueResult.toString());
		return result;
	}

}
