//#2108
//
//제목
//통계학
//
//문제
//수를 처리하는 것은 통계학에서 상당히 중요한 일이다.통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다.단, N은 홀수라고 가정하자.
//
//산술평균 : N개의 수들의 합을 N으로 나눈 값
//중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
//최빈값 : N개의 수들 중 가장 많이 나타나는 값
//범위 : N개의 수들 중 최댓값과 최솟값의 차이
//N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 수의 개수 N(1 ≤ N ≤ 500, 000)이 주어진다.그 다음 N개의 줄에는 정수들이 주어진다.입력되는 정수의 절댓값은 4,000을 넘지 않는다.
//
//출력
//첫째 줄에는 산술평균을 출력한다.소수점 이하 첫째 자리에서 반올림한 값을 출력한다.
//
//둘째 줄에는 중앙값을 출력한다.
//
//셋째 줄에는 최빈값을 출력한다.여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.
//
//넷째 줄에는 범위를 출력한다.

#include <iostream>
#include <vector>
#include <algorithm>

bool compare(const std::pair<int, int> &a, const std::pair<int, int> &b)
{
	return a.second > b.second;
}

int main()
{
	int n;
	std::vector<std::pair<int, int>> dataArr;
	std::vector<int> tempArr;

	double sum = 0.0;
	scanf("%d", &n);

	int userInput, range;
	bool isNew;
	for (int i = 0; i < n; i++)
	{
		isNew = true;
		scanf("%d", &userInput);
		sum += userInput;
		for (int j = 0; j < dataArr.size(); j++)
		{
			if (userInput == dataArr[j].first)
			{
				dataArr[j].second++;
				isNew = false;
				break;
			}
		}
		if (isNew)
			dataArr.push_back(std::pair<int, int>(userInput, 0));
		tempArr.push_back(userInput);
	}
	int avg;
	if (sum >= 0)
		avg = sum / n + 0.5;
	else
		avg = sum / n - 0.5;
	printf("%d\n", avg);

	std::sort(dataArr.begin(), dataArr.end());
	std::sort(tempArr.begin(), tempArr.end());

	range = tempArr[n - 1] - tempArr[0];

	printf("%d\n", tempArr[n / 2]);

	std::sort(dataArr.begin(), dataArr.end(), compare);
	int mode;
	if (n == 1)
	{
		mode = dataArr[0].first;
	}
	else
	{
		if (dataArr[0].second == dataArr[1].second)
			mode = dataArr[1].first;
		else
			mode = dataArr[0].first;
	}

	printf("%d\n", mode);

	printf("%d\n", range);

	return 0;
}