//#2750
//
//����
//�� �����ϱ�
//
//����
//N���� ���� �־����� ��, �̸� ������������ �����ϴ� ���α׷��� �ۼ��Ͻÿ�.
//
//�Է�
//ù° �ٿ� ���� ���� N(1 �� N �� 1, 000)�� �־�����.��° �ٺ��� N���� �ٿ��� ���ڰ� �־�����.�� ���� ������ 1, 000���� �۰ų� ���� �����̴�.���� �ߺ����� �ʴ´�.
//
//���
//ù° �ٺ��� N���� �ٿ� ������������ ������ ����� �� �ٿ� �ϳ��� ����Ѵ�.

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