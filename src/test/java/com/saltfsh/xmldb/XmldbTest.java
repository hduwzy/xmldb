package com.saltfsh.xmldb;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import com.saltfsh.xmldb.result.XmlItem;

public class XmldbTest 
{
	@Test
	public void test()
	throws FileNotFoundException
	{
		File file = new File("./src/test/resources/student.xml");
    	try {
			Xmldb db = Xmldb.open(file);
			XmlItem item = new XmlItem();
			item.set("name", "Lily");
			item.set("age", "17");
			item.set("sex", "female");
			db.insert(item);
			db.close();
			dumpXmldb(file);
			System.out.println();
			
			db = Xmldb.open(file);
			item = new XmlItem();
			item.set("name", "Tom");
			item.set("age", "26");
			item.set("sex", "male");
			db.insert(item);
			db.close();
			dumpXmldb(file);
			System.out.println();
			
			db = Xmldb.open(file);
			List<XmlItem> result = db.getAll("name", "Lily");
			for (XmlItem i : result) {
				System.out.println("select result: " + i);
			}
			db.close();
			System.out.println();
			
			db = Xmldb.open(file);
			item = new XmlItem();
			item.set("name", "qqq");
			item.set("age", "111");
			item.set("sex", "female");
			db.update("name", "Tom", item);
			db.close();
			dumpXmldb(file);
			System.out.println();
			
			db = Xmldb.open(file);
			db.delete("name", "Lily");
			db.close();
			dumpXmldb(file);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	private void dumpXmldb(File file) {
		try {
			Xmldb db = Xmldb.open(file);
			for (XmlItem i : db.getAll()) {
				System.out.println(i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
