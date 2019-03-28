package edu.hunau.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.MaterialDao;
import edu.hunau.base.service.MaterialService;

@Service("meterialService")
@Transactional
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	@Qualifier("materialDao")
	private MaterialDao materialDao;

	public List<String> getCpIdByName(String name, int pageSize, int pageNum) {
		return materialDao.getCpIdByName(name, pageSize, pageNum);
	}

}
