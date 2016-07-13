package pl.privcom.dao.impl;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import pl.privcom.model.UserEntity;

/**
 * Created by Aleksander Domagała on 13/07/2016.
 */
abstract class JdbcUsersDAOTestBase {
    JdbcUsersDAO jdbcUsersDAO;

    UserEntity expectedUser;
    UserEntity testedUser;

    @Autowired
    public void setJdbcUsersDAO(JdbcUsersDAO jdbcUsersDAO) {
        this.jdbcUsersDAO = jdbcUsersDAO;
    }

    void assertEqualsTestedUserToExpectedUser() {
        assertEquals(expectedUser, testedUser);
    }
}
