package pl.privcom.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aleksander Domagała on 13/07/2016.
 */
public class UserEntityTestHashCode {
    private static final Integer ID = 213;
    private static final String LOGIN = "Hash";
    private static final String FIRST_NAME = "Code";
    private static final String LAST_NAME = "Żółć";
    private static final String PASSWORD = "123zx@@#";
    private static final String MAIL = "hashCode@test.pl";

    private UserEntity testedUser;

    private int correctHashCode;
    private int hashCodeForTestedUser;

    @Before
    public void calculateCorrectHashCode() {
        correctHashCode = ID.hashCode();
        correctHashCode = 31 * correctHashCode + LOGIN.hashCode();
        correctHashCode = 31 * correctHashCode + FIRST_NAME.hashCode();
        correctHashCode = 31 * correctHashCode + LAST_NAME.hashCode();
        correctHashCode = 31 * correctHashCode + PASSWORD.hashCode();
        correctHashCode = 31 * correctHashCode + MAIL.hashCode();
    }

    @Test
    public void testCalculateHashCodeForUserWithAllData() {
        createTestedUser();

        calculateHashCodeForTestedUser();

        checkHashCodesEqual();
    }
    
    private void createTestedUser() {
        testedUser = new UserEntity();
        testedUser.setId(ID);
        testedUser.setLogin(LOGIN);
        testedUser.setFirstName(FIRST_NAME);
        testedUser.setLastName(LAST_NAME);
        testedUser.setMail(MAIL);
        testedUser.setPassword(PASSWORD);
    }

    private void calculateHashCodeForTestedUser() {
        hashCodeForTestedUser = testedUser.hashCode();
    }

    private void checkHashCodesEqual() {
        assertTrue(hashCodeForTestedUser == correctHashCode);
    }
}