#include "Weapon.h"
#include <iostream>

#ifndef AXE_CPP
#define AXE_CPP

class Axe : public Weapon
{
public:
    Axe(int damage) : Weapon(damage) {}

    void takeDamage(Hero *hero, int additionalDamage)
    {
        int d = damage + additionalDamage + ((rand() * 10) % 5);
        std::cout << "Урон: " << d << std::endl;
        hero->getDamage(d);
    }
};

#endif