package com.ryan.java.dp.prototype;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException{
		ProtoType obj1 = new ProtoType();
		String str = "string_1";
		SerializableObject so1 = new SerializableObject();
		System.out.println("str = "+str.hashCode()+" so1 = "+so1.hashCode());
		obj1.setString(str);
		obj1.setObj(so1);
		
		ProtoType clone1 = (ProtoType) obj1.clone();
		String cloneStr = clone1.getString();
		SerializableObject cloneObj = clone1.getObj();
		System.out.println("str = "+cloneStr.hashCode()+" so1 = "+cloneObj.hashCode());
		if(cloneStr == str){
			System.out.println("Shallow cloneStr == str");
		}else{
			System.out.println("Shallow cloneStr != str");
		}
		
		if(cloneObj == so1){
			System.out.println("Shallow cloneObj == obj");
		}else{
			System.out.println("Shallow cloneObj != obj");
		}
		
		ProtoType clone2 = (ProtoType) obj1.deepClone();
		String cloneStr2 = clone2.getString();
		SerializableObject cloneObj2 = clone2.getObj();
		System.out.println("str = "+cloneStr2.hashCode()+" so1 = "+cloneObj2.hashCode());
		if(cloneStr2 == str){
			System.out.println("Deep cloneStr == str");
		}else{
			System.out.println("Deep cloneStr != str");
		}
		
		if(cloneObj2 == so1){
			System.out.println("Deep cloneObj == obj");
		}else{
			System.out.println("Deep cloneObj != obj");
		}
	}
	
}
