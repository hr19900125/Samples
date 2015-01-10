package com.ryan.java.dp.iterator;

public class MyIterator implements Iterator{

    private Collection collection;
    private int pos = -1;
    
    public MyIterator(MyCollection myCollection) {
        collection = myCollection;
    }

    @Override
    public Object previous() {
        if(pos > 0){
            pos --;
        }
        return collection.get(pos);
    }

    @Override
    public Object next() {
        if(pos < collection.size() - 1){
            pos ++;
        }
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        return pos < collection.size() - 1;
    }

    @Override
    public Object first() {
        pos = 0;
        return collection.get(pos);
    }

}
