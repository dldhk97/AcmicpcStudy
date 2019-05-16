//#10866
//
//제목
//덱
//
//문제
//정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
//
//명령은 총 여덟 가지이다.
//
//push_front X : 정수 X를 덱의 앞에 넣는다.
//push_back X : 정수 X를 덱의 뒤에 넣는다.
//pop_front : 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다.만약, 덱에 들어있는 정수가 없는 경우에는 - 1을 출력한다.
//	pop_back : 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다.만약, 덱에 들어있는 정수가 없는 경우에는 - 1을 출력한다.
//	size : 덱에 들어있는 정수의 개수를 출력한다.
//	empty : 덱이 비어있으면 1을, 아니면 0을 출력한다.
//	front : 덱의 가장 앞에 있는 정수를 출력한다.만약 덱에 들어있는 정수가 없는 경우에는 - 1을 출력한다.
//	back : 덱의 가장 뒤에 있는 정수를 출력한다.만약 덱에 들어있는 정수가 없는 경우에는 - 1을 출력한다.
//	입력
//	첫째 줄에 주어지는 명령의 수 N(1 ≤ N ≤ 10, 000)이 주어진다.둘쨰 줄부터 N개의 줄에는 명령이 하나씩 주어진다.주어지는 정수는 1보다 크거나 같고, 100, 000보다 작거나 같다.문제에 나와있지 않은 명령이 주어지는 경우는 없다.
//
//	출력
//	출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
#include <iostream>

class Node
{
private:
	int data;
	Node* previousNode;
	Node* nextNode;
public:
	Node(int data)
	{
		this->data = data;
	}
	Node* getPreviousNode()
	{
		return previousNode;
	}
	Node* getNextNode()
	{
		return nextNode;
	}
	void setPreviousNode(Node* previousNode)
	{
		this->previousNode = previousNode;
	}
	void setNextNode(Node* nextNode)
	{
		this->nextNode = nextNode;
	}
	int getData()
	{
		return this->data;
	}
};

class Dequeue
{
private:
	Node* dummyHead = new Node(0);
	int size = 0;

public:
	void push_front(int data)
	{
		Node* newNode = new Node(data);
		if (isEmpty())
		{
			dummyHead->setNextNode(newNode);
			dummyHead->setPreviousNode(newNode);
			newNode->setNextNode(dummyHead);
			newNode->setPreviousNode(dummyHead);
		}
		else
		{
			dummyHead->getNextNode()->setPreviousNode(newNode);
			newNode->setNextNode(dummyHead->getNextNode());
			newNode->setPreviousNode(dummyHead);
			dummyHead->setNextNode(newNode);
		}
		size++;
	}
	void push_back(int data)
	{
		Node* newNode = new Node(data);
		if (isEmpty())
		{
			dummyHead->setNextNode(newNode);
			dummyHead->setPreviousNode(newNode);
			newNode->setNextNode(dummyHead);
			newNode->setPreviousNode(dummyHead);
		}
		else
		{
			dummyHead->getPreviousNode()->setNextNode(newNode);
			newNode->setPreviousNode(dummyHead->getPreviousNode());
			newNode->setNextNode(dummyHead);
			dummyHead->setPreviousNode(newNode);
		}
		size++;
	}
	int pop_front()
	{
		if (size > 1)
		{
			Node* resultNode = dummyHead->getNextNode();
			resultNode->getNextNode()->setPreviousNode(dummyHead);
			dummyHead->setNextNode(resultNode->getNextNode());
			int result = resultNode->getData();
			delete resultNode;
			size--;
			return result;
		}
		else if (size == 1)
		{
			Node* resultNode = dummyHead->getNextNode();
			dummyHead->setNextNode(nullptr);
			dummyHead->setPreviousNode(nullptr);
			int result = resultNode->getData();
			delete resultNode;
			size--;
			return result;
		}
		return -1;
	}
	int pop_back()
	{
		if (size > 1)
		{
			Node* resultNode = dummyHead->getPreviousNode();
			resultNode->getPreviousNode()->setNextNode(dummyHead);
			dummyHead->setPreviousNode(resultNode->getPreviousNode());
			int result = resultNode->getData();
			delete resultNode;
			size--;
			return result;
		}
		else if (size == 1)
		{
			Node* resultNode = dummyHead->getPreviousNode();
			dummyHead->setPreviousNode(nullptr);
			dummyHead->setNextNode(nullptr);
			int result = resultNode->getData();
			delete resultNode;
			size--;
			return result;
		}
		return -1;
	}
	int getSize()
	{
		return size;
	}
	bool isEmpty()
	{
		if (size == 0)
			return true;
		return false;
	}
	int getFront()
	{
		return dummyHead->getNextNode() ? dummyHead->getNextNode()->getData() : -1;
	}
	int getBack()
	{
		return dummyHead->getPreviousNode() ? dummyHead->getPreviousNode()->getData() : -1;
	}
};

int main()
{
	int n, userInput2 = 0;
	std::string userInput;
	Dequeue newDequeue;

	std::cin >> n;
	for (int i = 0; i < n; i++)
	{
		std::cin >> userInput;
		if (userInput == "push_front")
		{
			std::cin >> userInput2;
			newDequeue.push_front(userInput2);
		}
		else if (userInput == "push_back")
		{
			std::cin >> userInput2;
			newDequeue.push_back(userInput2);
		}
		else if (userInput == "pop_front")
		{
			std::cout << newDequeue.pop_front() << "\n";
		}
		else if (userInput == "pop_back")
		{
			std::cout << newDequeue.pop_back() << "\n";
		}
		else if (userInput == "size")
		{
			std::cout << newDequeue.getSize() << "\n";
		}
		else if (userInput == "empty")
		{
			std::cout << newDequeue.isEmpty() << "\n";
		}
		else if (userInput == "front")
		{
			std::cout << newDequeue.getFront() << "\n";
		}
		else if (userInput == "back")
		{
			std::cout << newDequeue.getBack() << "\n";
		}
	}

	return 0;
}