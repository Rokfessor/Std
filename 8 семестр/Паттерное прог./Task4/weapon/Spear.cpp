#include "Weapon.h"
#include "../hero/Hero.h"
#include <iostream>

#ifndef SPEAR_CPP
#define SPEAR_CPP

class Spear : public Weapon
{
public:
    Spear(int damage) : Weapon(damage) {}

    void takeDamage(Hero *hero, int additionalDamage)
    {
        int d = damage + additionalDamage + ((rand() * 10) % 3);
        std::cout << "Урон: " << d << std::endl;
        hero->getDamage(d);
    }
};

#endif