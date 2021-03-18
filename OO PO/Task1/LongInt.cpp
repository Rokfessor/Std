#include "LongInt.h"

LongInt::LongInt(string num) {
    number = std::move(num);
}

LongInt::LongInt(int num) {
    number = to_string(num);
}

LongInt::LongInt() {
    number = "0";
}

LongInt LongInt::operator+(const LongInt &num) const {
    if (number.at(0) == '-' && num.number.at(0) != '-')
        return num.operator+(*this); //Проверка
    if (number.at(0) != '-' && num.number.at(0) == '-')
        return operator-(num * -1);

    stringstream ss;
    string max = number, min = num.number;
    bool flag = false;

    if (max.at(0) == '-' && min.at(0) == '-') {
        max.erase(0, 1); //erase(позиция, число символов)
        min.erase(0, 1);
        flag = true;
    }

    if (LongInt::abs(*this) < LongInt::abs(num)) {
        string tmp = max;
        max = min;
        min = tmp;
    }

    int ost = 0;
    int maxInd = max.length() - 1, minInd = min.length() - 1;

    for (int i = 0; i < max.length(); i++) {

        int a = static_cast<int>(max[maxInd]) - 48;

        if (minInd != -1) {
            int b = static_cast<int>(min[minInd]) - 48;
            int c = a + b + ost;
            ost = 0;
            if (c >= 10) {
                c -= 10;
                ost++;
            }
            ss << c;
            minInd--;
        } else {
            int c = a + ost;
            ost = 0;
            if (c >= 10) {
                c -= 10;
                ost++;
            }
            ss << c;
        }
        maxInd--;
    }

    if (ost == 1)
        ss << 1;

    string ans = ss.str();
    reverse(ans.begin(), ans.end());

    int k = 0;
    for (char an : ans) {
        if (an != '0')
            break;
        else
            k++;
    }
    ans.erase(0, k);

    if (ans.length() == 0) {
        ans.append(1, '0');
        flag = false;
    }

    if (flag)
        ans.insert(0, "-");

    return LongInt(ans);
}

LongInt LongInt::operator-(const LongInt &num) const {
    if (number.at(0) == '-' && num.number.at(0) == '-')
        return operator+(num * -1);
    if (number.at(0) == '-' && num.number.at(0) != '-')
        return operator+(num * -1);
    if (number.at(0) != '-' && num.number.at(0) == '-')
        return operator+(num * -1);

    stringstream ss;
    string max = number, min = num.number;
    bool flag = false;

    if (max.at(0) == '-')
        max.erase(0, 1);
    if (min.at(0) == '-')
        min.erase(0, 1);


    if (LongInt::abs(*this) < LongInt::abs(num)) {
        string tmp = max;
        max = num.number;
        min = tmp;
        flag = true;
    }

    int maxInd = max.length() - 1, minInd = min.length() - 1;

    for (int i = 0; i < max.length(); i++) {
        int a = static_cast<int>(max[maxInd]) - 48;

        if (minInd != -1) {
            int b = static_cast<int>(min[minInd]) - 48;
            int c;
            if (a - b < 0) {
                for (int j = maxInd - 1; j >= 0; j--) {
                    if (max[j] == '0')
                        max[j] = (static_cast<int>(9)) + '0';
                    else {
                        max[j] = (static_cast<int>(max[j]) - 48 - 1) + '0'; //Вот так
                        break;
                    }
                }
                c = (a + 10) - b;
            } else
                c = a - b;
            ss << c;
            minInd--;
        } else {
            ss << a;
        }
        maxInd--;
    }

    string ans = ss.str();
    reverse(ans.begin(), ans.end());

    int k = 0;
    for (char an : ans) {
        if (an != '0')
            break;
        else
            k++;
    }
    ans.erase(0, k);

    if (ans.length() == 0) {
        ans.append(1, '0');
        flag = false;
    }

    if (flag)
        ans.insert(0, "-");

    return LongInt(ans);
}

LongInt LongInt::operator*(const LongInt &num) const {
    if (num == LongInt(0) || *this == LongInt(0))
        return LongInt(0);

    string n1 = number, n2 = num.number;
    bool flag = false;

    if (n1[0] == '-') {
        flag = !flag;
        n1.erase(0, 1);
    }
    if (n2[0] == '-') {
        flag = !flag;
        n2.erase(0, 1);
    }

    if (n1.length() > n2.length()) // делаем n2 большим по длинне
        n1.swap(n2);

    string res = "0";
    for (int i = n1.length() - 1; i >= 0; --i) // обратный проход по меньшему
    {
        string temp = n2; // временное = большему
        int currentDigit = n1[i] - '0'; // текуший разряд меньшего
        int carry = 0;

        for (int j = temp.length() - 1; j >= 0; --j) // обратный проход по временному
        {
            temp[j] = ((temp[j] - '0') * currentDigit) + carry; // произведение текущих символов плюс остаток

            if (temp[j] > 9) // вычисление нового остатка
            {
                carry = (temp[j] / 10);
                temp[j] -= (carry * 10);
            } else
                carry = 0;

            temp[j] += '0'; // возвращение к цифрам
        }

        if (carry > 0) // выделение места под новый разряд
            temp.insert(0, 1, (carry + '0'));

        temp.append((n1.length() - i - 1), '0'); // смещение на разряд
        res = (LongInt(res) + LongInt(temp)).number; // суммирование всех поразрядных произведений
    }

    while (res[0] == '0' && res.length() != 1) // удаление незначащих нулей
        res.erase(0, 1);

    if (flag)
        return LongInt(string("-").append(res));

    return LongInt(res);
    /*if (num == LongInt(0) || *this == LongInt(0))
        return LongInt(0);

    string n1 = number, n2 = num.number;
    bool flag = false;

    if (n1[0] == '-') {
        flag = !flag;
        n1.erase(0, 1);
    }
    if (n2[0] == '-') {
        flag = !flag;
        n2.erase(0, 1);
    }

    LongInt numb(n1);
    for (LongInt i(1); i < LongInt(n2); ++i) {
        numb = LongInt(n1) + numb;
    }

    if (flag)
        return LongInt(string("-").append(numb.number));

    return numb;*/
}

LongInt LongInt::operator*(const int &num) const {
    return operator*(LongInt(num));
}

LongInt LongInt::operator/(const LongInt &num) const {
    if (*this == (LongInt(0)) && num != LongInt(0))
        return LongInt(0);
    if (num == LongInt(0))
        cerr << "Division by zero";

    int flag = 1;

    LongInt tmp1 = *this;
    if (*this < LongInt(0)) {
        tmp1 = LongInt::abs(tmp1);
        flag *= -1;
    }

    LongInt tmp2 = num;
    if (tmp2 < LongInt(0)) {
        tmp2 = LongInt::abs(tmp2);
        flag *= -1;
    }

    if (tmp1 < tmp2)
        return LongInt(0);

    LongInt res(1);
    while (tmp1 >= (res * tmp2)) {
        ++res;

    }

    return (res - LongInt(1)) * flag;
}

LongInt LongInt::operator%(const LongInt &num) const {
    if (*this == (LongInt(0)) && num != LongInt())
        return LongInt(0);
    if (num == LongInt(0))
        cerr << "Division by zero";

    if (LongInt::abs((const LongInt &) *this) < LongInt::abs(num))
        return *this;

    return (*this - (num * (*this / num)));
}

bool LongInt::operator>(const LongInt &num) const {
    if (number[0] == '-' && num.number[0] != '-')
        return false;
    if (number[0] != '-' && num.number[0] == '-')
        return true;

    string n1 = number, n2 = num.number;
    bool flag = true;

    if (number[0] == '-' && num.number[0] == '-') {
        flag = false;
        n1.erase(0, 1);
        n2.erase(0, 1);
    }

    if (n1.length() > n2.length())
        return flag;
    if (n1.length() < n2.length())
        return !flag;


    for (int i = 0; i < n1.length(); i++) {
        if (static_cast<int>(n1[i]) > static_cast<int>(n2[i]))
            return (flag);
        else if (static_cast<int>(n1[i]) < static_cast<int>(n2[i]))
            return (!flag);
    }

    return false;
}

bool LongInt::operator==(const LongInt &num) const {
    if (number.length() > num.number.length() || number.length() < num.number.length())
        return false;

    for (int i = 0; i < number.length(); i++) {
        if (static_cast<int>(number[i]) != static_cast<int>(num.number[i]))
            return false;
    }
    return true;
}

bool LongInt::operator>=(const LongInt &num) const {
    return operator>(num) || operator==(num);
}

bool LongInt::operator<(const LongInt &num) const {
    if (number[0] == '-' && num.number[0] != '-')
        return true;
    if (number[0] != '-' && num.number[0] == '-')
        return false;

    string n1 = number, n2 = num.number;
    bool flag = false;

    if (number[0] == '-' && num.number[0] == '-') {
        flag = true;
        n1.erase(0, 1);
        n2.erase(0, 1);
    }

    if (n1.length() > n2.length())
        return flag;
    if (n1.length() < n2.length())
        return !flag;


    for (int i = 0; i < n1.length(); i++) {
        if (static_cast<int>(n1[i]) > static_cast<int>(n2[i]))
            return (flag);
        else if (static_cast<int>(n1[i]) < static_cast<int>(n2[i]))
            return (!flag);
    }

    return false;
}

bool LongInt::operator<=(const LongInt &num) const {
    return operator<(num) || operator==(num);
}

bool LongInt::operator!=(const LongInt &num) const {
    return !operator==(num);
}

ostream &operator<<(ostream &out, const LongInt &number) {
    out << number.number;
    return out;
}

const LongInt &operator++(LongInt &i) {
    i = i + (LongInt(1));
    return i;
}

LongInt &LongInt::operator=(const LongInt &i) {
    if (this == &i) {
        return *this;
    }
    number.assign(i.number);
    return *this;
}

LongInt LongInt::abs(LongInt num) {
    if (num.number[0] == '-')
        return LongInt(num.number.erase(0, 1));
    return num;
}

