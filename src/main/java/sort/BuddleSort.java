package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author wb-zyc501131
 *
 */

public class BuddleSort {
	
	public static void main(String[] args) {
		Integer[] aIntegers = {2,4,6,1,3,615,0,3,3,6,100,32,89,265,1111,222,33533543,6546,65656};
		buddleSort(aIntegers);
		Arrays.asList(aIntegers).stream().peek(v -> {System.out.print(v + " ");}).count();	
	}
	
	public static void buddleSort(Integer[] array) {
		int length = array.length;
		for (int i = length-1; i > 1; i --) {
			int index = 0;
			for (int j = 0; j < i; j ++) {
				if (array[i] > array[j]) {
					swap(array, i, j);
					index ++;
				}
			}
			if (index == 0) {
				return;
			}
			Arrays.asList(array).stream().peek(v -> {System.out.print(v + " ");}).count();	
			System.out.println();
		}
	}

	private static void swap(Integer[] array, int i, int j) {
		array[i] = array[i] + array[j];
		array[j] = array[i] - array[j];
		array[i] = array[i] - array[j];
	}
}
