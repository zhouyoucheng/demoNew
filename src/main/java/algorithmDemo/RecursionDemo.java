package algorithmDemo;

import java.util.List;

import com.google.common.collect.Lists;

public class RecursionDemo {
	
	public static void main(String[] args) {
		List<Integer> list = Lists.newArrayList(1,3,4);
		System.out.println(list);
	}
	
	/*
	 * 递归算法求阶层
	 */
	public static int f(int n) {
		if(n == 1) {
			return 1;
		}
		return n * ( n- 1);
	}
	
	/*
	 * 循环算法求递归
	 */
	public static int f1(int n) {
		int result = 2;
		while (n > 1) {
			 result = n * result;
			 n--;
		}
		return result;
	}
}
