package pl.privcom.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.privcom.model.UserEntity;

/**
 * Created by Aleksander Domagała on 05/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class JdbcUsersDAOTestGetUser extends JdbcUsersDAOTestBase {
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
    public void getUserByLoginTest() {
        getUserByLogin();

        assertEqualsTestedUserToExpectedUser();
    }

    @Test
    @Transactional(readOnly = true)
    public void getUserByMailTest() {
        getUserByMail();

        assertEqualsTestedUserToExpectedUser();
    }

    @Test
    @Transactional(readOnly = true)
    public void getUserByIdTest() {
        getUserById();

        assertEqualsTestedUserToExpectedUser();
    }

    private void getUserByLogin() {
        testedUser = jdbcUsersDAO.getUserByLogin(LOGIN);
    }

    private void getUserByMail() {
        testedUser = jdbcUsersDAO.getUserByMail(MAIL);
    }

    private void getUserById() {
        testedUser = jdbcUsersDAO.getUserById(ID);
    }
}