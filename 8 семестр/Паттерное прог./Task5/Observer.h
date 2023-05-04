#ifndef OBSERVER_H
#define OBSERVER_H

class Observer {
    public:
        virtual void update(const char* message) = 0;
};

#endif