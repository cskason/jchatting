package com.jchatting.db.persistent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class DbXmlParseUtil {
	private String XML_PATH = "res/dbconfig.xml";
	public DbXmlParseUtil() {
	}
	
	public DbConfig parseConnectXml() {
		System.out.println("Parse DbConnect Xml");
		DbConfig connectBean = new DbConfig();
		
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(XML_PATH));
			
			Element databaseInfo = document.getRootElement();
			Element driverElement = databaseInfo.element("driver");
			connectBean.setDriver(driverElement.getText());
			Element urlElement = databaseInfo.element("url");
			connectBean.setUrl(urlElement.getText());
		 	Element usernameElement = databaseInfo.element("user");
		 	connectBean.setUserName(usernameElement.getText());
		 	Element passwordElement = databaseInfo.element("password");
		 	connectBean.setPassword(passwordElement.getText());
		 	Element timeoutElement = databaseInfo.element("sessionTime");
		 	connectBean.setTimeOut(timeoutElement.getText());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件未找到！");
			System.exit(0);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件解析发生错误！");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("dbconfig.xml解析中数据格式转换错误！");
			System.exit(0);
		}
		return connectBean;
	}
}
