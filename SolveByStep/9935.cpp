#include <iostream>

using namespace std;

int main()
{
	//����� �ӵ� ����� ���� �޼ҵ�
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//�Է� �ޱ�
	string input1, input2;
	cin >> input1 >> input2;

	string result = "";

	//���� �ϳ��� Ž��
	for (char c : input1)
	{
		//���ÿ� ����.
		result += c;

		//�� ���ڰ� ��ź�� ������ ���ڿ� ���ٸ�
		if (c == input2[input2.length() - 1])
		{
			int cnt = 0;
			bool isBomb = false;

			//��ź�� ���������ں��� ù��° ���ڱ��� ���ð� ��(���� ��)
			for (string::reverse_iterator iter = input2.rbegin(); iter != input2.rend(); iter++)
			{
				//�������� Ž���� ��ź�� ���ڿ� ���ÿ� ���� ���ڰ� �����ϴٸ� 
				if (*iter == result[result.length() - (cnt + 1)])
				{
					cnt++;
					isBomb = true;
				}
				//�������� ���� ��� ��ź�� �ƴϹǷ� �н�
				else
				{
					isBomb = false;
					break;
				}
			}

			//������ Ž���ߴµ�, ��ź�� ���ڿ� ������ ��ġ�Ѵٸ� ��ź�̹Ƿ�, ��ź�� ��ġ�� ������.
			if (isBomb)
			{
				result.erase(result.length() - cnt, cnt);
			}
		}
	}

	//������� �� ��� ����ó��
	if (result == "")
		result = "FRULA";

	cout << result;

	return 0;
}


//������ ���
//���ѽð��� 1���̱� ������ O(n^2)�� �˰������δ� ��� ����.

//int main()
//{
//	ios::sync_with_stdio(false);
//	cin.tie(NULL);
//	cout.tie(NULL);
//
//	string input1, input2;
//	cin >> input1 >> input2;
//
//	while (1)
//	{
//		int index = input1.find(input2);
//		if (index == -1)
//		{
//			break;
//		}
//		else
//		{
//			input1.replace(index, input2.length(), "");
//		}
//	}
//
//	if (input1 == "")
//	{
//		input1 = "FRULA";
//	}
//
//	cout << input1;
//
//	return 0;
//}