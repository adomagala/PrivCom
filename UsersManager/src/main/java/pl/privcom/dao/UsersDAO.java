package pl.privcom.dao;

import pl.privcom.model.UserEntity;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
public interface UsersDAO {
    UserEntity getUserByLogin(String login);

    UserEntity getUserByMail(String mail);
}