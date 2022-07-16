package org.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {

/*
    //프록시 패턴
    //컴파일 시점 생성
    BookService bookService = new BookServiceProxy(new DefaultBookService());
*/

/*
    //다이나믹 프록시 실습
    //런타임 시점 생성: BookServiceProxy 파일 자체가 없어도 동작
    BookService bookService = (BookService) Proxy.newProxyInstance
            (Book.class.getClassLoader(),
            new Class[]{BookService.class}, //인터페이스 기반. 클래스 기반으로는 못 만듦
            new InvocationHandler() {

                BookService bookService = new DefaultBookService(); //실제

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //동적으로 메서드를 분리해야하는 제약: 스프링을 사용하면 aop에서 동적으로 처리해줌
                    if (method.getName().equals("rent")) {
                        System.out.println("##### start runtime proxy message #####");
                        Object invoke = method.invoke(bookService, args);//실제 서비스 실행
                        System.out.println("##### end runtime proxy message #####");
                        return invoke;
                    }
                    return method.invoke(bookService, args);
                }
            });
*/

    @Test
    public void di() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
/*
        //클래스의 프록시가 필요하다면: 상속을 기반으로 하기 때문에, 상속할 수 없는 일부 클래스는 사용하지 못함.
        // 예) final class, private constructor
        // -> 가급적 interface 생성하여 작성하길 권장

        //cglib로 생성
        MethodInterceptor handler = new MethodInterceptor() {
            DefaultBookService defaultBookService = new DefaultBookService(); //real subject
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //동적으로 메서드를 분리해야하는 제약: 스프링을 사용하면 aop에서 동적으로 처리해줌
                if (method.getName().equals("rent")) {
                    System.out.println("##### start cglib proxy #####");
                    Object invoke = method.invoke(defaultBookService, args);
                    System.out.println("##### end cglib proxy #####");
                    return invoke;
                }
                return method.invoke(defaultBookService, args);
            }
        };

        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);
*/

/*
        //ByteBuddy로 생성
        Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {

                    DefaultBookService defaultBookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //동적으로 메서드를 분리해야하는 제약: 스프링을 사용하면 aop에서 동적으로 처리해줌
                        if (method.getName().equals("rent")) {
                            System.out.println("##### start cglib proxy #####");
                            Object invoke = method.invoke(defaultBookService, args);
                            System.out.println("##### end cglib proxy #####");
                            return invoke;
                        }
                        return method.invoke(defaultBookService, args);
                    }

                }))
                .make().load(DefaultBookService.class.getClassLoader()).getLoaded();
        BookService bookService =
                proxyClass.getConstructor(null).newInstance(); //DefaultBookService 클래스를 리턴
*/

        //Mockito
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        Book hibernateBook = new Book();
        hibernateBook.setTitle("Hibernate");
        when(bookRepositoryMock.save(any())).thenReturn(hibernateBook);

        DefaultBookService bookService = new DefaultBookService(bookRepositoryMock);

        Book book = new Book();
        book.setTitle("Spring");

        bookService.rent(book);
        System.out.println();
        bookService.responseBook(book);
        System.out.println();

        bookService.save(book); //mokito 적용
    }
}