package com.saltfsh.xmldb.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.dom4j.Document;

import com.saltfsh.xmldb.result.XmlItem;

public class DefaultDriver extends AbstractDriver {

	public DefaultDriver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(XmlItem item) {
		List<XmlItem> list = getItemList();
		list.add(item);
		return list.size();
	}

	@Override
	public List<XmlItem> getAll(String key, String val) {
		List<XmlItem> result = new ArrayList<XmlItem>();
		for (XmlItem i : getItemList()) {
			if (val.equals(i.get(key))) {
				result.add(i);
			}
		}
		return result;
	}

	@Override
	public int update(String key, String value, XmlItem item) {
		List<XmlItem> l = getItemList();
		int count = 0;
		for (int idx = 0; idx < l.size(); idx++) {
			XmlItem i = l.get(idx);
			if (value.equals(i.get(key))) {
				l.set(idx, item);
			}
		}
		return count;
	}

	@Override
	public int delete(String key, String value) {
		List<XmlItem> l = getItemList();
		int count = 0;
		ListIterator<XmlItem> listIt = l.listIterator();
		while (listIt.hasNext()) {
			XmlItem i = listIt.next();
			if (value.equals(i.get(key))) {
				//TODO
				listIt.remove();
				count++;
			}
		}
		return count;
	}
}
