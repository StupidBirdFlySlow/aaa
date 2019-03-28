package edu.hunau.base.support;

import java.util.LinkedHashMap;
import java.util.List;

import edu.hunau.base.model.MenuDetails;

public class JsResult extends LinkedHashMap<Object,Object>{

	private static final long serialVersionUID = 1L;

	private Object item;
	
	private List<MenuDetails> items;
	
	public List<MenuDetails> getItems() {
		return items;
	}

	public void setItems(List<MenuDetails> items) {
		this.items = items;
	}

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.put("item", item);
	}
}
