package util;

import com.alibaba.excel.util.IoUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wb-zyc5011311
 */
@Slf4j
public class RunMainTest {

   static DatagramSocket udpSocket;

    static {
        try {
            udpSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static class PushPacket {
        public String type;
        public long lastRefTime;
        public String data;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Date date = new Date(1623202909350L);
        System.out.println(date);
    }




}


