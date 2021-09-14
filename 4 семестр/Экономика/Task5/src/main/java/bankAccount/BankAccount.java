package bankAccount;

import utils.UserType;

public class BankAccount {
    public String name, surname;
    public long personalAccountId;
    public long personalAccount;
    public int pin;
    public UserType userType;

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", personalAccountId=" + personalAccountId +
                ", personalAccount=" + personalAccount +
                ", pin=" + pin +
                ", userType=" + userType +
                '}';
    }
}
