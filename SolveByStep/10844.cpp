//#10844
//
//제목
//쉬운 계단 수
//
//문제
//
//45656이란 수를 보자.
//
//이 수는 인접한 모든 자리수의 차이가 1이 난다.이런 수를 계단 수라고 한다.
//
//세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.
//
//N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
//입력
//
//첫째 줄에 N이 주어진다.N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
//출력
//
//첫째 줄에 정답을 1, 000, 000, 000으로 나눈 나머지를 출력한다.

//일차원 배열로, 규칙성을 찾아 점화식을 구하려 했으나 실패함.
//그래서 검색해서 알아봤다.
//이차원 배열을 쓸 생각을 전혀 못했다.

//알고리즘은 다음과 같다.
//숫자의 끝이 0이면, N+1에서는 1만 만들 수 있다.
//숫자의 끝이 9이면, N+1에서는 8만 만들 수 있다.
//숫자의 끝이 0과 9가 아니면, 해당 숫자에서 -1을 한것, +1을 한것, 두개를 만들 수있다.
//이 규칙을 사용해서, 문제를 해결하는 것이 키 포인트였다.
//규칙까진 찾았으나, 이차원 배열을 사용할 생각을 못했음.

//arr[i][j]에서 i는 N, 숫자의 길이를 의미한다. j는 마지막 숫자를 의미한다.
//이 배열은 N길이의 숫자들 중 마지막 숫자가 j인 숫자들의 개수를 담는다.
//먼저 N=1일때는 1,2,3,4,5,6,7,8,9밖에 못만드니까, arr[1][0~9] = 1로 세팅한다.
//N=2부터는, 다음과 같은 점화식이 성립한다. if(j==0) arr[i][j] = arr[i-1][1]
//if(j==9) arr[i][j] = arr[i-1][8]
//else arr[i][j] = arr[i-1][j-1] + arr[i-1][j+1]
//즉, 마지막 숫자가 0이나 9일때는 그 다음에 올 숫자가 하나밖에 없으므로, 개수가 유지되고, 나머지 수는 x2씩 되는것임.

//그리고 숫자가 너무 빨리 커지기 때문에 모듈러 연산으로 1000000000을 나누고 나머지를 저장한다.
//모듈러 연산의 특성으로, 연산 도중에 모듈러 연산을 해도, 결과값에서 모듈러 연산을 하는 것과 같은 값이 나온다.
//이것 또한 검색해서 알아낸 점이라, 아마 제대로 된 알고리즘을 혼자 생각해냈어도 이것 때문에 틀렸을 것이다.
//나중에 다시 풀어보자.

#include <iostream>

using namespace std;

int arr[101][10];
const int DIVIDE = 1000000000;

void init()
{
	for (int j = 1; j < 10; j++)
	{
		arr[1][j] = 1;
	}
}

void calc(int n)
{
	for (int i = 2; i <= n; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			if (j == 0)
			{
				arr[i][0] = arr[i - 1][1] % DIVIDE;
			}
			else if (j == 9)
			{
				arr[i][9] = arr[i - 1][8] % DIVIDE;
			}
			else
			{
				arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j + 1]) % DIVIDE;
			}
		}
	}
}

int getSum(int n)
{
	int sum = 0;
	for (int j = 0; j < 10; j++)
	{
		sum = (sum + arr[n][j]) % DIVIDE;
	}
	return sum;
}

int main()
{
	init();

	int input;
	cin >> input;

	calc(input);

	cout << getSum(input) << '\n';

	return 0;
}