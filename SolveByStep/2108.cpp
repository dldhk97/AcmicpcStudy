//#2108
//
//薦鯉
//搭域俳
//
//庚薦
//呪研 坦軒馬澗 依精 搭域俳拭辞 雌雁備 掻推廃 析戚陥.搭域俳拭辞 N鯵税 呪研 企妊馬澗 奄沙 搭域葵拭澗 陥製引 旭精 依級戚 赤陥.舘, N精 筈呪虞壱 亜舛馬切.
//
//至綬汝液 : N鯵税 呪級税 杯聖 N生稽 蟹勧 葵
//掻肖葵 : N鯵税 呪級聖 装亜馬澗 授辞稽 蟹伸梅聖 井酔 益 掻肖拭 是帖馬澗 葵
//置朔葵 : N鯵税 呪級 掻 亜舌 弦戚 蟹展蟹澗 葵
//骨是 : N鯵税 呪級 掻 置奇葵引 置借葵税 託戚
//N鯵税 呪亜 爽嬢然聖 凶, 革 亜走 奄沙 搭域葵聖 姥馬澗 覗稽益轡聖 拙失馬獣神.
//
//脊径
//湛属 匝拭 呪税 鯵呪 N(1 ‖ N ‖ 500, 000)戚 爽嬢遭陥.益 陥製 N鯵税 匝拭澗 舛呪級戚 爽嬢遭陥.脊径鞠澗 舛呪税 箭奇葵精 4,000聖 角走 省澗陥.
//
//窒径
//湛属 匝拭澗 至綬汝液聖 窒径廃陥.社呪繊 戚馬 湛属 切軒拭辞 鋼臣顕廃 葵聖 窒径廃陥.
//
//却属 匝拭澗 掻肖葵聖 窒径廃陥.
//
//実属 匝拭澗 置朔葵聖 窒径廃陥.食君 鯵 赤聖 凶拭澗 置朔葵 掻 砧 腰属稽 拙精 葵聖 窒径廃陥.
//
//掛属 匝拭澗 骨是研 窒径廃陥.

#include <iostream>
#include <vector>
#include <algorithm>

const int MAX_ABS = 4000;

void quickSort(int *dataArr, int start, int end)
{
	if (start >= end)
		return;

	int key = start;
	int i = start + 1, j = end, temp;

	while (i <= j)
	{
		while (i <= end && dataArr[i] <= dataArr[key])
			i++;
		while (j > start && dataArr[j] >= dataArr[key])
			j--;
		if (i > j)
		{
			temp = dataArr[j];
			dataArr[j] = dataArr[key];
			dataArr[key] = temp;
		}
		else
		{
			temp = dataArr[i];
			dataArr[i] = dataArr[j];
			dataArr[j] = temp;
		}
	}
	quickSort(dataArr, start, j - 1);
	quickSort(dataArr, j + 1, end);
}

//たたたたたたたたたたたたた
//困斗稽 陥獣測獣陥...
//たたたたたたたたたたたたた

int main()
{
	int n;
	int *dataArr;
	int commonCnt[MAX_ABS * 2 + 1];

	for (int i = 0; i < MAX_ABS * 2 + 1; i++)
		commonCnt[i] = 0;

	double sum = 0.0;
	scanf("%d", &n);
	dataArr = new int[n];

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &dataArr[i]);
		sum += dataArr[i];
		commonCnt[dataArr[i] + MAX_ABS]++;
	}
	int avg = round(sum / n);
	printf("avg = %d\n", avg);

	quickSort(dataArr, 0, n - 1);
	printf("middleValue = %d\n", dataArr[n / 2]);

	//int mode = getMode(commonCnt) - 4000;
	//printf("modeValue = %d\n", mode);

	delete[] dataArr;
	return 0;
}