package org.example;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //default=class, runtime까지 애노테이션 유지
@Target({ElementType.TYPE, ElementType.FIELD}) //사용 위치 지정
@Inherited //상속되도록 설정이 가능하다.
public @interface AnotherAnnotation {

    //애노테이션은 기본값을 가질 수 있다.
    String name() default "name";

    int number() default 100;

    //value()를 사용하면 선언 없이 값만 넣어 사용할 수 있다.
    String value() default "value";
}
