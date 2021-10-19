package com.koreait.first.ch07;

public class Utils {
    public static int parseStringToInt(String val) {
        // 예외처리
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();   // 에러 내용 로그에 출력
//            // 예외가 발생 되었을 때 실행되고 싶은 것들을 작성
//        } finally {
//            // 에러가 발생 하거나 발생하지 않거나 반드시 실행될 구문을 작성
//        }

        int intVal = 0;
        try {
            intVal = Integer.parseInt(val);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return intVal;
    }
}
