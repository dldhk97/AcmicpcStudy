import java.util.Arrays;

class Solution {
  public int[] solution(int[] arr) {
    int[] sorted = Arrays.copyOf(arr, arr.length);
    mergeSort(sorted, 0, sorted.length - 1);
    return sorted;
  }

  private void mergeSort(int[] arr, int left, int right){
    if(left >= right)
      return;

    int mid = (left + right) / 2;

    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);

    merge(arr, left, right);
  }

  private void merge(int[] arr, int left, int right){
    int mid = (left + right) / 2;
    int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
    int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

    int leftIdx = 0;
    int rightIdx = 0;
    int idx = left;

    while(leftIdx < leftArr.length && rightIdx < rightArr.length){
      if(leftArr[leftIdx] < rightArr[rightIdx]){
        arr[idx] = leftArr[leftIdx++];
      }
      else{
        arr[idx] = rightArr[rightIdx++];
      }
      idx++;
    }

    while(leftIdx < leftArr.length)
      arr[idx++] = leftArr[leftIdx++];

    while(rightIdx < rightArr.length)
      arr[idx++] = rightArr[rightIdx++];
  }
}