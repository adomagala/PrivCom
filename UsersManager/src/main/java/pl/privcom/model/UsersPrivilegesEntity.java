package pl.privcom.model;

import javax.persistence.*;

/**
 * Created by Aleksander Domagała on 28/06/2016.
 */
@Entity
@Table(name = "users_privileges", schema = "public", catalog = "priv_com")
public class UsersPrivilegesEntity {
    private Integer id;
    private Integer userId;
    private Integer privilegeId;
    private String accessLevel;
    private UsersEntity usersByUserId;
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
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "privilege_id", nullable = false)
    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Basic
    @Column(name = "access_level", nullable = false, length = 3)
    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersPrivilegesEntity that = (UsersPrivilegesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (privilegeId != null ? !privilegeId.equals(that.privilegeId) : that.privilegeId != null) return false;
        if (accessLevel != null ? !accessLevel.equals(that.accessLevel) : that.accessLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (privilegeId != null ? privilegeId.hashCode() : 0);
        result = 31 * result + (accessLevel != null ? accessLevel.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
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
