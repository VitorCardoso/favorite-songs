package pt.vcardoso.songs.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonData implements Serializable {

    private static final long serialVersionUID = 1L;

    public JsonData() {
    }

    public JsonData(List<TrackData> aTracks) {
        this.aTracks = aTracks;
    }

    private List<TrackData> aTracks;

    public List<TrackData> getaTracks() {
        return aTracks;
    }

    public void setaTracks(List<TrackData> aTracks) {
        this.aTracks = aTracks;
    }

    @Override
    public String toString() {
        return "JSON [aTracks=" + aTracks + "]";
    }
}
