package com.saltfsh.xmldb.driver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.XMLFormatter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.saltfsh.xmldb.result.XmlItem;

public abstract class AbstractDriver {
	private List<XmlItem> itemList;
	
	public void parseDom(Document dom) 
	{
		itemList = parseRoot(dom);
	}
	
	private List<XmlItem> parseRoot(Document domRoot) 
	{
		List<XmlItem> list = new ArrayList<XmlItem>();
		Element root = domRoot.getRootElement();
		Iterator<Element> it = root.elementIterator("ITEM");
		
		while (it.hasNext()) {
			list.add(XmlItem.fromDom(it.next()));
		}
		
		return list;
	}
	
	public List<XmlItem> getItemList() {
		return itemList;
	}
	
	public void store(File file) {
		Document dom = DocumentHelper.createDocument();
		Element rootEle = dom.addElement("DATA");
		for (XmlItem i : itemList) {
			//System.out.println("write : " + i);
			i.appendTo(rootEle);
		}
		
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(file), OutputFormat.createPrettyPrint());
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract int insert(XmlItem item);
	public abstract List<XmlItem> getAll(String key, String val);
	public abstract int update(String key, String value, XmlItem item);
	public abstract int delete(String key, String value);
}
