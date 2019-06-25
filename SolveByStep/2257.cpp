#include <iostream>
#include <string>
#include <stack>

int main()
{
	std::stack<std::string> myStack;
	std::stack<int> resultStack;
	std::string formula;
	std::getline(std::cin, formula);

	int multiply = 1;
	int result = 0;

	for (int i = 0; i < formula.length(); i++)
	{
		if (formula[i] == ')')
		{
			int temp = 0;
			std::string cur = myStack.top();
			while (cur != "(")
			{
				cur = myStack.top();
				if ("2" <= cur && cur <= "9" && cur.find("@") == -1)
				{
					multiply = std::stoi(cur);
				}
				else if (cur == "H")
				{
					temp += 1 * multiply;
					multiply = 1;
				}
				else if (cur == "C")
				{
					temp += 12 * multiply;
					multiply = 1;
				}
				else if (cur == "O")
				{
					temp += 16 * multiply;
					multiply = 1;
				}
				else if (cur == "(")
				{
					break;
				}
				else
				{
					int value = std::stoi(cur.substr(0, cur.length() - 1));
					temp += value * multiply;
					multiply = 1;
				}
				myStack.pop();
			}
			myStack.pop();				// '('여는괄호 버리기
			myStack.push(std::to_string(temp) + "@");

		}
		else
		{
			std::string a(1, formula[i]);
			myStack.push(a);
		}
	}

	//스택에 남아있으면 다 계산
	while (!myStack.empty())
	{
		std::string cur = myStack.top();
		if ("2" <= cur && cur <= "9" && cur.find("@") == -1)
		{
			multiply = std::stoi(cur);
		}
		else if (cur == "H")
		{
			result += 1 * multiply;
			multiply = 1;
		}
		else if (cur == "C")
		{
			result += 12 * multiply;
			multiply = 1;
		}
		else if (cur == "O")
		{
			result += 16 * multiply;
			multiply = 1;
		}
		else
		{
			int value = std::stoi(cur.substr(0, cur.length() - 1));
			result += value * multiply;
			multiply = 1;
		}
		myStack.pop();
	}

	std::cout << result;

	return 0;
}