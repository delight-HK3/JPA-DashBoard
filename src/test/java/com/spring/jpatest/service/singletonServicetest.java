package com.spring.jpatest.service;

public class singletonServicetest {
    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static final singletonServicetest test = new singletonServicetest();
    
    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static singletonServicetest getInstance() {
        return test;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private singletonServicetest() {
    }

    // 임의로 호출 잘 되는지 확인용 함수
    public void call() {
        System.out.println("성공!");
    }
}
