//#11050
//
//����
//���� ��� 1
//
//����
//�ڿ��� �� ���� �� �־����� �� ���� ��� �� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
//
//�Է�
//ù° �ٿ� �� �� �־�����. (1 ��  �� 10, 0 ��  ��)
//
//���
//(N/K)�� ����Ѵ�.

#include <iostream>
int main()
{
	int n, k;
	double result = 1;
	std::cin >> n >> k;
	for (int i = 0; i < k; i++)
	{
		result *= (n - i);
		result /= (k - i);
	}
	std::cout << result << "\n";
	return 0;
}