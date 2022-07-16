package org.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 모자에서 토끼를 꺼내는 마술
 */
public class Masulsa {

    public static void main(String[] args) {
/*
        ClassLoader classLoader = Masulsa.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);

        try {
            new ByteBuddy().redefine(
                    typePool.describe("org.example.Moja").resolve(),
                    ClassFileLocator.ForClassLoader.of(classLoader))
                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
                    .make().saveIn(new File("/Volumes/topSSD/dev/workspace/thejavacodemanipulation/target/classes/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

        System.out.println(new Moja().pullOut());
    }
}
