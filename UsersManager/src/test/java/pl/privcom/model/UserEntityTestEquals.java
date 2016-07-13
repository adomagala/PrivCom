package pl.privcom.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aleksander Domagała on 13/07/2016.
 */
public class UserEntityTestEquals {
    private static final Integer ID = 21;
    private static final String LOGIN = "test_te";
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Żółć";
    private static final String PASSWORD = "123qweRTY%$*";
    private static final String MAIL = "jan@test.pl";

    private UserEntity modelUser;
    private UserEntity testedUser;

    @Before
    public void initializeTestElements() {
        createModelUser();
    }

    @Test
    public void testEmptyUserNotEqualsModelUser() {
        createEmptyTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithOnlyCorrectIdNotEqualsModelUser() {
        createEmptyTestedUser();
        addCorrectIdToTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithOnlyCorrectLoginNotEqualsModelUser() {
        createEmptyTestedUser();
        addCorrectLoginToTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithOnlyCorrectFirstNameNotEqualsModelUser() {
        createEmptyTestedUser();
        addCorrectFirstNameToTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithOnlyCorrectLastNameNotEqualsModelUser() {
        createEmptyTestedUser();
        addCorrectLastNameToTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithOnlyCorrectPasswordNotEqualsModelUser() {
        createEmptyTestedUser();
        addCorrectPasswordToTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithOnlyCorrectMailNotEqualsModelUser() {
        createEmptyTestedUser();
        addCorrectMaiToTestedUser();

        checkUsersNotEqual();
    }

    @Test
    public void testUserWithAllCorrectDataNotEqualsModelUser() {
        createTestedUserWithAllCorrectData();

        checkUsersEqual();
    }

    private void createModelUser() {
        modelUser = new UserEntity();
        modelUser.setId(ID);
        modelUser.setLogin(LOGIN);
        modelUser.setFirstName(FIRST_NAME);
        modelUser.setLastName(LAST_NAME);
        modelUser.setPassword(PASSWORD);
        modelUser.setMail(MAIL);
    }

    private void createEmptyTestedUser() {
        testedUser = new UserEntity();
    }

    private void addCorrectIdToTestedUser() {
        testedUser.setId(ID);
    }

    private void addCorrectLoginToTestedUser() {
        testedUser.setLogin(LOGIN);
    }

    private void addCorrectFirstNameToTestedUser() {
        testedUser.setFirstName(FIRST_NAME);
    }

    private void addCorrectLastNameToTestedUser() {
        testedUser.setLastName(LAST_NAME);
    }

    private void addCorrectPasswordToTestedUser() {
        testedUser.setPassword(PASSWORD);
    }

    private void addCorrectMaiToTestedUser() {
        testedUser.setMail(MAIL);
    }

    private void createTestedUserWithAllCorrectData() {
        createEmptyTestedUser();
        addCorrectIdToTestedUser();
        addCorrectLoginToTestedUser();
        addCorrectFirstNameToTestedUser();
        addCorrectLastNameToTestedUser();
        addCorrectPasswordToTestedUser();
        addCorrectMaiToTestedUser();
    }

    private void checkUsersNotEqual() {
        assertFalse(testedUser.equals(modelUser));
        assertFalse(modelUser.equals(testedUser));
    }

    private void checkUsersEqual() {
        assertTrue(testedUser.equals(modelUser));
        assertTrue(modelUser.equals(testedUser));
    }
}