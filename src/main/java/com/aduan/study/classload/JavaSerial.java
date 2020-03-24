package com.aduan.study.classload;

import java.io.*;

/**
 * Java序列化实现
 */
public class JavaSerial {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("DJ");
        user.setSex("boy");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bos);
        outputStream.writeObject(user);
        byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        User user1 = (User) ois.readObject();
        System.out.println(user1);
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 813079678551939957L;

    private String name;
    private transient String sex;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(sex);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        sex = (String) in.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
