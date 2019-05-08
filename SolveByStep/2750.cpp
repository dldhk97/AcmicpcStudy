//#2750
//
//제목
//수 정렬하기
//
//문제
//N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 수의 개수 N(1 ≤ N ≤ 1, 000)이 주어진다.둘째 줄부터 N개의 줄에는 숫자가 주어진다.이 수는 절댓값이 1, 000보다 작거나 같은 정수이다.수는 중복되지 않는다.
//
//출력
//첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

#include <iostream>

void quickSort(int *dataArr, int start, int end)
{
	if (start >= end)
		return;

	int key = start;
	int i = start + 1, j = end, temp;

	while (i <= j)
	{
		while (i <= end && dataArr[i] <= dataArr[key])
			i++;
		while (j > start && dataArr[j] >= dataArr[key])
			j--;
		if (i > j)
		{
			temp = dataArr[j];
			dataArr[j] = dataArr[key];
			dataArr[key] = temp;
		}
		else
		{
			temp = dataArr[i];
			dataArr[i] = dataArr[j];
			dataArr[j] = temp;
		}
	}
	quickSort(dataArr, start, j - 1);
	quickSort(dataArr, j + 1, end);
}


int main()
{
	int n;
	int *dataArr;

	scanf("%d", &n);
	dataArr = new int[n];

	for (int i = 0; i < n; i++)
		scanf("%d", &dataArr[i]);

	quickSort(dataArr, 0, n - 1);

	for (int i = 0; i < n; i++)
		printf("%d\n", dataArr[i]);

	delete[] dataArr;
	return 0;
}