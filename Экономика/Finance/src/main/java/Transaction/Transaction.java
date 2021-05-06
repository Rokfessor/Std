package Transaction;

import Account.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    public final String CREDIT_TYPE = "CREDIT", DEBIT_TYPE = "DEBIT", ACCUMULATION_TYPE = "ACCUMULATION";

    private long id;
    private double amount;
    private long timestamp;
    private Account fromAccount;
    private Account toAccount;
    private long fromSubjectId;
    private long toSubjectId;
}
