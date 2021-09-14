package Subject;

import Account.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class Subject {
    public static final String CLIENT_TYPE = "CLIENT",
            PUBLIC_ORGANISATION_TYPE = "PUBLIC_ORGANISATION", PRIVATE_ORGANISATION_TYPE = "PRIVATE_ORGANISATION";
    private String type;
    private String name;
    private Long id;
    private List<Account> accountList;
    private String login;
    private String password;
    private String securityWord;

    @Override
    public String toString() {
        return name;
    }
}
