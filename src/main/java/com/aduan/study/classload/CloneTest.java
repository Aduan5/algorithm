package com.aduan.study.classload;

import java.io.*;

public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        // 创建父亲(LiLiu)，儿子(LiWu)，孙子（LiLiu）并关联
        Son father = new Son();
        father.setName("LiSi");
        Son son = new Son();
        son.setName("LiWu");
        Son grandSon = new Son();
        grandSon.setName("LiLiu");
        father.setSon(son);
        son.setSon(grandSon);

        // 调用clone方法
        Son fatherCopy = (Son) father.clone();
        boolean flag1 = fatherCopy == father;
        boolean flag2 = fatherCopy.getSon() == son;
        boolean flag3 = fatherCopy.getSon().getSon() == grandSon;
        // 比较克隆后的地址
        System.out.println(flag1);// false
        System.out.println(flag2);// true
        System.out.println(flag3);// true
        // 比较Name
        flag1 = fatherCopy.getName() == father.getName();
        flag2 = fatherCopy.getSon().getName() == son.getName();
        flag3 = fatherCopy.getSon().getSon().getName() == grandSon.getName();
        System.out.println(flag1);// true
        System.out.println(flag2);// true
        System.out.println(flag3);// true

        //将对象写到流里
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(father);

        //从流里读出来
        ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        fatherCopy = (Son) objIn.readObject();

        flag1= fatherCopy==father;
        flag2 = fatherCopy.getSon() == son;
        flag3 = fatherCopy.getSon().getSon() == grandSon;
        System.out.println(flag1);// false
        System.out.println(flag2);// false
        System.out.println(flag3);// false

        // 比较Name
        flag1= fatherCopy.getName()==father.getName();
        flag2 = fatherCopy.getSon().getName() == son.getName();
        flag3 = fatherCopy.getSon().getSon().getName() == grandSon.getName();
        System.out.println(flag1);// false
        System.out.println(flag2);// false
        System.out.println(flag3);// false
    }
}

class Son implements Serializable, Cloneable {

    private String name;
    private Son son;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}