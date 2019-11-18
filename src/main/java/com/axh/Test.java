package com.axh;

public class Test {
    public static void main(String[] args) {
        String str = "admin@qq.com";
//        // ^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$  邮箱
        String reg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        System.out.println(str.matches(reg));
        // import java.util.regex正则

    }
}
