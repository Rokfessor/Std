#ifndef CHAT_H
#define CHAT_H

#include <vector>
#include "Observable.h"
#include "Observer.h"
#include "User.h"

class Chat : public Observable
{
private:
    std::vector<Observer *> users;

public:
    void handleMessage(const char *message, User *user);
    void addObserver(Observer *observer);
    void removeObserver(Observer *observer);
    void notifyObservers(const char *message);
};

#endif