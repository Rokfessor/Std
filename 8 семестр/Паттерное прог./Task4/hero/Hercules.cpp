#include "Hero.h"
#include <iostream>
#include "../weapon/Axe.cpp"

class Hercules : public Hero
{
public:
    Hercules(const char *name, int health) : Hero(name, health) {}

    void attack(Hero *enemy)
    {
        std::cout << name << " наносит удар" << std::endl;
        int additionalDamage = 0;
        if (dynamic_cast<const Axe *>(weapon) != nullptr)
            additionalDamage = 5;
        weapon->takeDamage(enemy, additionalDamage);
    };

    void getDamage(int damage){
        current_health -= (damage - 2);
        std::cout << name << " текущее здоровье " << getHealth() << std::endl;
    };
};