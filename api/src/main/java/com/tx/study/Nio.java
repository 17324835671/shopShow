package com.tx.study;

import com.sun.javafx.binding.StringFormatter;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tx_li
 * @Date 2020/5/1 12:03
 * @Version 1.0
 */
public class Nio {

    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(4);
        System.out.println(String.format("初始容量capacity为:%s,position位置为:%s,limit限制为:%s",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)2);
        byteBuffer.put((byte)3);
        System.out.println(String.format("写入三字节后capacity为:%s,position位置为:%s,limit限制为:%s",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
        System.out.println("开始读取###########");
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(String.format("读取2字节后capacity为:%s,position位置为:%s,limit限制为:%s",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
        byteBuffer.compact();
        System.out.println(String.format("读取2字节后capacity为:%s,position位置为:%s,limit限制为:%s",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
      //  byteBuffer.put((byte)4);
    }

}
