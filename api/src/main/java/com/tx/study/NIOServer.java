package com.tx.study;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author tx_li
 * @Date 2020/5/4 19:55
 * @Version 1.0
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");
        while (true) {
            SocketChannel socketChannel = channel.accept();
            if (socketChannel != null) {
                System.out.println("收取到新连接" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                try {


                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.isOpen() && socketChannel.read(byteBuffer) != -1) {
                        if (byteBuffer.position() > 0) {
                            break;
                        }
                        if (byteBuffer.position() == 0) {
                            continue;
                        }
                        byteBuffer.flip();
                        byte[] content = new byte[byteBuffer.limit()];
                        byteBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据，来自" + socketChannel.getRemoteAddress());
                        // 响应结果 200
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()) {
                            socketChannel.write(buffer);// 非阻塞
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
