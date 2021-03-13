#include "LongInt.h"

using namespace std;

int main() {
    LongInt n1(-25);
    LongInt n2(4);

    for (int i = 0; i < 50; i++) {
        int a1 = rand() - RAND_MAX / 2;
        int a2 = rand() - RAND_MAX / 2;
        LongInt n1(a1);
        LongInt n2(a2);
        cout << "======= " << n1 << "   " << n2 << " =======" << endl;
        cout << n1 << " >  " << n2 << " ? " << ((n1 > n2) == 1 ? "TRUE" : "FALSE") << " | "
             << ((a1 > a2) == 1 ? "TRUE" : "FALSE") << endl;
        cout << n1 << " >= " << n2 << " ? " << ((n1 >= n2) == 1 ? "TRUE" : "FALSE") << " | "
             << ((a1 >= a2) == 1 ? "TRUE" : "FALSE") << endl;
        cout << n1 << " != " << n2 << " ? " << ((n1 != n2) == 1 ? "TRUE" : "FALSE") << " | "
             << ((a1 != a2) == 1 ? "TRUE" : "FALSE") << endl;
        cout << n1 << " +  " << n2 << " ? " << (n1 + n2) << " | " << (a1 + a2) << endl;
        cout << n1 << " -  " << n2 << " ? " << (n1 - n2).number << " | " << (a1 - a2) << endl;
        cout << n1 << " *  " << n2 << " ? " << (n1 * n2) << " | " << (a1 * a2) << endl;
        cout << n1 << " /  " << n2 << " ? " << (n1 / n2) << " | " << (a1 / a2) << endl;
        cout << n1 << " %  " << n2 << " ? " << (n1 % n2) << " | " << (a1 % a2) << endl;
        cout << "=========================================" << endl << endl;
    }

    return 0;
}
