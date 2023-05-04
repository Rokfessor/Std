#ifndef USER_CPP
#define USER_CPP

#include "User.h"

User::User(const char *name) : Observer()
{
    this->name = name;
    this->chat = chat;
}

void User::update(const char *message)
{
    std::cout << "Пользователь " << name << " получил сообщение" << message << std::endl;
}

void User::sendMessage(const char *message)
{

}

#endif