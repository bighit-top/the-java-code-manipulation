package org.example;

import java.lang.reflect.*;
import java.util.Arrays;

public class App {
    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

/*      //리플렉션 API 1부: 클래스 정보 조회


        //클래스 인스턴스에 접근하는 방법: JVM 힙 영역에 생성
        //1. 타입을 통한 클래스 타입의 인스턴스 생성
        Class<Book> bookClass = Book.class;

        //2. 인스턴스를 통한 클래스 타입의 인스턴스 생성
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        //3. 클래스 풀 내임을 통한 클래스 타입의 인스턴스 생성
        Class<?> aClass1 = Class.forName("org.example.Book");


        //클래스의 필드를 가져옴
        System.out.println("    ##### getFields #####");
        Arrays.stream(bookClass.getFields()).forEach(System.out::println); //public 필드만 가져옴
        System.out.println();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println); //전체 필드를 가져옴
        System.out.println();

        //필드의 값을 가져옴
        System.out.println("    ##### getDeclaredFields #####");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true); //접근 불가한 필드에 접근할 수 있도록 설정
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        System.out.println();

        //클래스의 메서드를 가져옴
        System.out.println("    ##### getMethods #####");
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println); //Object 포함해서 가져옴
        System.out.println();

        //클래스의 생성자를 가져옴
        System.out.println("    ##### getConstructors #####");
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);
        System.out.println();

        //상위 슈퍼 클래스를 가져옴
        System.out.println("    ##### getSuperclass #####");
        System.out.println(MyBook.class.getSuperclass());
        System.out.println();

        //인터페이스를 가져옴
        System.out.println("    ##### getInterfaces #####");
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
        System.out.println();

        //기타 활용: 접근제어자 확인
        System.out.println("    ##### etc #####");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        //기타 활용: 메서드 리턴타입, 파라미터 확인
        System.out.println();
        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
            System.out.println(m.getReturnType());
            System.out.println(m.getParameterCount());
        });
        System.out.println();
*/

/*      //애노테이션과 리플렉션


        //애노테이션: 설정없이는 출력안됨
        //@Retention(RetentionPolicy.RUNTIME) 설장시 출력됨
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
        System.out.println();

        //상속관계에서 특정 클래스에 붙은 애노테이션만 가져올 수 있다.
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
        System.out.println();

        //특정 애노테이션이 붙은 필드를 찾을 수 있고 애노테이션 정보 조회도 가능하다.
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) a;
                    System.out.println(myAnnotation.value());
                    System.out.println(myAnnotation.number());
                }
            });
        });
*/

        //리플랙션 API 2부: 클래스 정보 수정 또는 실행

        //생성자
        System.out.println("    ##### constructor #####");
        Class<?> bookClass = Class.forName("org.example.Book");
//        Constructor<?> constructor = bookClass.getConstructor(null); //파라미터가 없는 생성자
        Constructor<?> constructor = bookClass.getConstructor(String.class); //파라미터가 있는 생성자
        Book book = (Book) constructor.newInstance("constructorParameter");
        System.out.println(book);
        System.out.println();

        //필드: static
        System.out.println("    ##### field: static #####");
        Field a = Book.class.getDeclaredField("A");
        System.out.println(a.get(null)); //get: static이라 null로 가져와도 됨
        a.set(null, "AAA"); //set: 값 변경
        System.out.println(a.get(null));
        System.out.println();

        //필드: normal
        System.out.println("    ##### field: normal #####");
        Field b = Book.class.getDeclaredField("B");
        b.setAccessible(true); //접근지시자로 막힌 필드 접근 허용
        System.out.println(b.get(book)); //get: normal 이라 특정 인스턴스를 거쳐야함
        b.set(book, "BBB"); //set
        System.out.println(b.get(book));
        System.out.println();

        //메서드: private, no parameter
        System.out.println("    ##### method: private #####");
        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true); //접근지시자로 막힌 메서드 접근 허용
        c.invoke(book);
        System.out.println();

        //메서드: public, 1 parameter
        System.out.println("    ##### method: public #####");
        Method d = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) d.invoke(book, 1, 2);
        System.out.println(invoke);

    }
}
