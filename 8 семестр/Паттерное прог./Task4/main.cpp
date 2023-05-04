#include "hero/Achiless.cpp"
#include "hero/Hercules.cpp"
#include "weapon/Axe.cpp"
#include "weapon/Sword.cpp"
#include "weapon/Spear.cpp"
#include <stdlib.h>
#include <time.h>

int main()
{
    srand(time(NULL));

    Hero *achiless = new Achiless("Ахилес", 100);
    Hero *hercules = new Hercules("Геркулес", 120);

    Weapon *weapons[] = {
        new Spear(10),
        new Axe(15),
        new Sword(20)};

    bool turn = rand() > 0.5;
    int achilessWin = 0;
    for (int i = 1; i <= 3; ++i)
    {
        std::cout << "===Раунд " << i << "===" << std::endl;
        achiless->setWeapon(weapons[rand() % 3]);
        hercules->setWeapon(weapons[rand() % 3]);

        while (achiless->getHealth() > 0 && hercules->getHealth() > 0) {
            if (turn)
                achiless->attack(hercules);
            else 
                hercules->attack(achiless);

            turn = !turn;
        }
        
        const char* winnerName;
        if (achiless->getHealth() <= 0)
            winnerName = hercules->getName();
        else {
            winnerName = achiless->getName();
            ++achilessWin;
        }

        hercules->restoreHealth();
        achiless->restoreHealth();

        std::cout << "===" << winnerName << " победил в " << i << " раунде===" << std::endl << std::endl;
    }

    const char* winnerName;
    if (achilessWin > 1)
        winnerName = achiless->getName();
    else    
        winnerName = hercules->getName();


    std::cout << winnerName << " победил в схватке" << std::endl;

    return 0;
};