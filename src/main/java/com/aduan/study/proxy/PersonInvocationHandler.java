package com.aduan.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonInvocationHandler implements InvocationHandler {
    private Person person;

    PersonInvocationHandler(Person person){
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(person, args);
        return invoke;
    }

    public static void main(String[] args) {
        Person chinese = new ChinesePerson();

        PersonInvocationHandler personInvocationHandler = new PersonInvocationHandler(chinese);
        Person proxy = (Person) Proxy.newProxyInstance(chinese.getClass().getClassLoader(),
                chinese.getClass().getInterfaces(),
                personInvocationHandler);
        proxy.sayHello();
    }
}
