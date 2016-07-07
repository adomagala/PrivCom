package pl.privcom.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.privcom.model.UserEntity;

/**
 * Created by Aleksander Domagała on 05/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springContext-test.xml"})
public class JdbcUsersDAOTestGetUser {
    private static final Integer ID = 1;
    private static final String LOGIN = "test_1";
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Żółć";
    private static final String PASSWORD = "123qweRTY%$*";
    private static final String MAIL = "jan@test.pl";

    private JdbcUsersDAO jdbcUsersDAO;

    private UserEntity user;

    @Autowired
    public void setJdbcUsersDAO(JdbcUsersDAO jdbcUsersDAO) {
        this.jdbcUsersDAO = jdbcUsersDAO;
    }

    @Test
    @Transactional(readOnly = true)
    public void getUserByLoginTest() {
        getUserByLogin();

        validateUser();
    }

    @Test
    @Transactional(readOnly = true)
    public void getUserByMailTest() {
        getUserByMail();

        validateUser();
    }

    private void getUserByLogin() {
        user = jdbcUsersDAO.getUserByLogin(LOGIN);
    }

    private void getUserByMail() {
        user = jdbcUsersDAO.getUserByMail(MAIL);
    }

    private void validateUser() {
        assertEquals(ID, user.getId());
        assertEquals(LOGIN, user.getLogin());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(MAIL, user.getMail());
    }
}