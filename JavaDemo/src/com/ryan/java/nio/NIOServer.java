package com.ryan.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    
    //通道管理器  
    private Selector selector;
    
    private void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道 
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞  
        serverSocketChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，  
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    
    private void listen() throws IOException{
        System.out.println("Server is started");
        // 轮询访问selector  
        while(true) {
            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 删除已选的key,以防重复处理
                iterator.remove();
                // 客户端请求连接事件
                if(key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道 
                    SocketChannel channel = serverSocketChannel.accept();
                    // 设置成非阻塞  
                    channel.configureBlocking(false);
                    // 给客户端发送消息
                    channel.write(ByteBuffer.wrap(new String("发送信息：连接成功").getBytes()));
                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限
                    channel.register(this.selector, SelectionKey.OP_READ);
                    
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
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("接收客户端的信息：" + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        //将消息回发给客户端
        channel.write(outBuffer);
    }
    
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8016);
        server.listen();
    }

}
