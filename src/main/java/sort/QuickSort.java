package sort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		Integer[] aIntegers = {2,4,6,1,3,615,0,3,3,6,100,32,89,265,1111,222,33533543,6546,65656};
		quickSort(aIntegers, 0, aIntegers.length-1);
		Arrays.asList(aIntegers).stream().peek(v -> {System.out.print(v + " ");}).count();
	}

	public static void quickSort(Integer[] array, int left, int right) {
		final int left_ = left;
		final int right_ = right;
		if(left <= right){
			int pivot = array[left];    // 取第一个元素为基准
			int index = 0; // index做标记，如果没有交换直接返回
			while(left != right) {
				while(true) {
					if (left < right && array[right] >= pivot) {
						right --;
					} else {
						index ++;
						break;
					}
				}
				array[left] = array[right];
				while(true) {
					if (left < right &&  array[left] <= pivot) {
						left++;
					} else {
						index ++;
						break;
					}
				}
				array[right] = array[left];
//				while (left < right && array[right] >= pivot) right--;
//				array[left] = array[right];
//				while (left < right &&  array[left] <= pivot) left++;
//				array[right] = array[left];
			}


			if (index == 0) {
				return;
			}
			array[right] = pivot;    // 集准点归位
			quickSort(array, left_, left - 1);    // 递归左分区
			quickSort(array, right + 1, right_);    // 递归右分区
			Arrays.asList(array).stream().peek(v -> {System.out.print(v + " ");}).count();
			System.out.println();
		}
	}
}
