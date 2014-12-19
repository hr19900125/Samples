package com.ryan.java.dp.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ProtoType implements Cloneable , Serializable{

	private static final long serialVersionUID = 1L;
	
	private String string;
	
	private SerializableObject obj;

	/**
	 * 浅复制 ：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		ProtoType proto = (ProtoType) super.clone();
		return proto;
	}
	
	/**
	 * 深复制 ：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Object deepClone() throws IOException, ClassNotFoundException{
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
	
	public String getString(){
		return string;
	}
	
	public void setString(String str){
		string = str;
	}
	
	public SerializableObject getObj(){
		return obj;
	}
	
	public void setObj(SerializableObject object){
		obj = object;
	}
	
}
