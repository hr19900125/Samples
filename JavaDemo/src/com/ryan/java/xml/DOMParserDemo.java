package com.ryan.java.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParserDemo {

	private static final String TEST_XML_PATH = "src/com/ryan/java/xml/test.xml";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        write();
		read();
	}
	
	private static void read(){
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(TEST_XML_PATH);
			NodeList nodeList = doc.getElementsByTagName("Project");
			for(int i=0,size = nodeList.getLength();i < size;i ++){
				Element node = (Element) nodeList.item(i);
				System.out.println("ProjectName : "+node.getAttribute("name"));
				System.out.println("Desc : "+node.getElementsByTagName("Description").item(0).getFirstChild().getNodeValue());
				System.out.println("Author : "+node.getElementsByTagName("Author").item(0).getFirstChild().getNodeValue());
				System.out.println("Telephone : "+node.getElementsByTagName("Telephone").item(0).getFirstChild().getNodeValue());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void write(){
		Element cloud = null;
		Element project = null;
		Element desc = null;
		Element author = null;
		Element tel = null;
		
		try {
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			//从DOM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//把要解析的xml文档读入DOM解析器
			Document doc = dbBuilder.parse(TEST_XML_PATH);
			//得到文档名称为Cloud的元素的节点列表
			NodeList nodeList = doc.getElementsByTagName("Cloud");
			cloud = (Element) nodeList.item(0);
			//创建名称为Project的元素
			project = doc.createElement("Project");
			//设置Project元素的属性name
			project.setAttribute("name", "DocDemo");
			
			desc = doc.createElement("Description");
			desc.appendChild(doc.createTextNode("Doc Demo"));
			
			author = doc.createElement("Author");
			author.appendChild(doc.createTextNode("Ryan"));
			
			tel = doc.createElement("Telephone");
			tel.appendChild(doc.createTextNode("897654321"));
			
			project.appendChild(desc);
			project.appendChild(author);
			project.appendChild(tel);
			cloud.appendChild(project);
			
			((XmlDocument)doc).write(new FileOutputStream(TEST_XML_PATH));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
