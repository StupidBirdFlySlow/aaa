package edu.hunau.base.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.MenuDetailsDao;
import edu.hunau.base.model.MenuDetails;

@Repository("menuDetailsDao")
public class MenuDetailsDaoImpl extends BaseEntityDaoImpl implements MenuDetailsDao {

	/*
	 * private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 */

	public void addMenuDetails(MenuDetails menuDetails) {
		this.getHibernateTemplate().save(menuDetails);
	}

	public MenuDetails getEntityById(String id) {
		Session session = getSession();
		String hql = "from MenuDetails where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		MenuDetails result = (MenuDetails) query.uniqueResult();
		return result;
	}

	/**
	 * 首次加载时调用该方法，获取数据信息，测试版本
	 */
	public List<MenuDetails> getEntityList(int pageNum, int pageSize, String type) {
		List<MenuDetails> list = new ArrayList<MenuDetails>();
		StringBuilder hql = new StringBuilder("from MenuDetails");
		if (type != null) {
			hql.append(" where type_short=:type");
		}
		Session session = getSession();
		Query query = session.createQuery(hql.toString());
		if (type != null) {
			query.setParameter("type", type);
		}
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum - 1) * pageSize);
		list = (List<MenuDetails>) query.list();
		return list;
	}

	public int getTotalRecords(String type) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("select count(*) from t_sys_caipu");
		if (type != null) {
			sql.append(" where type_short=:type");
		}
		SQLQuery query = session.createSQLQuery(sql.toString());
		if (type != null) {
			query.setParameter("type", type);
		}
		Object result = query.uniqueResult();
		int totalCount = Integer.parseInt(result.toString());
		return totalCount;
	}

	/**
	 * 获取最新的菜谱
	 */
	public List<MenuDetails> getMostNewRecipes(int pageSize, int pageNum) {
		String hql = "from MenuDetails order by createTime desc";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum - 1) * pageSize);
		List<MenuDetails> list = (List<MenuDetails>) query.list();
		return list;
	}

	/**
	 * 获得本星期内最热的菜谱
	 */
	public List<MenuDetails> getMostHotRecipesOfThisWeek(Date date, int pageSize, int pageNum) {
		Date firstDayOfWeek = getFirstDayOfWeek(date);
		Date lastDayOfWeek = getLastDayOfWeek(date);
		Session session = getSession();
		String hql = "from MenuDetails where createTime BETWEEN :begin And :end order by clickCount desc";
		Query query = session.createQuery(hql);
		query.setParameter("begin", firstDayOfWeek);
		query.setParameter("end", lastDayOfWeek);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum - 1) * pageSize);
		List<MenuDetails> list = (List<MenuDetails>) query.list();
		return list;
	}

	/**
	 * 获得本月最热的菜谱
	 */
	public List<MenuDetails> getMostHotRecipesOfThisMonth(Date date, int pageSize, int pageNum) {
		Date firstDayOfWeek = getFirstDayOfMonth(date);
		Date lastDayOfWeek = getLastDayOfMonth(date);
		Session session = getSession();
		String hql = "from MenuDetails where createTime BETWEEN :begin And :end order by clickCount desc";
		Query query = session.createQuery(hql);
		query.setParameter("begin", firstDayOfWeek);
		query.setParameter("end", lastDayOfWeek);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum - 1) * pageSize);
		List<MenuDetails> list = (List<MenuDetails>) query.list();
		return list;
	}

	private Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		Date time = calendar.getTime();
		return time;
	}

	private Date getLastDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		Date time = calendar.getTime();
		return time;
	}

	private Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int minimum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, minimum);
		Date time = calendar.getTime();
		return time;
	}

	private Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, maximum);
		Date time = calendar.getTime();
		return time;
	}

	/**
	 * 更新菜谱的浏览量
	 */
	public void updateRecipesClickcount(int clickCount ,String id) {
		Session session = getSession();
		String sql="update t_sys_caipu set clickCount=:clickCount where id=:id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("clickCount", clickCount);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public List<String> getRecipesNameByKeywords(String keywords) {
		Session session = getSession();
		String sql="select cpname from t_sys_caipu where cpname like :keywords";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("keywords", "%"+keywords+"%");
		query.setMaxResults(10);
		query.setFirstResult(0);
		List<String> list = (List<String>)query.list();
		return list;
	}

	public List<String> getRecipesIdByKeywords(String keywords) {
		Session session = getSession();
		String sql="select id from t_sys_caipu where cpname like :keywords";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("keywords", "%"+keywords+"%");
		query.setMaxResults(10);
		query.setFirstResult(0);
		List<String> list = (List<String>)query.list();
		return list;
	}

	public void updateMenuDetails(MenuDetails menuDetails) {
		Session session = getSession();
		session.update(menuDetails);
	}

	
	
}
