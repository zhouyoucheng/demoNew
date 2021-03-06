package io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object>{
	
	private WebSocketServerHandshaker handshake;
	private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			handHttpRequest(ctx, (FullHttpRequest)msg);
		} else if (msg instanceof WebSocketFrame) {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
		if(!req.getDecoderResult().isSuccess() || ("websocket".equals(req.headers().get("Upgrade")))) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
		handshake = wsFactory.newHandshaker(req);
		if (handshake == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshake.handshake(ctx.channel(), req);
		}
		
	}
	
	/**
	 * ????????????????????????????????????websocket??????
	 * @param ctx
	 * @param frame
	 */
	@SuppressWarnings("unused")
	private void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		if (frame instanceof CloseWebSocketFrame) {
			handshake.close(ctx.channel(), (CloseWebSocketFrame)frame);
		}
		
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
		}
		
		if (!(frame instanceof TextWebSocketFrame)) {
			System.out.println("??????????????????????????????");
			throw new RuntimeException("??????????????????????????????");
		}
		
		String request = ((TextWebSocketFrame)frame).text();
		System.out.println(request);
		
	}
	
	@SuppressWarnings("deprecation")
	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
			DefaultFullHttpResponse res) {
		if (res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}
		// ?????????????????????????????????
		ChannelFuture future = ctx.channel().writeAndFlush(res);
		if (res.getStatus().code() != 200) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
	/**
	 * ????????????????????????????????????????????????
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.add(ctx.channel());
		System.err.println("?????????????????????????????????");
	}
	
	/**
	 * ???????????????
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.remove(ctx.channel());
		System.out.println("?????????????????????????????????");
	}
	
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public boolean acceptInboundMessage(Object msg) throws Exception {
		// TODO Auto-generated method stub
		return super.acceptInboundMessage(msg);
	}
	
	
}
