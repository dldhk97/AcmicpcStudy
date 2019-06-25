#include <iostream>
#include <string>

std::string getUsefulSound(std::string originalSound)
{
	int startIndex = originalSound.find("goes") + 5;

	return originalSound.substr(startIndex, originalSound.length() - startIndex);
}

std::string getFoxSound(std::string originalSound, std::string* animalSounds, int animalCnt)
{
	std::string result = originalSound;
	std::string resultArr[100];

	int startIndex = 0;
	int cnt = 0;
	for (int i = 0; i < result.length(); i++)
	{
		if (result[i] == ' ')
		{
			resultArr[cnt++] = result.substr(startIndex, i - startIndex);
			startIndex = i + 1;
		}
	}
	resultArr[cnt++] = result.substr(startIndex, result.length() - startIndex);

	result = "";

	for (int i = 0; i < animalCnt; i++)
	{
		for (int j = 0; j < cnt; j++)
		{
			if (animalSounds[i] == resultArr[j])
			{
				resultArr[j] = "";
			}
		}
	}

	for (int i = 0; i < cnt; i++)
	{
		if (resultArr[i] != "")
		{
			result += resultArr[i] + " ";
		}
			
	}

	result = result.substr(0, result.length() - 1);

	return result;
}

int main()
{
	int t;

	scanf("%d", &t);
	getchar();

	for (int i = 0; i < t; i++)
	{
		std::string sound;
		std::string animals[100];
		int animalCnt = 0;

		std::getline(std::cin, sound);
		getchar();

		while (true)
		{
			std::string temp;
			std::getline(std::cin, temp);
			if (temp == "what does the fox say?")
			{
				break;
			}
			else
			{
				animals[animalCnt++] = getUsefulSound(temp);
			}
		}
		std::cout << getFoxSound(sound, animals, animalCnt) << "\n";

	}


	return 0;
}