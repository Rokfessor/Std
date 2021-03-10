#ifndef TASK1_LONGINT_H
#define TASK1_LONGINT_H

#include <string>
#include <malloc.h>
#include <iostream>
#include <sstream>
#include <cstdlib>
#include<bits/stdc++.h>

using namespace std;

class LongInt {
public:
    string number;

public :
    explicit LongInt(string num);
    explicit LongInt(int num);
    explicit LongInt();
    LongInt operator+(const LongInt& num) const;
    LongInt operator-(const LongInt& num) const;
    LongInt operator*(const LongInt& num) const;
    LongInt operator*(const int& num) const;
    LongInt operator/(const LongInt& num) const;
    LongInt operator%(const LongInt& num) const;

    bool operator>(const LongInt& num) const;
    bool operator>=(const LongInt& num) const;
    bool operator<(const LongInt& num) const;
    bool operator<=(const LongInt& num) const;
    bool operator==(const LongInt& num) const;
    bool operator!=(const LongInt& num) const;

};

#endif //TASK1_LONGINT_H
