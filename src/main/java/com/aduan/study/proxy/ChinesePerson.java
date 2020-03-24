package com.aduan.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ChinesePerson implements Person {
    @Override
    public void sayHello() {
        System.out.println("Chinese say hello");
    }

    public static void main(String[] args) {

        // 代理的目标接口Person
        Person person = new ChinesePerson();
        // JDK动态代理核心类 Proxy.newProxyInstance
        Person proxyInstance = (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(), // 类加载器
                person.getClass().getInterfaces(), // 代理类的接口数组
                new InvocationHandler() { // 代理处理器
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK动态代理方法执行前");
                        Object result = method.invoke(person, args);
                        System.out.println("JDK动态代理方法执行后");
                        return result;
                    }
                });
        proxyInstance.sayHello();
    }
}
