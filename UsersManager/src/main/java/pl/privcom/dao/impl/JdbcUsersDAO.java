package pl.privcom.dao.impl;

import org.springframework.stereotype.Repository;
import pl.privcom.dao.UsersDAO;
import pl.privcom.dao.exceptions.UserExistInDatabase;
import pl.privcom.model.UserEntity;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
@Repository
public class JdbcUsersDAO extends JdbcBaseDAO implements UsersDAO {

    public UserEntity getUserByLogin(final String login) {
        return (UserEntity) getOneElement("from UserEntity where login=:login", "login", login);

    }

    public UserEntity getUserByMail(final String mail) {
        return (UserEntity) getOneElement("from UserEntity where mail=:mail", "mail", mail);
    }

    public void addNewUser(final UserEntity userToAdd) throws UserExistInDatabase {
        if (userNotExistsInDatabase(userToAdd)) {
            saveUserToDatabase(userToAdd);
        } else {
            throwUserExistException(userToAdd);
        }
    }

    private boolean userNotExistsInDatabase(final UserEntity userToCheck) {
        return getUserByLogin(userToCheck.getLogin()) == null;
    }

    private void saveUserToDatabase(final UserEntity userToSave) {
        getCurrentSession().save(userToSave);
    }

    private void throwUserExistException(final UserEntity userWhichExistInDatabase) throws UserExistInDatabase {
        throw new UserExistInDatabase(userWhichExistInDatabase);
    }
}
