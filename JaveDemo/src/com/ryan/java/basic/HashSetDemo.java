
package com.ryan.java.basic;

import java.util.HashSet;
import java.util.Iterator;

/**
 * HashSet 此类实现 Set 接口，由哈希表（实际上是一个 HashMap
 * 实例）支持。它不保证集合的迭代顺序；特别是它不保证该顺序恒久不变。此类允许使用 null 元素。 此类为基本操作提供了稳定性能，这些基本操作包括
 * add、remove、contains 和 size，假定哈希函数将这些元素正确地分布在桶中。 对此集合进行迭代所需的时间与 HashSet
 * 实例的大小（元素的数量）和底层 HashMap 实例（桶的数量）的“容量”的和成比例。
 * 因此，如果迭代性能很重要，则不要将初始容量设置得太高（或将加载因子设置得太低）。
 */
public class HashSetDemo {

    public static void main(String[] args)
    {
        HashSet hs = new HashSet();
        hs.add(new Student(1, "zhangsan"));
        hs.add(new Student(2, "lishi"));
        hs.add(new Student(3, "wangwu"));
        hs.add(new Student(1, "zhangsan"));
        Iterator it = hs.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }

    static class Student
    {
        int num;
        String name;

        Student(int num, String name)
        {
            this.num = num;
            this.name = name;
        }

        public String toString()
        {
            return "num :" + num + " name:" + name;
        }

        public int hashCode()
        {
            return num * name.hashCode();
        }

        public boolean equals(Object o)
        {
            Student s = (Student) o;
            return num == s.num && name.equals(s.name);
        }
    }
}
