package org.example;
/* //애노테이션과 리플렉션
@MyAnnotation("") //애노테이션 값 지정
 */

public class Book {

/*  //리플렉션 API 1부: 클래스 정보 조회, 애노테이션과 리플렉션

    private static String B = "BOOK";
    private static final String C = "BOOK";

    @MyAnnotation
    private String a = "a";
    public String d = "d";
    protected String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }
*/
    //리플랙션 API 2부: 클래스 정보 수정 또는 실행
    public static String A = "A";
    private String B = "B";

    public Book() {
    }

    public Book(String b) {
        B = b;
    }

    private void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }


}
