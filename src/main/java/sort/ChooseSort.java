package sort;

import java.util.Arrays;

public class ChooseSort {
	
	public static void main(String[] args) {
		Integer[] aIntegers = {2,4,6,1,3,615,0,3,3,6,100,32,89,265,1111,222,33533543,6546,65656};
		chooseSort(aIntegers);
		Arrays.asList(aIntegers).stream().peek(v -> {System.out.print(v + " ");}).count();	
	}
	
	public static void chooseSort(Integer[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			for (int j = length - 1; j > i; j --) {
				if (array[i] > array[j]) {
					swap(array, i, j);
				}
			}
		}
	}
	
	private static void swap(Integer[] array, int i, int j) {
		array[i] = array[i] + array[j];
		array[j] = array[i] - array[j];
		array[i] = array[i] - array[j];
	}

}
