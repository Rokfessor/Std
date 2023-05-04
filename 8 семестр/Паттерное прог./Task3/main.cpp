#include <stdio.h>
#include <iostream>
#include <stdexcept>
#include <cstring>

using namespace std;

class Vector
{
protected:
    int *elements;
    int size;

public:
    //Vector() = default;
    Vector(int s)
    {
        elements = new int[s];
        size = s;
    }

    ~Vector() {
        delete[] elements;
    }

    int getSize()
    {
        return size;
    }

    virtual void push(int element) {
        int *newElements = new int[size + 1];
        memcpy(newElements, elements, size * sizeof(int));
        ++size;
        delete[] elements;
        elements = newElements;
        elements[size - 1] = element;
    };

    virtual int & operator[](int index) {
        return elements[index];
    }
}; 

class IndexedVector : public virtual Vector
{
public:
    IndexedVector(int s) : Vector(s) {}

    int & operator[](int index)
    {
        if (index >= size || index < 0)
            throw "Index out of bounds exception";

        return elements[index];
    }
};

class SortedVector : public virtual  Vector
{
public:
    SortedVector(int s) : Vector(s) {}

    void push(int element)
    {
        int *newElements = new int[size + 1];
        bool elementAdded = false;
        ++size;

        for (int i = 0, j = 0; i < size; ++i, ++j)
        {
            if (!elementAdded)
            {
                if (i == size - 1)
                {
                    newElements[i] = element;
                    continue;
                }

                if (elements[j] > element)
                {
                    newElements[i] = element;
                    elementAdded = true;
                    --j;
                    continue;
                }
            }

            newElements[i] = elements[j];
        }

        delete[] elements;
        elements = newElements;
    }
};

class SortedIndexedVector : public SortedVector, IndexedVector
{
public:
    SortedIndexedVector(int s): Vector(s), SortedVector(s), IndexedVector(s) {}

    void push(int index)
    {
        SortedVector::push(index);
    }

    int & operator[](int index)
    {
        return IndexedVector::operator[](index);
    }
};

void printVector(Vector *v)
{
    for (int i = 0; i < v->getSize(); ++i)
    {
        cout << "v[" << i << "] = " << (*v)[i] << endl;
    }

    try
    {
        cout << "v[" << v->getSize() << "] = ";
        cout << (*v)[v->getSize()] << endl;
    }
    catch (const char *e)
    {
        cout << e << endl;
    }
}

int main()
{
    cout << "Indexed vector" << endl;
    IndexedVector *indexedVector = new IndexedVector(3);
    indexedVector->push(2);
    indexedVector->push(3);
    indexedVector->push(1);

    printVector(indexedVector);

    cout << endl
         << "Sorted vector" << endl;
    SortedVector *sortedVector = new SortedVector(3);
    sortedVector->push(2);
    sortedVector->push(3);
    sortedVector->push(1);

    printVector(sortedVector);

    cout << endl
         << "Sorted and indexed vector" << endl;
    SortedIndexedVector *sortedIndexedVector = new SortedIndexedVector(3);

    sortedIndexedVector->push(2);
    sortedIndexedVector->push(3);
    sortedIndexedVector->push(1);

    printVector(sortedIndexedVector);

    delete indexedVector;
    delete sortedVector;
    delete sortedIndexedVector;

    cout << endl;
    return 0;
}