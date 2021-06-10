package sort;

import java.util.Arrays;

/**
 * Created by supaur on 2021/5/6 15:22
 *
 * @author supaur
 */
public class MySort {

    public static void main(String[] args) {
        int[] aIntegers = {2, 4, 6, 1, 3, 615, 0, 3, 3, 6, 100, 32, 89, 265, 1111, 222, 33533543, 6546, 65656};
        quickSort(aIntegers, 0, aIntegers.length - 1);
        System.out.println(Arrays.toString(aIntegers));
    }

    public void swapArr(int[] arr, int i, int j) {
        int temp = arr[i] + arr[j];
        arr[j] = arr[i];
        arr[i] = temp - arr[i];
    }

//    public int[] mergeSort(int[] arr) {
//        if (arr.length == 2) {
//            if (arr[0] > arr[1]) {
//                swapArr(arr, 0, 1);
//            }
//          return arr;
//        }
//        int[] tempArr = new int[arr.length];
//        int middle = arr.length / 2;
//        int[] left = mergeSort(System.arraycopy(arr, 0, middle));
//    }

    public static void quickSort(int[] arr, int left, int right) {
        // 递归结束时间点
        if (left > right) {
            return;
        }
        // 保留原始位置
        int originLeft = left;
        int originRight = right;
        // 标志位数值
        int flagNum = arr[left];
        while (left < right) {
            // 遍历右边
            while (right > left && arr[right] >= flagNum) {
                right--;
            }
            arr[left] = arr[right];
            while (right > left && arr[left] <= flagNum) {
                left++;
            }
            arr[right] = arr[left];
        }
        // 集准点归位
        arr[right] = flagNum;
        quickSort(arr, originLeft, left - 1);
        quickSort(arr, right + 1, originRight);
    }

}
