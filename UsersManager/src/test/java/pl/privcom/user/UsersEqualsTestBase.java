package pl.privcom.user;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Aleksander Domagała on 13/07/2016.
 */
abstract class UsersEqualsTestBase {
    @Autowired
    UsersDAO usersDAO;

    UserEntity expectedUser;
    UserEntity testedUser;

    void assertEqualsTestedUserToExpectedUser() {
        assertEquals(expectedUser, testedUser);
    }
}
