package pt.vcardoso.users.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String uuid;

    private String name;

    private String email;

    public User() {
        this.uuid = UUID.randomUUID().toString();
    }

    public User(String name, String email) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [uuid=" + uuid + ", name=" + name + ", email=" + email + "]";
    }

}
