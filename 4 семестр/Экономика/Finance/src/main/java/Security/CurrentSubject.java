package Security;

import Exceptions.SecurityException;
import Subject.Subject;
import lombok.Getter;
import lombok.Setter;

public class CurrentSubject {
    @Getter
    @Setter
    private static Subject currentSubject;
}
