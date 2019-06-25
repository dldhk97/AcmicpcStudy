#include <iostream>
#include <string>

int main()
{
	int testCase;

	scanf("%d", &testCase);

	for (int i = 0; i < testCase; i++)
	{
		int charHP, charMP, charATK, charDEF;
		int eqHP, eqMP, eqATK, eqDEF;
		int resultHP, resultMP, resultATK, resultDEF;

		std::cin >> charHP >> charMP >> charATK >> charDEF >> eqHP >> eqMP >> eqATK >> eqDEF;

		resultHP = (charHP + eqHP) < 1 ? 1 : (charHP + eqHP);
		resultMP = (charMP + eqMP) < 1 ? 1 : (charMP + eqMP);
		resultATK = (charATK + eqATK) < 0 ? 0 : (charATK + eqATK);
		resultDEF = (charDEF + eqDEF);

		int power = 1 * resultHP + 5 * resultMP + 2 * resultATK + 2 * resultDEF;

		printf("%d\n", power);
	}
	return 0;
}