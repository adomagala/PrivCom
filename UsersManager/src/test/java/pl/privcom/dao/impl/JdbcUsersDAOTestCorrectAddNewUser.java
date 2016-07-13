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

/**
 * Created by Aleksander Domagała on 06/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class JdbcUsersDAOTestCorrectAddNewUser extends JdbcUsersDAOTestBase {
    private SessionFactory sessionFactory;

    private Integer availableUserId;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Before
    public void initializeTestElements() {
        getNextAvailableValueFromSequences();

        createUserExpected();
    }

    @Test
    @Transactional
    @Rollback
    public void testCorrectAddedUserToDatabase() throws UserExistInDatabase {
        insertNewUserToDatabase();

        getUserFromDatabase();

        assertEqualsTestedUserToExpectedUser();
    }

    private void getNextAvailableValueFromSequences() {
        BigInteger lastValue = (BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT last_value FROM users_id_seq").getSingleResult();

        availableUserId = lastValue.intValue() + 1;
    }

    private void createUserExpected() {
        expectedUser = new UserEntity();
        expectedUser.setId(availableUserId);
        expectedUser.setLogin("test_2");
        expectedUser.setFirstName("Marek");
        expectedUser.setLastName("Łupacz");
        expectedUser.setMail("m@test.pl");
        expectedUser.setPassword("123asdCV@#");
    }

    private void insertNewUserToDatabase() throws UserExistInDatabase {
        jdbcUsersDAO.addNewUser(expectedUser);
    }

    private void getUserFromDatabase() {
        testedUser = (UserEntity) sessionFactory.getCurrentSession()
                .createQuery("from UserEntity where id=:id")
                .setParameter("id", availableUserId).uniqueResult();
    }
}