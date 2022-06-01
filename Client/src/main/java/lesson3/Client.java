package lesson3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {

    private final int port;
    private final String host;
    private static String fileName;
    private static long fileSize;
    private static byte[] bytes;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public static void main(String[] args) throws Exception {
//        Message message = new Message("test1.txt", 5, new byte[]{1, 2, 3, 4, 5});

        // При старте Client обрабатывает тестовый файл и отсылает его на сервер.
        // Мне кажется, это лучше делать в специальном методе. Посоветуйте, где его создать
        // Также, хотелось бы понять, как быть с переменными

        // private final int port;
//        private final String host;
//        private static String fileName;
//        private static long fileSize;
//        private static byte[] bytes;

        File file = new File("Client/src/main/resources/test1.txt");
        if(file.exists()){
            fileName = file.getAbsolutePath();// имя файла
            System.out.println(fileName);
            fileSize = file.length();
            System.out.println(fileSize + " bytes");
            bytes = Files.readAllBytes(Paths.get("Client/src/main/resources/test1.txt"));
            String content = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(content);
        }else System.out.println("File does not Exists!");

        Message message = new Message(fileName, (int) fileSize, bytes);

        new Client(9999, "localhost").run(message);

    }

    private void run(Message message) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap client = new Bootstrap();
            client.group(workerGroup);
            client.channel(NioSocketChannel.class);
            client.option(ChannelOption.SO_KEEPALIVE, true);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new MessageEncoder(),
                            new LineBasedFrameDecoder(80),
                            new StringDecoder(StandardCharsets.UTF_8),
                            new ClientHandler(message)
                    );
                }
            });

            ChannelFuture future = client.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
