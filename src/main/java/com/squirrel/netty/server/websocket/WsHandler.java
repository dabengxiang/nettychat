package com.squirrel.netty.server.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * Date:2018/10/17
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class WsHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        String content = textWebSocketFrame.text();
        System.out.println("接收到的信息："+content);
        channelGroup.writeAndFlush(new TextWebSocketFrame(
                "[服务器在]" + LocalDateTime.now()
                        + "接受到消息, 消息为：" + content
        ));
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
    }
}
