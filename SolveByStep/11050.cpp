//#11050
//
//제목
//이항 계수 1
//
//문제
//자연수 과 정수 가 주어졌을 때 이항 계수 를 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 과 가 주어진다. (1 ≤  ≤ 10, 0 ≤  ≤)
//
//출력
//(N/K)를 출력한다.

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