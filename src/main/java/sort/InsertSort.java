package sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author wb-zyc501131
 *
 */
public class InsertSort {
	public static void main(String[] args) {
		Integer[] aIntegers = {2,4,6,1,3,615,0};
		insertSort(aIntegers);
		Arrays.asList(aIntegers).stream().peek(System.out :: println).count();
	}
	/**
	 *1.外层代表有顺序的前面i+1个数
	 *2.内层循环通过当前的第j+1个数字，去跟前面的有序的数字比较，大则直接换，小于继续往前比较
	 * @param array
	 */
	public static void insertSort(Integer[] array) {
		for (int i = 1;i < array.length;i++) {
			for (int j = i;j > 0 && array[j] < array[j-1]; j--) {
				array[j] = array[j] + array[j-1];
				array[j-1] = array[j] - array[j-1];
				array[j] = array[j] - array[j-1];
			}
		}
	}
}
