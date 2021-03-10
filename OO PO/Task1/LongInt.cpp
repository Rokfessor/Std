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
    //Вычисялем как в столбик
    if (!(number.at(0) == '-' && num.number.at(0) == '-') && !(number.at(0) != '-' && num.number.at(0) != '-'))
        return operator-(num * -1); //Проверка на вшивость

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
    if (number.length() < num.number.length()) {
        string tmp = max;
        max = num.number;
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

    if (flag) //Если была сумма 2 отрицательных чисел
        ss << '-';

    string ans = ss.str(); //stringstream пишет в формате стека, поэтому строку нужно
    reverse(ans.begin(), ans.end()); //реверснуть

    return LongInt(ans);
}

LongInt LongInt::operator-(const LongInt &num) const {
    //Вычисялем как в столбик
    //По сути тоже самое, только в цикле нужно следующий разряд не увеличить на 1, за уменьшить(занять)
    if (number.at(0) == '-' && num.number.at(0) != '-')
        return operator+(num);

    stringstream ss; //Поток записи результата
    string max = number, min = num.number; //Потом пригодится
    bool flag = false; //Для того, чтобы грамотно - поставить

    //Убираем минусики, если числа отрицательные
    if (max.at(0) == '-')
        max.erase(0, 1);
    if (min.at(0) == '-')
        min.erase(0, 1);


    if (number.length() < num.number.length()) {
        string tmp = max;
        max = num.number;
        min = tmp;
        flag = true;
    }

    int maxInd = max.length() - 1, minInd = min.length() - 1;

    for (int i = 0; i < max.length(); i++) {
        int a = static_cast<int>(max[maxInd]) - 48;
s
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

    if (flag)
        ss << '-';

    string ans = ss.str(); //stringstream пишет в формате стека, поэтому строку нужно
    reverse(ans.begin(), ans.end()); //реверснуть

    for (int i = 0; i < ans.length(); i++) { //Если лишние нолики впереди остались
        if (ans[i] != '0')
            break;
        else
            ans.erase(i, 1);
    }

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

    LongInt numb(0);
    for (LongInt i(1); i < LongInt(n2); i = i.operator+(LongInt(1))) { //Суммируем n раз ¯\_(ツ)_/¯
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
    if (this->operator==(LongInt(0)) && num != LongInt())
        return LongInt();
    if (num == LongInt())
        cerr << "Division by zero";

    if (this->operator<(num))
        return LongInt();

    LongInt res(1);
    while (this->operator>=(res * num)) {
        res = res + LongInt(1); //Суммируем, пока не упремся в число, либо перепрыгнем его
        //Можно было и вычитать
    }

    return res - LongInt(1);
}

LongInt LongInt::operator%(const LongInt &num) const {
    if (this->operator==(LongInt(0)) && num != LongInt())
        return LongInt();
    if (num == LongInt())
        cerr << "Division by zero";

    return this->operator-(this->operator/(num) * num);
}

bool LongInt::operator>(const LongInt &num) const {
    if (number.length() > num.number.length())
        return true;
    if (number.length() < num.number.length())
        return false;

    for (int i = 0; i < number.length(); i++) { // Сравниваем по разрядам (от большего к меньшему)
        if (static_cast<int>(number[i]) > static_cast<int>(num.number[i]))
            return true;
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
    return !operator>(num);
}

bool LongInt::operator<=(const LongInt &num) const {
    return operator<(num) || operator==(num);
}

bool LongInt::operator!=(const LongInt &num) const {
    return !operator==(num);
}
