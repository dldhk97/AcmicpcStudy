//#10866
//
//����
//��
//
//����
//������ �����ϴ� ��(Deque)�� ������ ����, �Է����� �־����� ����� ó���ϴ� ���α׷��� �ۼ��Ͻÿ�.
//
//����� �� ���� �����̴�.
//
//push_front X : ���� X�� ���� �տ� �ִ´�.
//push_back X : ���� X�� ���� �ڿ� �ִ´�.
//pop_front : ���� ���� �տ� �ִ� ���� ����, �� ���� ����Ѵ�.����, ���� ����ִ� ������ ���� ��쿡�� - 1�� ����Ѵ�.
//	pop_back : ���� ���� �ڿ� �ִ� ���� ����, �� ���� ����Ѵ�.����, ���� ����ִ� ������ ���� ��쿡�� - 1�� ����Ѵ�.
//	size : ���� ����ִ� ������ ������ ����Ѵ�.
//	empty : ���� ��������� 1��, �ƴϸ� 0�� ����Ѵ�.
//	front : ���� ���� �տ� �ִ� ������ ����Ѵ�.���� ���� ����ִ� ������ ���� ��쿡�� - 1�� ����Ѵ�.
//	back : ���� ���� �ڿ� �ִ� ������ ����Ѵ�.���� ���� ����ִ� ������ ���� ��쿡�� - 1�� ����Ѵ�.
//	�Է�
//	ù° �ٿ� �־����� ����� �� N(1 �� N �� 10, 000)�� �־�����.�Ѥ� �ٺ��� N���� �ٿ��� ����� �ϳ��� �־�����.�־����� ������ 1���� ũ�ų� ����, 100, 000���� �۰ų� ����.������ �������� ���� ����� �־����� ���� ����.
//
//	���
//	����ؾ��ϴ� ����� �־��� ������, �� �ٿ� �ϳ��� ����Ѵ�.
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