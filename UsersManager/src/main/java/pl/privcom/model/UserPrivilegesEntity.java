package pl.privcom.model;

import javax.persistence.*;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
@Entity
@Table(name = "user_privileges", schema = "public", catalog = "priv_com")
public class UserPrivilegesEntity {
    private Integer id;
    private String level;
    private UserEntity usersByUserId;
    private PrivilegesEntity privilegesByPrivilegeId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "level", nullable = true, length = 3)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrivilegesEntity that = (UserPrivilegesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UserEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "privilege_id", referencedColumnName = "id", nullable = false)
    public PrivilegesEntity getPrivilegesByPrivilegeId() {
        return privilegesByPrivilegeId;
    }

    public void setPrivilegesByPrivilegeId(PrivilegesEntity privilegesByPrivilegeId) {
        this.privilegesByPrivilegeId = privilegesByPrivilegeId;
    }
}