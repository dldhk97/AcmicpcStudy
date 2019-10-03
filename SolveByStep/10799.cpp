#include <iostream>
#include <stack>

using namespace std;

int main()
{
	//입출력 속도 상승을 위한 메소드
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string input;
	cin >> input;

	int result = 0;

	stack<char> stk;

	for (int i = 0; i < input.length(); i++)
	{
		if (input[i] == '(')
		{
			stk.push('(');
		}
		else
		{
			stk.pop();
			if (input[i - 1] == '(')
			{
				result += stk.size();
			}
			else
			{
				result++;
			}
		}
	}

	cout << result;

	return 0;
}