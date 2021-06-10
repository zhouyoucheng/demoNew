package redis;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

public class RedisDemo1 {

    public  static Jedis initJedis() {
        Jedis jedis = new Jedis("http://127.0.0.1:6379");
        return jedis;
    }
    public static void main(String[] args) {

        Jedis jedis = initJedis();
        ArrayList<String> key = Lists.newArrayList("zyc", "zyc1");
        ArrayList<String> value = Lists.newArrayList("zyc111", "zyc11111");
        jedis.eval("for i=1,#KEYS do redis.call(\"set\", KEYS[i], ARGV[i]) end return true" , key, value);


    }
}
