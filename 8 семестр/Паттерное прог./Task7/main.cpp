#include <iostream>
#include <string>
using namespace std;

class Light
{
    string descr;

public:
    Light(string d) { descr = d; }
    void on() { cout << descr << ": Light is on" << endl; }
    void off() { cout << descr << ": Light is off" << endl; }
};

class GarageDoor
{
public:
    void up() { cout << "Garage door is open" << endl; }
    void down() { cout << "Garage door is close" << endl; }
};

class Stereo
{
public:
    void on() { cout << "Stereo is on" << endl; }
    void off() { cout << "Stereo is off" << endl; }
    void setCd() { cout << "Stereo is set for CD input" << endl; }
    void setDvd() { cout << "Stereo is set for DVD input" << endl; }
    void setRadio() { cout << "Stereo is set for radio" << endl; }
    void setVolume(int v) { cout << "Stereo volume set to " << v << endl; }
};

enum VentilatorState //!!
{
    LOW,
    MEDIUM,
    HARD,
    OFF
};

class Ventilator //!!
{
private:
    VentilatorState lastState = VentilatorState::OFF;
    VentilatorState currentState = VentilatorState::OFF;

    void changeState(VentilatorState s)
    {
        lastState = currentState;
        currentState = s;
    }
public:

    void setLastState() {
        switch (lastState)
        {
        case VentilatorState::LOW :
            setLowMode();
            break;
        case VentilatorState::MEDIUM :
            setMediumMode();
            break;
        case VentilatorState::HARD :
            setHardMode();
            break;
        case VentilatorState::OFF :
            off();
            break;
        default:
            break;
        }
    }
    void setLowMode()
    {
        changeState(VentilatorState::LOW);
        cout << "Ventilator is set on low mode" << endl;
    }
    void setMediumMode()
    {
        changeState(VentilatorState::MEDIUM);
        cout << "Ventilator is set on medium mode" << endl;
    }
    void setHardMode()
    {
        changeState(VentilatorState::HARD);
        cout << "Ventilator is set on hard mode" << endl;
    }
    void off()
    {
        changeState(VentilatorState::OFF);
        cout << "Ventilator is off" << endl;
    }
};

class Command
{
public:
    virtual void execute() = 0;
    virtual void undo() = 0;
};

class NoCommand : public Command
{
public:
    virtual void execute() {}
    virtual void undo() {}
};

class VentilatorLowModeCommand : public Command
{
    Ventilator *ventilator;

public:
    VentilatorLowModeCommand(Ventilator *v) { ventilator = v; }
    virtual void execute()
    {

        ventilator->setLowMode();
    }
    virtual void undo() { ventilator->setLastState(); }
};

class VentilatorMediumModeCommand : public Command
{
    Ventilator *ventilator;

public:
    VentilatorMediumModeCommand(Ventilator *v) { ventilator = v; }
    virtual void execute() { ventilator->setMediumMode(); }
    virtual void undo() { ventilator->setLastState(); }
};

class VentilatorHardModeCommand : public Command
{
    Ventilator *ventilator;

public:
    VentilatorHardModeCommand(Ventilator *v) { ventilator = v; }
    virtual void execute() { ventilator->setHardMode(); }
    virtual void undo() { ventilator->setLastState(); }
};

class VentilatorOffCommand : public Command
{
    Ventilator *ventilator;

public:
    VentilatorOffCommand(Ventilator *v) { ventilator = v; }
    virtual void execute() { ventilator->off(); }
    virtual void undo() { ventilator->setLastState(); }
};

class LightOnCommand : public Command
{
    Light *light;

public:
    LightOnCommand(Light *l) { light = l; }
    virtual void execute() { light->on(); }
    virtual void undo() { light->off(); }
};

class LightOffCommand : public Command
{
    Light *light;

public:
    LightOffCommand(Light *l) { light = l; }
    virtual void execute() { light->off(); }
    virtual void undo() { light->on(); }
};

class GarageDoorUpCommand : public Command
{
    GarageDoor *garageDoor;

public:
    GarageDoorUpCommand(GarageDoor *gd) { garageDoor = gd; }
    virtual void execute() { garageDoor->up(); }
    virtual void undo() { garageDoor->down(); }
};

class GarageDoorDownCommand : public Command
{
    GarageDoor *garageDoor;

public:
    GarageDoorDownCommand(GarageDoor *gd) { garageDoor = gd; }
    virtual void execute() { garageDoor->down(); }
    virtual void undo() { garageDoor->up(); }
};

class StereoOnWithCDCommand : public Command
{
    Stereo *stereo;

public:
    StereoOnWithCDCommand(Stereo *s) { stereo = s; }
    virtual void execute()
    {
        stereo->on();
        stereo->setCd();
        stereo->setVolume(11);
    }

    virtual void undo() { stereo->off(); }
};

class StereoOffCommand : public Command
{
    Stereo *stereo;

public:
    StereoOffCommand(Stereo *s) { stereo = s; }
    virtual void execute() { stereo->off(); }
    virtual void undo() { stereo->on(); }
};

// Пульт
class RemoteControl
{
    int countButtons;
    Command **onCommands;
    Command **offCommands;
    Command *lastCommand; // !!
    NoCommand *noCommand;

public:
    RemoteControl(int c)
    {
        countButtons = c;
        onCommands = new Command *[c];
        offCommands = new Command *[c];
        noCommand = new NoCommand;
        for (int i = 0; i < c; i++)
        {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    ~RemoteControl()
    {
        delete[] onCommands;
        delete[] offCommands;
        delete noCommand;
    }

    void setCommand(int slot, Command *onCommand, Command *offCommand)
    {
        if (onCommands[slot] != noCommand)
            delete onCommands[slot];
        onCommands[slot] = onCommand;
        if (offCommands[slot] != noCommand)
            delete offCommands[slot];
        offCommands[slot] = offCommand;
    }

    void undoLastCommand() // !!
    {
        cout << "Cancelling last operation" << endl;
        lastCommand->undo();
    }

    void onButtonWasPushed(int slot)
    {
        lastCommand = onCommands[slot]; // !!
        onCommands[slot]->execute();
    }

    void offButtonWasPushed(int slot)
    {
        lastCommand = offCommands[slot]; // !!
        offCommands[slot]->execute();
    }
};

int main()
{
    RemoteControl rc(7);
    Light *livingRoomLight = new Light("Living Room");
    Light *kitchenLight = new Light("Kitchen");
    GarageDoor *garageDoor = new GarageDoor;
    Stereo *stereo = new Stereo;
    Ventilator *ventilator = new Ventilator;

    LightOnCommand *livingRoomLightOn = new LightOnCommand(livingRoomLight);
    LightOffCommand *livingRoomLightOff = new LightOffCommand(livingRoomLight);
    LightOnCommand *kitchenLightOn = new LightOnCommand(kitchenLight);
    LightOffCommand *kitchenLightOff = new LightOffCommand(kitchenLight);
    GarageDoorUpCommand *garageDoorUp = new GarageDoorUpCommand(garageDoor);
    GarageDoorDownCommand *garageDoorDown = new GarageDoorDownCommand(garageDoor);
    StereoOnWithCDCommand *stereoOnWithCD = new StereoOnWithCDCommand(stereo);
    StereoOffCommand *stereoOff = new StereoOffCommand(stereo);
    VentilatorMediumModeCommand *ventilatorMedium = new VentilatorMediumModeCommand(ventilator);
    VentilatorOffCommand *ventilatorOff = new VentilatorOffCommand(ventilator);

    rc.setCommand(0, livingRoomLightOn, livingRoomLightOff);
    rc.setCommand(1, kitchenLightOn, kitchenLightOff);
    rc.setCommand(2, garageDoorUp, garageDoorDown);
    rc.setCommand(3, stereoOnWithCD, stereoOff);
    rc.setCommand(4, ventilatorMedium, ventilatorOff);

    for (int i = 0; i < 5; i++)
    {
        rc.onButtonWasPushed(i);
        rc.offButtonWasPushed(i);
    }

    rc.undoLastCommand(); // !!

    return 0;
}
