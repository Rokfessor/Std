package transaction;

public class Transaction {
    public long transactionId;
    public long fromPersonalAccountId;
    public long toPersonalAccountId;
    public long payment;
    public String data;

    @Override
    public String toString() {
        return "transaction.Transaction{" +
                "transactionId=" + transactionId +
                ", fromPersonalAccountId=" + fromPersonalAccountId +
                ", toPersonalAccountId=" + toPersonalAccountId +
                ", payment=" + payment +
                '}';
    }
}
