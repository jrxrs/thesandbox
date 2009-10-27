package Item5;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: 24-Oct-2009
 * Time: 13:33:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class Person {
    protected final Date birthDate;

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    public abstract boolean isBabyBoomer();
}
