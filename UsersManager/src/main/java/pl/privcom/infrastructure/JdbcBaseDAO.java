package pl.privcom.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
public abstract class JdbcBaseDAO {

    protected SessionFactory sessionFactory;

    @Autowired
    public JdbcBaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Object getOneElement(String query, String key, Object value) {
        return getCurrentSession().createQuery(query).setParameter(key, value).uniqueResult();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
