import java.util.Arrays;

class Solution {
  public int[] solution(int[] arr) {
    int[] sorted = Arrays.copyOf(arr, arr.length);
    quickSort(sorted, 0, sorted.length - 1);
    return sorted;
  }

  private void quickSort(int[] arr, int left, int right){
    if(left >= right)
      return;

    int pivot = divide(arr, left, right);

    quickSort(arr, left, pivot - 1);
    quickSort(arr, pivot + 1, right);
  }

  private int divide(int[] arr, int left, int right){
    int leftIdx = left;
    int rightIdx = right;
    int pivot = arr[left];

    while(leftIdx < rightIdx){
      while(arr[rightIdx] > pivot && rightIdx > left)
        rightIdx--;

      while(arr[leftIdx] <= pivot && leftIdx < rightIdx)
        leftIdx++;

      swap(arr, leftIdx, rightIdx);
    }

    arr[left] = arr[leftIdx];
    arr[leftIdx] = pivot;
    return leftIdx;
  }

  private void swap(int[] arr, int indexA, int indexB){
    int temp = arr[indexA];
    arr[indexA] = arr[indexB];
    arr[indexB] = temp;
  }
}