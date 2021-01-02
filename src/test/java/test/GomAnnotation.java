package test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    1 , 2 를 쓰는 어노테이션이다.
 */

@Retention(RetentionPolicy.RUNTIME) // 런타임까지 끌고 가겠다.
@Target(ElementType.METHOD) // 메소드 영역
@Test          // 1
@Tag("gom")    // 2
public @interface GomAnnotation {

}
