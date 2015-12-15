package com.ryan.java.generic;

/**
 * 普通泛型
 */
public class NotePad<K , V> {

    private K key;
    private V value;
    
    public K getKey(){
        return key;
    }
    
    public V getValue(){
        return value;
    }
    
    public void setKey(K key){
        this.key = key;
    }
    
    public void setValue(V value){
        this.value = value;
    }
    
}
