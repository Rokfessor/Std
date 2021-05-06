package Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    public final static String CREDIT_TYPE = "CREDIT", DEBIT_TYPE = "DEBIT", ACCUMULATION_TYPE = "ACCUMULATION";

    private long id;
    private double amount;
    private long ownerId;
    private String type;

    @Override
    public String toString() {
        return id + " " + type;
    }
}
