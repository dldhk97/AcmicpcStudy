#include <iostream>

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 1; i <= n * 2 - 1; i++)
	{
		for (int j = 1; j <= n * 2 - 1; j++)
		{
			if (i < n)
			{
				if (j < n)
				{
					if (j > n - i)
					{
						printf("*");
					}
					else
					{
						printf(" ");
					}
				}
				else
				{
					if (j >= n * 2 - i)
					{
						printf("*");
					}
				}
			}
			else if (i == n)
			{
				printf("*");
			}
			else
			{
				if (j < n)
				{
					if (j > i - n)
					{
						printf("*");
					}
					else
					{
						printf(" ");
					}
				}
				else
				{
					if (j >= i)
					{
						printf("*");
					}
				}
			}
		}
		if (i != n * 2 - 1)
			printf("\n");
	}
	return 0;
}