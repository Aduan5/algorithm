package com.aduan.study;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Author DuanJun
 * @Date 2019/12/5 20:01
 */
public class MainTest {

    public static void main(String[] args) {
//        Class<User> userClass = User.class;
//        System.out.println(userClass.getDeclaredConstructors().length);

//        System.out.println(RuntimeUtil.execForStr("ipconfig"));
//        Console.log("ddd:{}",  4,5);

//        ImgUtil.pressText(//
//                FileUtil.file("D:\\Aduan\\picture\\monkey.jpg"), //
//                FileUtil.file("D:\\Aduan\\picture\\monkey_result.png"), //
//                "版权所有", Color.WHITE, //文字
//                new Font("黑体", Font.BOLD, 100), //字体
//                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
//                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
//                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
//        );

        ImgUtil.scale(FileUtil.file("D:\\Aduan\\picture\\me.jpg"), FileUtil.file("D:\\Aduan\\picture\\m2.jpg"), 0.4f);

//        ImgUtil.pressImage(
//                FileUtil.file("D:\\Aduan\\picture\\monkey.jpg"), //
//                FileUtil.file("D:\\Aduan\\picture\\monkey_result2.png"), //
//                ImgUtil.read(FileUtil.file("D:\\Aduan\\picture\\ggimg.png")), //水印图片
//                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
//                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
//                0.9f
//        );

        String[] s = new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s = list.toArray(new String[0]);//没有指定类型的话会报错
        System.out.println(Arrays.toString(s));
    }
}
