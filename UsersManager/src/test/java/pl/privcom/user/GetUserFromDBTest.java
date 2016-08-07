package pl.privcom.user;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Aleksander Domagała on 05/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class GetUserFromDBTest extends UsersEqualsTestBase {
    private static final Integer ID = 1;
    private static final String LOGIN = "test_1";
    private static final String MAIL = "jan@test.pl";

    @Before
    public void createExpectedUser() {
        expectedUser = new UserEntity();
        expectedUser.setId(ID);
        expectedUser.setLogin(LOGIN);
        expectedUser.setFirstName("Jan");
        expectedUser.setLastName("Żółć");
        expectedUser.setPassword("123qweRTY%$*");
        expectedUser.setMail(MAIL);
    }

    @Test
    @Transactional(readOnly = true)
    public void testGetUserByLogin() {
        getUserByLogin();

        assertEqualsTestedUserToExpectedUser();
    }

    @Test
    @Transactional(readOnly = true)
    public void testGetUserByMail() {
        getUserByMail();

        assertEqualsTestedUserToExpectedUser();
    }

    @Test
    @Transactional(readOnly = true)
    public void testGetUserById() {
        getUserById();

        assertEqualsTestedUserToExpectedUser();
    }

    private void getUserByLogin() {
        testedUser = usersDAO.getUserByLogin(LOGIN);
    }

    private void getUserByMail() {
        testedUser = usersDAO.getUserByMail(MAIL);
    }

    private void getUserById() {
        testedUser = usersDAO.getUserById(ID);
    }
}