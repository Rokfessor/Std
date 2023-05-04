#ifndef USER_CPP
#define USER_CPP

#include "Chat.h"

void Chat::addObserver(Observer *observer)
{
    users.push_back(observer);
};

void Chat::removeObserver(Observer *observer){

};

void Chat::notifyObservers(const char *message){
    for (const auto& user : users) {
        user->update(message);
    }
};

void handleMessage(const char *message, User *user) {
    
}

#endif