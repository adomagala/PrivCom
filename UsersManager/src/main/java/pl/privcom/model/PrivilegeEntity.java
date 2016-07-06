package pl.privcom.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aleksander Domagała on 29/06/2016.
 */
@Entity
@Table(name = "privileges", schema = "public", catalog = "priv_com")
public class PrivilegeEntity {
    private Integer id;
    private String name;
    private String description;
    private Collection<UserPrivilegeEntity> userPrivilegesById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivilegeEntity that = (PrivilegeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "privilegesByPrivilegeId")
    public Collection<UserPrivilegeEntity> getUserPrivilegesById() {
        return userPrivilegesById;
    }

    public void setUserPrivilegesById(Collection<UserPrivilegeEntity> userPrivilegesById) {
        this.userPrivilegesById = userPrivilegesById;
    }
}
