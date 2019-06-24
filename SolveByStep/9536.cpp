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
	for (int i = 0; i < animalCnt; i++)
	{
		std::string targetSound = animalSounds[i];
		int startIndex = result.find(targetSound);
		while (startIndex != -1)
		{
			result = result.replace(startIndex, targetSound.length() + 1, "");
			startIndex = result.find(targetSound);
		}

	}
	return result;
}

//접근이 틀렸음.
//pow는 삭제하면 안되는데 ow를 찾아 지울때 같이 지운다.

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