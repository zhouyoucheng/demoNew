package util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by supaur on 2021/4/22 9:33
 * @author supaur
 */
public class RandomUniqueNum {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.println(get16UUID());
        }
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 生成16位唯一数
     *
     * @return 16唯一数
     */
    public static String get16UUID() {
        //最大支持1-9个集群机器部署
        int machineId = new Random(10).nextInt(8) + 1;
        System.out.println("machineId:" + machineId);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        System.out.println("hashCodeV:" + hashCodeV);
        String time = String.valueOf(System.currentTimeMillis());
        String newTime = time.substring(time.length() - 5);
        System.out.println("newTime:" + newTime);
        return machineId + String.format("%010d", hashCodeV) + newTime;
    }

}
