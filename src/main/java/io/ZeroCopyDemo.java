package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by supaur on 2021/4/20 11:24
 */
public class ZeroCopyDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\zyc\\zyc-demo\\src\\main\\java\\io\\zyc");
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        MappedByteBuffer map = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 2048);
        map.put("zyc".getBytes());
        rw.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\zyc\\zyc-demo\\src\\main\\java\\io\\zyc1");
        FileChannel channel1 = fileOutputStream.getChannel();
        channel.transferTo(0, file.length(), channel1);
    }
}
