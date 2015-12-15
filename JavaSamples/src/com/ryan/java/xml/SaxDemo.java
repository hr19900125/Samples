package com.ryan.java.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxDemo extends DefaultHandler{
	
	private String tagValue;
	
	public void startDocument(){
		System.out.println("开始解析");
	}

	public void endDocument(){
		System.out.println("解析结束");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
//		super.startElement(uri, localName, qName, attributes);
		System.out.println(qName+"开始");
		if(attributes != null && attributes.getLength() != 0){
			System.out.println("属性：");
			for(int i = 0;i < attributes.getLength();i ++){
				System.out.println(attributes.getQName(i) + "=");
				System.out.println(attributes.getValue(i) + " ");
			}
			System.out.println();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
//		super.endElement(uri, localName, qName);
		System.out.println(qName + "标签值: " + tagValue);
		System.out.println(qName + "结束");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
//		super.characters(ch, start, length);
		tagValue = new String(ch, start, length).trim();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("src/com/ryan/java/xml/test.xml");
		SAXParserFactory saxParFac = SAXParserFactory.newInstance();
		try {  
            SAXParser saxParser = saxParFac.newSAXParser();  
            saxParser.parse(file, new SaxDemo());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

}
