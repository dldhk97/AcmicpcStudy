//#10989
//
//����
//�� �����ϱ� 3
//
//����
//N���� ���� �־����� ��, �̸� ������������ �����ϴ� ���α׷��� �ۼ��Ͻÿ�.
//
//�Է�
//ù° �ٿ� ���� ���� N(1 �� N �� 10, 000, 000)�� �־�����.��° �ٺ��� N���� �ٿ��� ���ڰ� �־�����.�� ���� 10, 000���� �۰ų� ���� �ڿ����̴�.
//
//���
//ù° �ٺ��� N���� �ٿ� ������������ ������ ����� �� �ٿ� �ϳ��� ����Ѵ�.

#include <iostream>

using namespace std;

const int MAX_INTEGER = 10000;		//�Է°��� �ִ밪

int main()
{
	int n;
	int count[MAX_INTEGER + 1];

	cin >> n;
	for (int i = 0; i < MAX_INTEGER + 1; i++)
		count[i] = 0;

	int userInput;
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &userInput);
		count[userInput - 1]++;
	}

	for (int i = 0; i < MAX_INTEGER + 1; i++)
	{
		if (count[i] != 0)
		{
			for (int j = 0; j < count[i]; j++)
			{
				printf("%d\n", i + 1);
			}
		}
	}

	return 0;
}