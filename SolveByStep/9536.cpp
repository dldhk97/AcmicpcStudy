#include <iostream>
#include <string>

std::string getUsefulSound(std::string originalSound)
{
	int startIndex = originalSound.find("goes") + 5;

	return originalSound.substr(startIndex, originalSound.length() - startIndex);
}

std::string getFoxSound(std::string originalSound, std::string* animalSounds, int animalCnt)
{
	/*std::string result = originalSound;
	for (int i = 0; i < animalCnt; i++)
	{
		std::string targetSound = animalSounds[i];
		int startIndex = result.find(targetSound);
		while (startIndex != -1)
		{
			result = result.replace(startIndex, targetSound.length() + 1, "");
			startIndex = result.find(targetSound);
		}

	}*/

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
	resultArr[cnt] = result.substr(startIndex, result.length() - startIndex);

	result = "";

	for (int i = 0; i < cnt; i++)
	{
		for (int j = 0; j < animalCnt; j++)
		{
			if (resultArr[i] == animalSounds[j])
			{
				resultArr[i] = "";
			}
		}
	}

	for (int i = 0; i < cnt - 1; i++)
	{
		if (resultArr[i] != "")
			result += resultArr[i] + " ";
	}
	result += resultArr[cnt];

	return result;
}

int main()
{
	int t;

	std::cin >> t;

	for (int i = 0; i < t; i++)
	{
		std::string sound;
		std::string animals[100];
		int animalCnt = 0;

		std::cin.ignore();
		std::cin.clear();

		std::getline(std::cin, sound);

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
		std::cout << getFoxSound(sound, animals, animalCnt);

	}


	return 0;
}