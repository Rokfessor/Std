package utils;

import bankAccount.BankAccount;
import bankAccount.BankAccountStorage;

public class LogIn {
    public static BankAccount logIn(long personalAccountId, int pin) {
        for (BankAccount account : BankAccountStorage.bankAccounts) {
            if (account.personalAccountId == personalAccountId && account.pin == pin)
                return account;
        }
        return null;
    }
}
