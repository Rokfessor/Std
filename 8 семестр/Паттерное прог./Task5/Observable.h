#ifndef OBSERVERABLE_H
#define OBSERVERABLE_H

#include "Observer.h"

class Observable {
    public:
        virtual void addObserver(Observer* observer) = 0;
        virtual void removeObserver(Observer* observer) = 0;
        virtual void notifyObservers(const char* message) = 0;
};

#endif