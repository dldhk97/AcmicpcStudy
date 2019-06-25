#include <iostream>

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n * 2 - 1; j++)
		{
			if (j < n)
			{
				if (j >= i)
				{
					printf("*");
				}
				else
				{
					printf(" ");
				}
			}
			else if (j == n)
			{
				printf("*");
			}
			else
			{
				if (j <= n * 2 - i)
				{
					printf("*");
				}
			}
		}
		if (i != n)
			printf("\n");
	}
	return 0;
}