
package com.ryan.java.basic;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 此类实现 Set 接口，该接口由 TreeMap 实例支持。此类保证排序后的 set 按照升序排列元素，根据使用的构造方法不同，可能会按照元素的自然顺序
 * 进行排序， 或按照在创建 set 时所提供的比较器进行排序。
 * 是一个有序集合,元素中安升序排序,缺省是按照自然顺序进行排序,意味着TreeSet中元素要实现Comparable接口;
 * 我们可以构造TreeSet对象时,传递实现了Comparator接口的比较器对象.
 */
public class TreeSetDemo {

    public static void main(String[] args)
    {
        // TreeSet ts=new TreeSet();
        TreeSet ts = new TreeSet(new Students.compareToStudent());
        ts.add(new Students(2, "zhangshan"));
        ts.add(new Students(3, "lishi"));
        ts.add(new Students(1, "wangwu"));
        ts.add(new Students(4, "maliu"));

        Iterator it = ts.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }

    static class Students implements Comparable
    {
        int num;
        String name;

        Students(int num, String name)
        {
            this.num = num;
            this.name = name;
        }

        // 定义一个内部类来实现比较器
        static class compareToStudent implements Comparator
        {
            public int compare(Object o1, Object o2) {
                Students s1 = (Students) o1;
                Students s2 = (Students) o2;
                int rulst = s1.num > s2.num ? 1 : (s1.num == s2.num ? 0 : -1);
                if (rulst == 0)
                {
                    rulst = s1.name.compareTo(s2.name);
                }
                return rulst;
            }
        }

        // 写具体的比较方法
        public int compareTo(Object o)

        {
            int result;
            Students s = (Students) o;
            result = num > s.num ? 1 : (num == s.num ? 0 : -1);
            if (result == 0)
            {
                result = name.compareTo(s.name);
            }
            return result;
        }

        public String toString()
        {
            return num + ":" + name;
        }
    }
}
