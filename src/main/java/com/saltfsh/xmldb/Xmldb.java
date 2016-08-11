package com.saltfsh.xmldb;

import java.io.*;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class Xmldb 
{
	private Document dom;
	
	public static Xmldb open(File file) 
	throws FileNotFoundException
	{
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		
		return new Xmldb(file);
	}
	
	public Xmldb(File file) {
		
	}
}
