package com.qihui.sun.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;


public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据，读取客户端发送的消息
     * @param ctx 上下文对象，含有管道pipeline，通道channel，地址
     * @param msg 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程 "+Thread.currentThread().getName());
        System.out.println("server stx ="+ctx);
        System.out.println("看看channel和pipeline的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();//本质上是一个双向列表
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息是："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址是："+ channel.remoteAddress());
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写到缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client",CharsetUtil.UTF_8));
    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
