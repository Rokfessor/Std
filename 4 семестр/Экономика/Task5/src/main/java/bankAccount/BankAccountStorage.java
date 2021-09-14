package bankAccount;

import utils.UserType;

import java.util.ArrayList;

public class BankAccountStorage {
    public static ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public static BankAccount getByPersonalAccountId(long personalAccountId) {
        for (BankAccount acc : bankAccounts)
            if (acc.personalAccountId == personalAccountId)
                return acc;
        return null;
    }

    public static ArrayList<BankAccount> getBankAccountsByType(UserType type) {
        ArrayList<BankAccount> list = new ArrayList<>();
        for (BankAccount acc : bankAccounts)
            if (acc.userType == type)
                list.add(acc);

        return list;
    }
}
