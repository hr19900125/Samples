package com.ryan.java.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDemo {
	
	
	private Document document;
	
	/** 
     * 创建DOM树 
     *  
     * 要读入一个XML文档，首先要一个DocumentBuilder对象 
     */  
	public void init(){
		// 获取 DocumentBuilderFactory 的新实例 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 使用当前配置的参数创建一个新的 DocumentBuilder 实例 
		DocumentBuilder builder = null;
		try{
			builder = factory.newDocumentBuilder();
		}catch(Exception e){
			e.printStackTrace();
		}
		// 获取 DOM Document 对象的一个新实例来生成一个 DOM 树 
		this.document = builder.newDocument();
	}
	
	public void createXml(File file){
		this.init();
		
		// 创建XML根节点employees 
		Element root = this.document.createElement("employees");
		
		this.document.appendChild(root);
		
		// 1.创建根节点的子节点employee 
		Element employee = this.document.createElement("employee");
		
		// 向根节点添加属性节点 
		Attr id = this.document.createAttribute("id");
		id.setNodeValue("0001");
		// 把属性节点对象，追加到达employee节点；
		employee.setAttributeNode(id);
		
		// 声明employee的子节点name 
		Element name = this.document.createElement("name");
		// 向XML文件name节点追加数据 
		name.appendChild(this.document.createTextNode("hr"));
		employee.appendChild(name);
		
		Element sex = this.document.createElement("sex");
		sex.appendChild(this.document.createTextNode("m"));
		employee.appendChild(sex);
		
		Element age = this.document.createElement("age");
		age.appendChild(this.document.createTextNode("25"));
		employee.appendChild(age);
		
		root.appendChild(employee);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tf.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件没有找到！");
		}
		
		// 充当转换结果的持有者，可以为 XML、纯文本、HTML 或某些其他格式的标记  
        StreamResult result = new StreamResult(pw);  
        // DOMSource implements Source  
        DOMSource source = new DOMSource(document); 
        
        try {  
            // 将 XML Source 转换为 Result  
            transformer.transform(source, result);  
        } catch (TransformerException e) {  
            e.printStackTrace();  
            System.out.println("生成XML文件失败!");  
        }  
        System.out.println("生成XML文件成功!");  
		
	}
	
	public void parserXml(File file){
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = null;
		
		try {  
            builder = factory.newDocumentBuilder();  
            // 将给定 URI的内容解析为一个 XML文档，并且返回一个新的 DOM Document 对象  
            document = builder.parse(file);  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
		// 获得文档根元素对对象;  
        Element root = document.getDocumentElement(); 
        
     // 获得文档根元素下一级子元素所有元素;  
        NodeList nodeList = root.getChildNodes();  
  
        System.out.print("<employees>");  
        System.out.println(root.getNodeName());  
  
        if (null != root) {  
            for (int i = 0; i < nodeList.getLength(); i++) {  
                Node child = nodeList.item(i);  
  
                // 输出child的属性;  
                System.out.print("<test>");  
                System.out.println(child);  
  
                if (child.getNodeType() == Node.ELEMENT_NODE) {  
                    System.out.print("<id>");  
                    System.out.println(child.getAttributes().getNamedItem("id").getNodeValue());  
                }  
                for (Node node = child.getFirstChild(); node != null; node = node.getNextSibling()) {  
                    if (node.getNodeType() == Node.ELEMENT_NODE) {  
                        if ("name".equals(node.getNodeName())) {  
                            System.out.print("<name>");  
                            System.out.println(node.getFirstChild().getNodeValue());  
                        }  
                    }  
                    if (node.getNodeType() == Node.ELEMENT_NODE) {  
                        if ("sex".equals(node.getNodeName())) {  
                            System.out.print("<sex>");  
                            System.out.println(node.getFirstChild().getNodeValue());  
                        }  
                    }  
                    if (node.getNodeType() == Node.ELEMENT_NODE) {  
                        if ("age".equals(node.getNodeName())) {  
                            System.out.print("<age>");  
                            System.out.println(node.getFirstChild().getNodeValue());  
                        }  
                    }  
                    if (node.getNodeType() == Node.ELEMENT_NODE) {  
                        if ("email".equals(node.getNodeName())) {  
                            System.out.print("<email>");  
                            System.out.println(node.getFirstChild().getNodeValue());  
                        }  
                    }  
                }  
            }  
        }  
        System.out.println("解析完毕");  

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        DomDemo dom = new DomDemo();
        File file = new File("src/com/ryan/java/xml/domdemo.xml");
        System.out.println(file.getAbsolutePath());
        dom.createXml(file);
        dom.parserXml(file);
	}

}
