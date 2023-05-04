#ifndef WEAPON_H
#define WEAPON_H

class Hero;

class Weapon
{
protected:
    int damage;

public:
    Weapon(int damage)
    {
        this->damage = damage;
    }

    virtual void takeDamage(Hero *hero, int additionalDamage) = 0;
};

#endif