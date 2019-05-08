//#2108
//
//����
//�����
//
//����
//���� ó���ϴ� ���� ����п��� ����� �߿��� ���̴�.����п��� N���� ���� ��ǥ�ϴ� �⺻ ��谪���� ������ ���� �͵��� �ִ�.��, N�� Ȧ����� ��������.
//
//������ : N���� ������ ���� N���� ���� ��
//�߾Ӱ� : N���� ������ �����ϴ� ������ �������� ��� �� �߾ӿ� ��ġ�ϴ� ��
//�ֺ� : N���� ���� �� ���� ���� ��Ÿ���� ��
//���� : N���� ���� �� �ִ񰪰� �ּڰ��� ����
//N���� ���� �־����� ��, �� ���� �⺻ ��谪�� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
//
//�Է�
//ù° �ٿ� ���� ���� N(1 �� N �� 500, 000)�� �־�����.�� ���� N���� �ٿ��� �������� �־�����.�ԷµǴ� ������ ������ 4,000�� ���� �ʴ´�.
//
//���
//ù° �ٿ��� �������� ����Ѵ�.�Ҽ��� ���� ù° �ڸ����� �ݿø��� ���� ����Ѵ�.
//
//��° �ٿ��� �߾Ӱ��� ����Ѵ�.
//
//��° �ٿ��� �ֺ��� ����Ѵ�.���� �� ���� ������ �ֺ� �� �� ��°�� ���� ���� ����Ѵ�.
//
//��° �ٿ��� ������ ����Ѵ�.

#include <iostream>
#include <vector>
#include <algorithm>

const int MAX_ABS = 4000;

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

//��������������������������
//���ͷ� �ٽ�¬�ô�...
//��������������������������

int main()
{
	int n;
	int *dataArr;
	int commonCnt[MAX_ABS * 2 + 1];

	for (int i = 0; i < MAX_ABS * 2 + 1; i++)
		commonCnt[i] = 0;

	double sum = 0.0;
	scanf("%d", &n);
	dataArr = new int[n];

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &dataArr[i]);
		sum += dataArr[i];
		commonCnt[dataArr[i] + MAX_ABS]++;
	}
	int avg = round(sum / n);
	printf("avg = %d\n", avg);

	quickSort(dataArr, 0, n - 1);
	printf("middleValue = %d\n", dataArr[n / 2]);

	//int mode = getMode(commonCnt) - 4000;
	//printf("modeValue = %d\n", mode);

	delete[] dataArr;
	return 0;
}