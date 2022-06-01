package lesson3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

        import java.nio.charset.StandardCharsets;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getFileName().length());
        out.writeCharSequence(msg.getFileName(), StandardCharsets.UTF_8);
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getData());
    }
}