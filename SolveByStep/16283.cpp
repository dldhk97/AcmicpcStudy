#include <iostream>

using namespace std;

int main()
{
	//입출력 속도 상승을 위한 메소드
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int a, b, n, w;
	cin >> a >> b >> n >> w;

	int s, g, cnt = 0;

	for (int i = 1; i < n; i++)
	{
		if (a * i + b * (n - i) == w)
		{
			cnt++;
			s = i;
			g = n - i;
		}
	}

	if (cnt == 0 || cnt >= 2)
	{
		cout << -1;
	}
	else
	{
		cout << s << " " << g;
	}

	return 0;
}

