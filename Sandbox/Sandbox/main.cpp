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

bool compare(const std::pair<int, int> &a, const std::pair<int, int> &b)
{
	if (a.second == b.second)
		return a.first < b.first;
	return a.second > b.second;
}

int main()
{
	int n;
	std::vector<std::pair<int,int>> dataArr;

	double sum = 0.0;
	scanf("%d", &n);

	int userInput, range;
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &userInput);
		dataArr.push_back(std::pair<int,int>(userInput, 0));
		sum += dataArr[i].first;
		for (int j = 0; j < i; j++)
			if (dataArr[i].first == dataArr[j].first)
			{
				dataArr[j].second++;
				dataArr[i].second++;
			}
	}
	int avg = round(sum / n);
	printf("avg = %d\n", avg);

	std::sort(dataArr.begin(), dataArr.end());

	range = dataArr[n - 1].first - dataArr[0].first;

	printf("middleValue = %d\n", dataArr[n / 2]);

	std::sort(dataArr.begin(), dataArr.end(), compare);
	int mode;
	if (n == 1)
	{
		mode = dataArr[0].first;
	}
	else
	{
		if (dataArr[0].second == dataArr[1].second)
			mode = dataArr[n - 2].first;
		else
			mode = dataArr[0].first;
	}
	

	printf("mode = %d\n", mode);

	printf("range = %d\n", range);

	return 0;
}