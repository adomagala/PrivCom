package pl.privcom.infrastructure.exceptions;

import pl.privcom.user.UserEntity;

/**
 * Created by Aleksander Domagała on 07/07/2016.
 */
public class UserExistInDatabase extends DAOException {
    public UserExistInDatabase(final UserEntity userWhichExistInDatabase) {
        super("The user with login " + userWhichExistInDatabase.getLogin() + " exist in the database");
    }
}
