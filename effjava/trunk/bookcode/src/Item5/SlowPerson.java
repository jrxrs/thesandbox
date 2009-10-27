package Item5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: 24-Oct-2009
 * Time: 13:15:39
 * To change this template use File | Settings | File Templates.
 */

public class SlowPerson extends Person
{
    public SlowPerson(Date birthDate) {
        super(birthDate);
    }

    // DON'T DO THIS!
    @Override 
    public boolean isBabyBoomer() {
        // Unnecessary allocation of expensive object
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }

    public static void runGenericPeople(Class<? extends Person> P) {
        Person[] people = new Person[4];
        Calendar gCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gCal.set(1955, Calendar.JANUARY, 1, 0, 0, 0);
        Date dob = gCal.getTime();
        Class[] ctorArgs = new Class[1];
        ctorArgs[0] = Date.class;
        Constructor<? extends Person> construc;
        try {
            construc = P.getConstructor(ctorArgs);
            people[0] = construc.newInstance(dob);
            gCal.set(1985, Calendar.JANUARY, 1, 0, 0, 0);
            dob = gCal.getTime();
            people[1] = construc.newInstance(dob);
            gCal.set(1981, Calendar.JANUARY, 1, 0, 0, 0);
            dob = gCal.getTime();
            people[2] = construc.newInstance(dob);
            gCal.set(1953, Calendar.JANUARY, 1, 0, 0, 0);
            dob = gCal.getTime();
            people[3] = construc.newInstance(dob);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        } catch (InstantiationException ie) {
            ie.printStackTrace();
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        }

        System.out.println("Starting with " + P.getName() + "!");
        long begin = System.currentTimeMillis();
        int revs = (10000000 / 4);
        for(int i = 0; i < revs; i++) {
            people[0].isBabyBoomer();
            people[1].isBabyBoomer();
            people[2].isBabyBoomer();
            people[3].isBabyBoomer();
        }
        long end = System.currentTimeMillis();
        System.out.println(P.getName() + " took " + (end - begin) + "ms.");
    }

    public static void main(String args[]) {
        runGenericPeople(SlowPerson.class);
        runGenericPeople(QuickPerson.class);
    }
}
