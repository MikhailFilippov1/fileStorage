package lesson3;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        System.out.println("Received>" + message);

        // Тут можно вставить обработку файла? Например, создать файл по полученному пути и записать его?
        // Или лучше создать новый метод? Где его лучше создать?

        ChannelFuture future = ctx.writeAndFlush("OK\n");
        future.addListener(ChannelFutureListener.CLOSE);
    }
}
