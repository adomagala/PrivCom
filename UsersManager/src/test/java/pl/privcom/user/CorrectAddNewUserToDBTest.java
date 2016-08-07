package pl.privcom.user;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.privcom.infrastructure.exceptions.UserExistInDatabase;

import java.math.BigInteger;

/**
 * Created by Aleksander Domagała on 06/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class CorrectAddNewUserToDBTest extends UsersEqualsTestBase {
    @Autowired
    private SessionFactory sessionFactory;

    private Integer availableUserId;

    @Before
    public void initializeTestElements() {
        getNextAvailableValueFromSequence();

        createExpectedUser();
    }

    @Test
    @Transactional
    @Rollback
    public void testCorrectAddedUserToDatabase() throws UserExistInDatabase {
        insertNewUserToDatabase();

        getUserFromDatabase();

        assertEqualsTestedUserToExpectedUser();
    }

    private void getNextAvailableValueFromSequence() {
        BigInteger lastValue = getCurentValueFromSequence();

        availableUserId = lastValue.intValue() + 1;
    }

    private BigInteger getCurentValueFromSequence() {
        return (BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT last_value FROM users_id_seq").getSingleResult();
    }

    private void createExpectedUser() {
        expectedUser = new UserEntity();
        expectedUser.setId(availableUserId);
        expectedUser.setLogin("test_2");
        expectedUser.setFirstName("Marek");
        expectedUser.setLastName("Łupacz");
        expectedUser.setMail("m@test.pl");
        expectedUser.setPassword("123asdCV@#");
    }

    private void insertNewUserToDatabase() throws UserExistInDatabase {
        usersDAO.addNewUser(expectedUser);
    }

    private void getUserFromDatabase() {
        testedUser = (UserEntity) sessionFactory.getCurrentSession()
                .createQuery("from UserEntity where id=:id")
                .setParameter("id", availableUserId).uniqueResult();
    }
}