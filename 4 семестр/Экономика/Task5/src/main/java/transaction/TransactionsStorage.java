package transaction;

import java.util.ArrayList;

public class TransactionsStorage {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static long getNewTransactionId(){
        if (transactions.size() == 0)
            return 100000L;
        else
            return ++transactions.get(transactions.size() - 1).transactionId;
    }
}
