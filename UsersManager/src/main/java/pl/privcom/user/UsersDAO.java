package pl.privcom.user;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pl.privcom.infrastructure.exceptions.UserExistInDatabase;
import pl.privcom.infrastructure.JdbcBaseDAO;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
@Repository
public class UsersDAO extends JdbcBaseDAO {

    public UsersDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public UserEntity getUserById(final Integer id) {
        return (UserEntity) getOneElement("from UserEntity where id=:id", "id", id);
    }

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
