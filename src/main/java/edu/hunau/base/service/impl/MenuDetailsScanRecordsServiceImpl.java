package edu.hunau.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.MenuDetailsScanRecordsDao;
import edu.hunau.base.model.MenuDetailsScanRecords;
import edu.hunau.base.service.MenuDetailsScanRecordsService;

@Service("menuDetailsScanRecordsService")
@Transactional
public class MenuDetailsScanRecordsServiceImpl implements MenuDetailsScanRecordsService {

	@Autowired
	@Qualifier("menuDetailsScanRecordsDao")
	private MenuDetailsScanRecordsDao menuDetailsScanRecordsDao;
	
	
	public void addRecord(MenuDetailsScanRecords entity) {
		menuDetailsScanRecordsDao.addRecord(entity);
	}

}
