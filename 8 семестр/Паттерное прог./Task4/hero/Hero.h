#ifndef HERO_H
#define HERO_H

#include "../weapon/Weapon.h"

class Hero
{
    protected:
        const char* name;
        int max_health;
        int current_health;
        Weapon* weapon;
        
    public:
        Hero(const char* name, int health) {
            this->name = name;
            this->max_health = health;
            this->current_health = health;
        }

        void setWeapon(Weapon* weapon) {
            this->weapon = weapon;
        }

        void restoreHealth() {
            current_health = max_health;
        }

        int getHealth() {
            return current_health < 0 ? 0 : current_health;
        }

        const char* getName() {
            return name;
        }

        virtual void attack(Hero* enemy) = 0;
        virtual void getDamage(int damage) = 0;
};

#endif