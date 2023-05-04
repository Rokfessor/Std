#include <string>
#include <iostream>

using namespace std;

enum class Size
{
    SMALL,
    MEDIUM,
    BIG
};

class Beverage
{
protected:
    string description;
    Size size;

public:
    virtual string getDescription() { return description; }
    virtual double cost() = 0;
    void setSize(Size s) { size = s; }
    Size getSize() { return size; }
};

class Espresso : public Beverage
{
public:
    Espresso() { description = "Espresso"; size = Size::MEDIUM; }
    virtual double cost()
    {
        switch (size)
        {
        case Size::SMALL:
            return 1.5;
        case Size::MEDIUM:
            return 2;
        case Size::BIG:
            return 2.5;
        }

        return 1.5;
    }
};

class HouseBlend : public Beverage
{
public:
    HouseBlend() { description = "House Blend coffee"; size = Size::MEDIUM; }
    virtual double cost()
    {
        switch (size)
        {
        case Size::SMALL:
            return 0.99;
        case Size::MEDIUM:
            return 1.25;
        case Size::BIG:
            return 1.49;
        }

        return 0.99;
    }
};

class DarkRoast : public Beverage
{
public:
    DarkRoast() { description = "Dark Roast coffee"; size = Size::MEDIUM; }
    virtual double cost()
    {
        switch (size)
        {
        case Size::SMALL:
            return 1.39;
        case Size::MEDIUM:
            return 1.99;
        case Size::BIG:
            return 2.59;
        }

        return 1.39;
    }
};

class CondimentDecorator : public Beverage
{
protected:
    Beverage *beverage;
};

class Soy : public CondimentDecorator
{
public:
    Soy(Beverage *b)
    {
        beverage = b;
        setSize(b->getSize());
    }
    virtual string getDescription() { return beverage->getDescription() + ", Soy"; }
    virtual double cost()
    {
        switch (size)
        {
        case Size::SMALL:
            return 0.2 + beverage->cost();
        case Size::MEDIUM:
            return 0.4 + beverage->cost();
        case Size::BIG:
            return 0.6 + beverage->cost();
        }

        return beverage->cost();
    }
    ~Soy() { delete beverage; }
};

class Whip : public CondimentDecorator
{
public:
    Whip(Beverage *b)
    {
        beverage = b;
        setSize(b->getSize());
    }
    virtual string getDescription() { return beverage->getDescription() + ", Whip"; }
    virtual double cost()
    {
        switch (size)
        {
        case Size::SMALL:
            return 0.15 + beverage->cost();
        case Size::MEDIUM:
            return 0.25 + beverage->cost();
        case Size::BIG:
            return 0.35 + beverage->cost();
        }

        return beverage->cost();
    }
    ~Whip() { delete beverage; }
};

int main()
{
    Beverage *b = new Espresso;
    b->setSize(Size::MEDIUM);
    cout << b->getDescription() << ", $" << b->cost() << endl;
    delete b;

    Beverage *b2 = new HouseBlend;
    b2->setSize(Size::BIG);
    b2 = new Soy(b2);
    b2 = new Whip(b2);
    b2 = new Whip(b2);
    cout << b2->getDescription() << ", $" << b2->cost() << endl;
    delete b2;

    return 0;
}
