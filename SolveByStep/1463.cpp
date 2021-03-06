//#1463
//
//제목
//1로 만들기
//
//문제
//
//정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
//
//X가 3으로 나누어 떨어지면, 3으로 나눈다.
//X가 2로 나누어 떨어지면, 2로 나눈다.
//1을 뺀다.
//
//정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.연산을 사용하는 횟수의 최솟값을 출력하시오.
//입력
//
//첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 정수 N이 주어진다.
//출력
//
//첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

#include <iostream>

using namespace std;

int arr[1000001];

int getMin(int x, int y)
{
	return x < y ? x : y;
}

int make(int x)
{
	//주어진 값에서 -1 연산을 하면, 주어진 값 바로 앞의 값(x-1)이 된다. 그 값의 연산을 그대로 하는 경우.
	//주어진 값 바로 앞의 값의 연산횟수 + 1이다.
	arr[x] = arr[x - 1] + 1;

	//주어진 값이 2로 나눌 수 있는 경우, 3으로 나누었을 때와 동일함.
	if (x % 2 == 0)
		arr[x] = getMin(arr[x], arr[x / 2] + 1);

	//주어진 값이 3으로 나눌 수 있는 경우
	if (x % 3 == 0)
	{
		//3으로 나누고, 결과값의 연산을 그대로 하는경우
		int divdeBy3 = arr[x / 3] + 1;

		//주어진 값에서 -1 연산을 하고 결과값의 연산을 따라가는 경우와, 3으로 나누고 결과값을 따라가는 경우 중 최솟값을 사용한다.
		arr[x] = getMin(arr[x], divdeBy3);
	}

	return arr[x];
}


int main()
{
	arr[1] = 0;
	arr[2] = 1;
	arr[3] = 1;

	int input;
	cin >> input;

	for (int i = 2; i <= input; i++)
	{
		make(i);
	}

	cout << arr[input] << '\n';;

	return 0;
}

