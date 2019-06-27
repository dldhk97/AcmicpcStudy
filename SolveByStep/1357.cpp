#include <iostream>
#include <string>

std::string reversing(std::string input)
{
	std::string result = "";
	for (int i = input.length() - 1; i >= 0; i--)
	{
		result += input[i];
	}
	return result;
}

int main()
{
	std::string X, Y;
	std::cin >> X >> Y;

	std::string rX_s = reversing(X);
	int rx_i = std::stoi(rX_s);

	std::string rY_s = reversing(Y);
	int ry_i = std::stoi(rY_s);

	int xy_i = rx_i + ry_i;
	std::string rXY_s = reversing(std::to_string(xy_i));
	int rXY_i = std::stoi(rXY_s);
	std::cout << rXY_i;

	return 0;
}