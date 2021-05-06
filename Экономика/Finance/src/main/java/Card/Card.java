package Card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    public final static String CREDIT_TYPE = "CREDIT", DEBIT_TYPE = "DEBIT";

    private long owner;
    private long account;
    private double amount;
    private long id;
    private String type;

    @Override
    public String toString() {
        return "Card{" +
                "owner=" + owner +
                ", account=" + account +
                ", amount=" + amount +
                ", id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
