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
	return a.second > b.second;
}

int main()
{
	int n;
	std::vector<std::pair<int, int>> dataArr;
	std::vector<int> tempArr;

	double sum = 0.0;
	scanf("%d", &n);

	int userInput, range;
	bool isNew;
	for (int i = 0; i < n; i++)
	{
		isNew = true;
		scanf("%d", &userInput);
		sum += userInput;
		for (int j = 0; j < dataArr.size(); j++)
		{
			if (userInput == dataArr[j].first)
			{
				dataArr[j].second++;
				isNew = false;
				break;
			}
		}
		if (isNew)
			dataArr.push_back(std::pair<int, int>(userInput, 0));
		tempArr.push_back(userInput);
	}
	int avg;
	if (sum >= 0)
		avg = sum / n + 0.5;
	else
		avg = sum / n - 0.5;
	printf("%d\n", avg);

	std::sort(dataArr.begin(), dataArr.end());
	std::sort(tempArr.begin(), tempArr.end());

	range = tempArr[n - 1] - tempArr[0];

	printf("%d\n", tempArr[n / 2]);

	std::sort(dataArr.begin(), dataArr.end(), compare);
	int mode;
	if (n == 1)
	{
		mode = dataArr[0].first;
	}
	else
	{
		if (dataArr[0].second == dataArr[1].second)
			mode = dataArr[1].first;
		else
			mode = dataArr[0].first;
	}

	printf("%d\n", mode);

	printf("%d\n", range);

	return 0;
}