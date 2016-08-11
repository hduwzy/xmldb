package com.saltfsh.xmldb;

import java.io.*;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.saltfsh.xmldb.driver.AbstractDriver;
import com.saltfsh.xmldb.driver.DefaultDriver;
import com.saltfsh.xmldb.result.XmlItem;

public class Xmldb 
{
	private AbstractDriver driver;
	private File file;
	
	public static Xmldb open(File file) 
	throws FileNotFoundException
	{
		return open(file, new DefaultDriver()); 
	}
	
	public static Xmldb open(File file, AbstractDriver driver) 
	throws FileNotFoundException
	{
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		
		return new Xmldb(file, driver);
	}
	
	public void close() {
		this.driver.store(file);
	}
	
	private Xmldb(File file, AbstractDriver driver) 
	{
		this.driver = driver;
		this.file = file;
		SAXReader saxReader = new SAXReader();
		try {
			Document dom = saxReader.read(file);
			this.driver.parseDom(dom);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<XmlItem> getAll(String key, String value) 
	{
		return this.driver.getAll(key, value);
	}
	
	public List<XmlItem> getAll() 
	{
		return this.driver.getItemList();
	}
	
	public int insert(XmlItem item) 
	{
		return this.driver.insert(item);
	}
	
	public int update(String key, String value, XmlItem item)
	{
		return this.driver.update(key, value, item);
	}
	
	public int delete(String key, String value)
	{
		return this.driver.delete(key, value);
	}
}
