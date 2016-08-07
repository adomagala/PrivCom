package pl.privcom.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.privcom.infrastructure.exceptions.UserExistInDatabase;

/**
 * Created by Aleksander Domagała on 07/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class FailureAddNewUserToDBTest extends UsersEqualsTestBase {
    private static final Integer ID = 1;
    private static final String LOGIN = "test_1";
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Żółć";
    private static final String PASSWORD = "123qweRTY%$*";
    private static final String MAIL = "jan@test.pl";

    private UserEntity userWhichExistInDatabase;

    @Before
    public void createUserWhichExistInDatabase() {
        userWhichExistInDatabase = new UserEntity();
        userWhichExistInDatabase.setId(ID);
        userWhichExistInDatabase.setLogin(LOGIN);
        userWhichExistInDatabase.setFirstName(FIRST_NAME);
        userWhichExistInDatabase.setLastName(LAST_NAME);
        userWhichExistInDatabase.setMail(MAIL);
        userWhichExistInDatabase.setPassword(PASSWORD);
    }

    @Test(expected = UserExistInDatabase.class)
    @Transactional
    @Rollback
    public void testFailureAddedUserWithAllVariablesToDatabase() throws UserExistInDatabase {
        addNewUser();
    }

    private void addNewUser() throws UserExistInDatabase {
        usersDAO.addNewUser(userWhichExistInDatabase);
    }
}