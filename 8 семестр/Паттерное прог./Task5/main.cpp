#include "Chat.cpp"
#include "User.cpp"

int main () {
    Chat *chat = new Chat();

    User* james = new User();
    User* mike = new User();
    User* fred = new User();

    chat->addObserver(james);
    chat->addObserver(mike);
    chat->addObserver(fred);

    chat->notifyObservers("Message");

    delete chat;
    delete james;
    delete mike;
    delete fred;

    return 0;
}