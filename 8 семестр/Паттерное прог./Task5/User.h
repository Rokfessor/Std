#ifndef USER_H
#define USER_H

#include <iostream>
#include "Observer.h"

class User : public Observer
{
private:
    const char *name;

public:
    User(const char *name);
    User();
    void update(const char *message);
    void sendMessage(const char *message);
};

#endif