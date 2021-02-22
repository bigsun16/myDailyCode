package com.qihui.sun.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //只处理连接请求,无线循环
        //含有的子线程NioEventLoop的个数默认是cpu核数*2
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //真正和客户端业务处理,无线循环
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器端的启动对象,并配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG,128)//设置线程队列的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)//设置保持活动的链接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("....server is ready...");
            //绑定一个端口并且同步，生成了一个ChannelFuture对象
            //启动服务器，并绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
