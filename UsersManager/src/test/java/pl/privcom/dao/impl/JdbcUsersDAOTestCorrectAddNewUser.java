package pl.privcom.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.privcom.dao.exceptions.UserExistInDatabase;
import pl.privcom.model.UserEntity;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksander Domagała on 06/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class JdbcUsersDAOTestCorrectAddNewUser {
    private static final String LOGIN = "test_2";
    private static final String FIRST_NAME = "Marek";
    private static final String LAST_NAME = "Łupacz";
    private static final String MAIL = "m@test.pl";
    private static final String PASSWORD = "123asdCV@#";

    private SessionFactory sessionFactory;

    private JdbcUsersDAO jdbcUsersDAO;

    private UserEntity userExpected;
    private UserEntity userToInsert;
    private UserEntity userFromDatabase;

    private Integer availableUserId;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setJdbcUsersDAO(JdbcUsersDAO jdbcUsersDAO) {
        this.jdbcUsersDAO = jdbcUsersDAO;
    }

    @Before
    public void initializeTestElements() {
        getNextAvailableValueFromSequences();

        createUserExpected();
        createUserToInsert();
    }

    @Test
    @Transactional
    @Rollback
    public void correctAddedUserToDatabase() throws UserExistInDatabase {
        insertNewUserToDatabase();

        getUserFromDatabase();

        validateUser();
        validateUserElements();
    }

    private void createUserExpected() {
        userExpected = new UserEntity();
        userExpected.setId(availableUserId);
        userExpected.setLogin(LOGIN);
        userExpected.setFirstName(FIRST_NAME);
        userExpected.setLastName(LAST_NAME);
        userExpected.setMail(MAIL);
        userExpected.setPassword(PASSWORD);
    }

    private void createUserToInsert() {
        userToInsert = new UserEntity();
        userToInsert.setLogin(LOGIN);
        userToInsert.setFirstName(FIRST_NAME);
        userToInsert.setLastName(LAST_NAME);
        userToInsert.setMail(MAIL);
        userToInsert.setPassword(PASSWORD);
    }

    private void insertNewUserToDatabase() throws UserExistInDatabase {
        jdbcUsersDAO.addNewUser(userToInsert);
    }

    private void getUserFromDatabase() {
        userFromDatabase = (UserEntity) sessionFactory.getCurrentSession()
                .createQuery("from UserEntity where id=:id")
                .setParameter("id", availableUserId).uniqueResult();
    }

    private void getNextAvailableValueFromSequences() {
        BigInteger lastValue = (BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT last_value FROM users_id_seq").getSingleResult();

        availableUserId = lastValue.intValue() + 1;
    }

    private void validateUser() {
        assertEquals(userExpected, userFromDatabase);
    }

    private void validateUserElements() {
        assertEquals(availableUserId, userFromDatabase.getId());
        assertEquals(LOGIN, userFromDatabase.getLogin());
        assertEquals(FIRST_NAME, userFromDatabase.getFirstName());
        assertEquals(LAST_NAME, userFromDatabase.getLastName());
        assertEquals(PASSWORD, userFromDatabase.getPassword());
        assertEquals(MAIL, userFromDatabase.getMail());
    }
}