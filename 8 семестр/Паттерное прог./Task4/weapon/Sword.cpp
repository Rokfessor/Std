#include "Weapon.h"
#include <iostream>

#ifndef SWORD_CPP
#define SWORD_CPP

class Sword : public Weapon
{
public:
    Sword(int damage) : Weapon(damage) {}

    void takeDamage(Hero *hero, int additionalDamage)
    {
        int d = damage + additionalDamage + ((rand() * 10) % 7);
        std::cout << "Урон: " << d << std::endl;
        hero->getDamage(d);
    }
};

#endif