package algorithmDemo;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Data;

@Data
public class Factorial {
    public static int factorialRec(final int number) {
        if(number == 1)
            return number;
        else
            return number * factorialRec(number - 1);
    }
    public static TailCall<Integer> factorialTailRec(
            final int factorial, final int number) {
        if (number == 1)
            return TailCalls.done(factorial);
        else
            return TailCalls.call(() -> factorialTailRec(factorial * number, number - 1));
    }

    public static void main(String[] args) {
        System.out.println(factorialTailRec(1, 15).invoke());  // 0
        System.out.println(TailCalls.call(factorialTailRec(2,3)).invoke());
        System.out.println(TailCalls.done(3).invoke());
    }

}
