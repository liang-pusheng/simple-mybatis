/**
 * 字符串的处理方法
 *
 * 处理字符串的三个类：String、StringBuilder、StringBuffer
 */
public class MyString {

    public static void main(String[] args) {
        // 一般由String声明的字符串长度是不可变的，这也是它与其他二者的区别，这跟它的类声明有关(final class)
        String str = "abc";
        String str1 = "abc";
        // 第一次声明的"abc"会被放到字符串池中，当再次声明时，会直接去字符串池中取，因此地址是相同的
        System.out.println(str == str1); //true
        // 注意：使用 == 时尽量将常量放在左边，这样可以避免写出 = 时编译器将此操作认为是赋值行为
        // compareTo()方法
        String str3 = "abc";
        String str4 = "abcd";
        System.out.println(str3.compareTo(str4));// -1
        // split()方法
        String hello = "helloworld";
        String[] split = hello.split("abc");// 当split中没有"abc"时，它会将hello整个字符串放到数组中
        for (String s : split) {
            System.out.println(s + " ");
        }
        // 自动类型转换
        int i= 1;
        int j = 2;
        String x = "5";
        System.out.println(i + j + x);// 35
        System.out.println(i + x + j);// 152
        // 常量池是针对在编译期就确定下来的常量而言的

        // StringBuffer、StringBuilder
        StringBuffer sb = new StringBuffer("hello");
        System.out.println(sb.length());// 5，获取的是字符串的长度
        System.out.println(sb.capacity());// 21，获取的是当前缓冲区的大小，默认为16
        // equals()方法
        StringBuffer sb2 = new StringBuffer("hello");
        System.out.println(sb.equals(sb2));// false，因为StringBuffer没有重写Object的equals()方法
        // 使用append方法追加字符串时，会在原有的基础上追加，不会开辟新的内存，这点比String好
        sb.append("world!");
        System.out.println(sb);
    }

}
