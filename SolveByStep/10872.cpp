//#10872
//
//제목
//팩토리얼
//
//문제
//0보다 크거나 같은 정수 N이 주어진다.이때, N!을 출력하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 정수 N(0 ≤ N ≤ 12)가 주어진다.
//
//출력
//첫째 줄에 N!을 출력한다.

#include <iostream>
int main()
{
	int n, result = 1;
	std::cin >> n;
	for (int i = n; i > 0; i--)
	{
		result *= i;
	}
	std::cout << result << "\n";
	return 0;
}