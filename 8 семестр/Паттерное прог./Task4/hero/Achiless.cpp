#include "Hero.h"
#include "../weapon/Spear.cpp"
#include <iostream>

class Achiless : public Hero
{
public:
    Achiless(const char *name, int health) : Hero(name, health) {}

    void attack(Hero *enemy)
    {
        std::cout << name << " наносит удар" << std::endl;
        int additionalDamage = 0;
        if (dynamic_cast<const Spear *>(weapon) != nullptr)
            additionalDamage = 10;
        weapon->takeDamage(enemy, additionalDamage);
    };

    void getDamage(int damage)
    {
        current_health -= (damage - 5);
        std::cout << name << " текущее здоровье " << getHealth() << std::endl;
    };
};