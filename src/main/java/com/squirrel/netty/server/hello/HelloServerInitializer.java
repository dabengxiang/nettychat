package com.squirrel.netty.server.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Date:2018/10/17
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {



    @Override
    protected void initChannel(SocketChannel SocketChannel) throws Exception {
        ChannelPipeline pipeline = SocketChannel.pipeline();

        pipeline.addLast("httpServerCodec",new HttpServerCodec());

        pipeline.addLast("helloHandler",new HelloHandler());


    }
}
