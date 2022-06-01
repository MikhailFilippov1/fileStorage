package lesson3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

        import java.nio.charset.StandardCharsets;
        import java.util.List;

public class MessageDecoder extends ReplayingDecoder<Message> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // Тут сервер распаковывает Message и можно что-то сделать с файлом
        // Или лучше в другом методе?
        //Где его лучше создать тогда?

        int typeLen = in.readInt();
        String fileName = in.readCharSequence(typeLen, StandardCharsets.UTF_8).toString();
        int lengthFile = in.readInt();
        byte[] data = new byte[(lengthFile)];
        in.readBytes(data, 0, lengthFile);

        Message message = new Message(fileName, lengthFile, data);
        out.add(message);
    }
}
