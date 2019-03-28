package edu.hunau.base.dao;

import java.util.List;

public interface MaterialDao {

	public List<String> getCpIdByName(String name,int pageSize,int pageNum);
}
