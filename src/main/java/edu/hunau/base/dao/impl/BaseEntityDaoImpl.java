package edu.hunau.base.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class BaseEntityDaoImpl extends HibernateDaoSupport {

	/*private E entity;
	private D daoClass;*/

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactoryBean(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public Session getSession() {
		Session session = this.currentSession();
		return session;
	}

	/*public E getEntityById(String id) {
		String hql="from :E where id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("E", entity);
		query.setParameter("id", id);
		return (E)query.uniqueResult();
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public D getDaoClass() {
		return daoClass;
	}

	public void setDaoClass(D daoClass) {
		this.daoClass = daoClass;
	}*/

}
