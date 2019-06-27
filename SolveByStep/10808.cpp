#include <iostream>
#include <string>

int main()
{
	std::string S;
	std::cin >> S;

	int arr[26] = {};

	for (int i = 0; i < S.length(); i++)
	{
		arr[S[i] - 'a']++;
	}

	for (int i = 0; i < 26; i++)
	{
		std::cout << arr[i] << " ";
	}

	return 0;
}