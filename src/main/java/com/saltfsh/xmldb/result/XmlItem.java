package com.saltfsh.xmldb.result;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlItem 
{
	private int id;
	private Map<String, String> fields;
	
	public XmlItem() 
	{
		fields = new TreeMap<String, String>();
	}
	
	public static XmlItem fromDom(Element dom) 
	{
		XmlItem xmlItem = new XmlItem();
		Iterator<Element> it = dom.elementIterator();
		while (it.hasNext()) {
			Element e = it.next();
			xmlItem.set(e.getName(), e.getText());
		}
		return xmlItem;
	}
	
	public Element toDom() 
	{
		Element item = DocumentHelper.createElement("ITEM");
		for (String key : fields.keySet()) {
			Element e = DocumentHelper.createElement(key);
			e.setText(fields.get(key));
		}
		return item;
	}
	
	public void appendTo(Element e) 
	{
		Element eOut = e.addElement("ITEM");
		for (String key : fields.keySet()) {
			Element eNew = eOut.addElement(key);
			eNew.setText(fields.get(key));
		}
	}
	
	public String get(String key) 
	{
		return fields.get(key);
	}

	public Map<String, String> getFields() 
	{
		return fields;
	}
	
	public void set(String key, String value) 
	{
		fields.put(key, value);
	}
	
	public String toString()
	{
		return fields.toString();
	}
}
