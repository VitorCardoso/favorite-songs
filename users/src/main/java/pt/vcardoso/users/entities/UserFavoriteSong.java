package pt.vcardoso.users.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class UserFavoriteSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String uuid;

    private String userId;

    private String songId;

    public UserFavoriteSong() {
        this.uuid = UUID.randomUUID().toString();
    }

    public UserFavoriteSong(String userId, String songId) {
        this.uuid = UUID.randomUUID().toString();
        this.userId = userId;
        this.songId = songId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
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
        UserFavoriteSong other = (UserFavoriteSong) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserFavoriteSong [uuid=" + uuid + ", userId=" + userId + ", songId=" + songId + "]";
    }

}
