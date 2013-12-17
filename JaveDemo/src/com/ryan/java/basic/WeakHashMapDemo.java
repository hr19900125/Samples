
package com.ryan.java.basic;

import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static class Element {
        private String ident;

        public Element(String id) {
            ident = id;
        }

        public String toString() {
            return ident;
        }

        public int hashCode() {
            return ident.hashCode();
        }

        public boolean equals(Object obj) {
            return obj instanceof Element && ident.equals(((Element) obj).ident);
        }

        protected void finalize() {
            System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
        }
    }

    public static class Key extends Element {
        public Key(String id) {
            super(id);
        }
    }

    public static class Value extends Element {
        public Value(String id) {
            super(id);
        }
    }

    public static void main(String[] args) {
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<Key, Value>();
        for (int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0)
                keys[i] = k;
            map.put(k, v);
        }
        System.gc();
    }
}
