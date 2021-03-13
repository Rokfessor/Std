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
    //cout << number << " + " << num.number << " = ";
    //Вычисялем как в столбик
    if (number.at(0) == '-' && num.number.at(0) != '-')
        return num.operator+(*this); //Проверка на вшивость
    if (number.at(0) != '-' && num.number.at(0) == '-')
        return operator-(num * -1);

    stringstream ss; //Поток записи результата
    string max = number, min = num.number; //Потом пригодится
    bool flag = false; //Для суммы отрицательных чисел

    //Убираем минусики, если числа отрицательные
    if (max.at(0) == '-' && min.at(0) == '-') {
        max.erase(0, 1); //erase(позиция, число символов)
        min.erase(0, 1);
        flag = true;
    }

    //Ищем наименьшее число (по числу цифр, а не значению!)
    if (LongInt::abs(*this) < LongInt::abs(num)) {
        string tmp = max;
        max = min;
        min = tmp;
    }

    int ost = 0; // остаток
    //Типа числа записываются слева направо и если мы возьмем 0 индекс, то получим 1 цифу числа, а нужна последняя
    int maxInd = max.length() - 1, minInd = min.length() - 1; //Поэтому высчитываем эту херь

    for (int i = 0; i < max.length(); i++) { //Херачим, пока большее число не кончится

        int a = static_cast<int>(max[maxInd]) - 48; //Получаем цифру большего числа

        if (minInd != -1) { //Херачим, пока меньшее число не кончится
            //Получаем цифру меньшего числа
            int b = static_cast<int>(min[minInd]) - 48;
            //Суммируем числа с учетом остатка от прошлой суммы
            int c = a + b + ost;
            ost = 0; //Убираем остаток
            if (c >= 10) { //И считаем его снова
                c -= 10;
                ost++;
            }
            ss << c; //Пишем в поток результат
            minInd--; //Идем к следующей цифре меньшего числа (справа налево)
        } else { //Тоже самое, только с оставшимися разрядами большего числа
            int c = a + ost; //Никто не отменял 9999999999+1, поэтому тут тоже нужно считать остаток
            ost = 0;
            if (c >= 10) {
                c -= 10;
                ost++;
            }
            ss << c;
        }
        maxInd--; //Идем к следующей цифре большего числа (справа налево)
    }

    if (ost == 1) //Если есть еще остаточек - добавляем
        ss << 1;

    string ans = ss.str(); //stringstream пишет в формате стека, поэтому строку нужно
    reverse(ans.begin(), ans.end()); //реверснуть

    int k = 0;
    for (char an : ans) { //Если лишние нолики впереди остались
        if (an != '0')
            break;
        else
            k++;
    }
    ans.erase(0, k);

    if (ans.length() == 0){
        ans.append(1, '0');
        flag = false;
    }

    if (flag) //Если была сумма 2 отрицательных чисел
        ans.insert(0, "-");

    return LongInt(ans);
}

LongInt LongInt::operator-(const LongInt &num) const {
    //cout << number << " - " << num.number << " = ";
    //Вычисялем как в столбик
    //По сути тоже самое, только в цикле нужно следующий разряд не увеличить на 1, за уменьшить(занять)
    if (number.at(0) == '-' && num.number.at(0) == '-')
        return operator+(num * -1);
    if (number.at(0) == '-' && num.number.at(0) != '-')
        return operator+(num * -1);
    if (number.at(0) != '-' && num.number.at(0) == '-')
        return operator+(num * -1);

    stringstream ss; //Поток записи результата
    string max = number, min = num.number; //Потом пригодится
    bool flag = false; //Для того, чтобы грамотно - поставить

    //Убираем минусики, если числа отрицательные
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
            if (a - b < 0) { //Если число меньше, то занимаем у следующего ненулевого! разряда (100000000000 - 1 никто не отменял)
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

    string ans = ss.str(); //stringstream пишет в формате стека, поэтому строку нужно
    reverse(ans.begin(), ans.end()); //реверснуть

    int k = 0;
    for (char an : ans) { //Если лишние нолики впереди остались
        if (an != '0')
            break;
        else
            k++;
    }
    ans.erase(0, k);

    if (ans.length() == 0){
        ans.append(1, '0');
        flag = false;
    }

    if (flag) //Если была сумма 2 отрицательных чисел
        ans.insert(0, "-");

    return LongInt(ans);
}

LongInt LongInt::operator*(const LongInt &num) const {
    if (num.number == "0")
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
    for (LongInt i(1); i < LongInt(n2); ++i) { //Суммируем n раз ¯\_(ツ)_/¯
        numb = LongInt(n1) + LongInt(numb);
    }

    if (flag)
        return LongInt(string("-").append(numb.number));

    return numb;
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
        ++res; //Суммируем, пока не упремся в число, либо перепрыгнем его
        //Можно было и вычитать
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

    return ( *this - (num * (*this/num)));
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


    for (int i = 0; i < n1.length(); i++) { // Сравниваем по разрядам (от большего к меньшему)
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


    for (int i = 0; i < n1.length(); i++) { // Сравниваем по разрядам (от большего к меньшему)
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
        return LongInt(num.number.erase(0,1));
    return num;
}

bool LongInt::isPositive(LongInt num) {
    return num.number.at(0) == '-';
}
