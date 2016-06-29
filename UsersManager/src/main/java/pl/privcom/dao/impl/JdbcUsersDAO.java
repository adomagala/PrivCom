package pl.privcom.dao.impl;

import org.springframework.stereotype.Repository;
import pl.privcom.dao.UsersDAO;
import pl.privcom.model.UserEntity;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
@Repository
public class JdbcUsersDAO extends JdbcBaseDAO implements UsersDAO {

    public UserEntity getUserByLogin(String login) {
        return (UserEntity) getOneElement("from UserEntity where login=:login", "login", login);

    }

    public UserEntity getUserByMail(String mail) {
        return (UserEntity) getOneElement("from UserEntity where mail=:mail", "mail", mail);
    }
}
