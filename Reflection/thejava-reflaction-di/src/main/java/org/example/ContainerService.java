package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if (f.getAnnotation(MyInject.class) != null) { //MyInject 애노테이션이 붙은게 있는 경우
                Object fieldInstance = createInstance(f.getType()); //필드 타입의 인스턴스를 생성 (예: Repository)
                f.setAccessible(true);
                try {
                    f.set(instance, fieldInstance); //DI (예: Service에 Repository 주입)
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
