package org.mybatis.test;

import java.io.IOException;

public class TestBat {

    public static void main(String[] args) {
        String command = "cmd /c start C://Users//Administrator//Desktop//mybat.bat";
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
