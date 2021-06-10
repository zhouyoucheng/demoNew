package util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * Created by supaur on 2021/6/7 15:31
 *
 * @author supaur
 */
public class SerialUtils {
    static Kryo kryo = new Kryo();
    static {
        kryo.setReferences(false);
    }

    public static void main(String[] args) {
        ObjectDemo objectDemo = new ObjectDemo();
        objectDemo.setA(3);
        objectDemo.setB("b");
        String s = kryoSerial(objectDemo);
        System.out.println(s);
        ObjectDemo serializable = deserializationObject(s);
        System.out.println(serializable);

    }

    @Data
    public static class ObjectDemo implements Serializable {
        private static final long serialVersionUID = -7696339018767774125L;
        private Integer a;
        private String b;
    }

    public static <T extends Serializable> String kryoSerial(T obj) {
        kryo.register(obj.getClass(), new JavaSerializer());
        ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
        Output output = new Output(outPutStream);
        kryo.writeClassAndObject(output, obj);
        return Base64.getEncoder().encodeToString(outPutStream.toByteArray());
    }

    private static <T extends Serializable> T deserializationObject(String objStr) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(objStr));
        Input input = new Input(inputStream);
        return (T) kryo.readClassAndObject(input);
    }

}
