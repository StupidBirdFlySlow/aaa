package edu.hunau.base.dao.impl;

import org.springframework.stereotype.Repository;

import edu.hunau.base.dao.MenuDetailsScanRecordsDao;
import edu.hunau.base.model.MenuDetailsScanRecords;

@Repository("menuDetailsScanRecordsDao")
public class MenuDetailsScanRecordsDaoImpl extends BaseEntityDaoImpl implements MenuDetailsScanRecordsDao{

	public void addRecord(MenuDetailsScanRecords entity) {
		this.getHibernateTemplate().save(entity);
	}

}
