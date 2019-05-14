//#2748
//
//제목
//피보나치 수 2
//
//문제
//피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다.그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.
//
//이를 식으로 써보면 Fn = Fn - 1 + Fn - 2 (n >= 2)가 된다.
//
//n = 17일때 까지 피보나치 수를 써보면 다음과 같다.
//
//0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597
//
//n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 n이 주어진다.n은 90보다 작거나 같은 자연수이다.
//
//출력
//첫째 줄에 n번째 피보나치 수를 출력한다.

#include <iostream>

long long repeat_fibo(int input)									//반복함수로 피보나치 수를 구합니다.
{
	if (input == 0) return 0;									//첫번째 피보나치 수를 구하려고할 경우 0을 반환합니다.
	if (input == 1) return 1;									//두번째 피보나치 수를 구하려고할 경우 1을 반환합니다.

	long long previous_num_1 = 0;									//Pn-2을 저장하는 변수입니다.
	long long previous_num_2 = 1;									//Pn-1을 저장하는 변수입니다.

	for (int i = 0; i < input - 1; i++)							//첫번째와 두번째 피보나치 수를 구하려고하는 2가지 경우는 제외합니다.
	{
		long long temp = previous_num_2;							//임시 변수에 Pn-1값을 저장해놓습니다.
		previous_num_2 = previous_num_1 + previous_num_2;		//새로운 값을 Pn-1에 저장합니다. (이것은 다음에 사용할 Pn-1이며, 새로 구한 피보나치수이기도 합니다)
		previous_num_1 = temp;									//새로운 Pn-2는 이전의 Pn-1값으로 저장합니다. (이것은 다음에 사용할 Pn-2입니다)
	}															//즉, A, B, C ---> A(버려짐), B, C, D(새 피보나치수)
	return previous_num_2;
}

int main()
{
	int n;
	std::cin >> n;
	std::cout << repeat_fibo(n);
	return 0;
}