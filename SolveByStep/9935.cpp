#include <iostream>

using namespace std;

int main()
{
	//입출력 속도 상승을 위한 메소드
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//입력 받기
	string input1, input2;
	cin >> input1 >> input2;

	string result = "";

	//문자 하나씩 탐색
	for (char c : input1)
	{
		//스택에 삽입.
		result += c;

		//현 문자가 폭탄의 마지막 문자와 같다면
		if (c == input2[input2.length() - 1])
		{
			int cnt = 0;
			bool isBomb = false;

			//폭탄의 마지막문자부터 첫번째 문자까지 스택과 비교(역순 비교)
			for (string::reverse_iterator iter = input2.rbegin(); iter != input2.rend(); iter++)
			{
				//역순으로 탐색한 폭탄의 문자와 스택에 쌓인 문자가 동일하다면 
				if (*iter == result[result.length() - (cnt + 1)])
				{
					cnt++;
					isBomb = true;
				}
				//동일하지 않은 경우 폭탄이 아니므로 패스
				else
				{
					isBomb = false;
					break;
				}
			}

			//스택을 탐색했는데, 폭탄의 문자와 완전히 일치한다면 폭탄이므로, 폭탄의 위치를 삭제함.
			if (isBomb)
			{
				result.erase(result.length() - cnt, cnt);
			}
		}
	}

	//결과값이 빈 경우 예외처리
	if (result == "")
		result = "FRULA";

	cout << result;

	return 0;
}


//간단한 방법
//제한시간이 1초이기 때문에 O(n^2)인 알고리즘으로는 어림도 없다.

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