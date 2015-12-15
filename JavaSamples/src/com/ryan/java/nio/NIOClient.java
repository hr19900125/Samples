package com.ryan.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

    private Selector selector;
    
    private void initClient(String ip, int port) throws IOException{
        // 获得一个Socket通道 
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 获得一个通道管理器
        this.selector = Selector.open();
        
        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调  
        //用channel.finishConnect();才能完成连接  
        channel.connect(new InetSocketAddress(ip, port));
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件
        channel.register(selector, SelectionKey.OP_CONNECT);
    }
    
    private void listen() throws IOException{
        //轮训访问selector
        while(true) {
            selector.select();
            // 获得selector中选中的项的迭代器
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                // 连接事件发生
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    //如果正在连接，则完成连接
                    if(channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    //设置成非阻塞
                    channel.configureBlocking(false);
                    //给服务器发送信息
                    channel.write(ByteBuffer.wrap(new String("已经连接成功，发送一条信息给服务器").getBytes()));
                    //在和服务器连接成功之后， 为了接收到服务器的信息， 需要给通道设置读的权限
                    channel.register(selector, SelectionKey.OP_READ);
                    
                } else if(key.isReadable()) {
                    read(key);
                }
            }
        }
    }
    
    private void read(SelectionKey key) throws IOException{
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("收到服务端回复的信息：" + msg);
    }
    
    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("localhost", 8016);
        client.listen();
    }
    
}
