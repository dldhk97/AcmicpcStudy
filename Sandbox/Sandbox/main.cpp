#include <iostream>
#include <string>

int main()
{
	int n;
	scanf("%d", &n);
	getchar();

	for (int i = 0; i < n; i++)
	{
		std::string url, protocol, host;
		std::string port = "<default>";
		std::string path = "<default>";

		std::getline(std::cin, url);

		protocol = url.substr(0, url.find("://"));
		url = url.substr(url.find("://") + 3);

		int portStartIdx = url.find(":");
		int pathStartIdx = url.find("/");

		if (portStartIdx == -1 && pathStartIdx == -1)	//port�� path ��� �������� ����.
		{
			host = url;
		}
		else if (portStartIdx != -1 && pathStartIdx != -1)	//port�� path ��� ����
		{
			if (portStartIdx < pathStartIdx)			// ':' ���� '/' �� ������ ���(port�� path ��� ����)
			{
				host = url.substr(0, portStartIdx);
				url = url.substr(portStartIdx + 1);
				pathStartIdx = url.find("/");
				port = url.substr(0, pathStartIdx);
				path = url.substr(pathStartIdx + 1);
			}
			else										// '/' ���� ':' �� �����°��(path�� ����)
			{
				host = url.substr(0, pathStartIdx);
				path = url.substr(pathStartIdx + 1);
			}
		}
		else if (portStartIdx != -1)					//port�� ����
		{
			host = url.substr(0, portStartIdx);
			port = url.substr(portStartIdx + 1);
		}
		else if (pathStartIdx != -1)					//path�� ����
		{
			host = url.substr(0, pathStartIdx);
			path = url.substr(pathStartIdx + 1);
		}
		else
		{
			//url = "ERORR";
			//protocol = "ERROR";
		}

		//print
		std::cout << "URL #" << i + 1 << "\n";
		std::cout << "Protocol = " << protocol << "\n";
		std::cout << "Host     = " << host << "\n";
		std::cout << "Port     = " << port << "\n";
		std::cout << "Path     = " << path;

		if (i != n - 1)
			std::cout << "\n\n";
	}
	return 0;
}