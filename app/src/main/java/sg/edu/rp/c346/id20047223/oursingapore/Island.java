package sg.edu.rp.c346.id20047223.oursingapore;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String islandName;
    private String islandDesc;
    private int islandArea;
    private int rating;

    public Island(int id, String islandName, String islandDesc, int islandArea, int rating) {
        this.id = id;
        this.islandName = islandName;
        this.islandDesc = islandDesc;
        this.islandArea = islandArea;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIslandName() {
        return islandName;
    }

    public void setIslandName(String islandName) {
        this.islandName = islandName;
    }

    public String getIslandDesc() {
        return islandDesc;
    }

    public void setIslandDesc(String islandDesc) {
        this.islandDesc = islandDesc;
    }

    public int getIslandArea() {
        return islandArea;
    }

    public void setIslandArea(int islandArea) {
        this.islandArea = islandArea;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Island{" +
                "id=" + id +
                ", islandName='" + islandName + '\'' +
                ", islandDesc='" + islandDesc + '\'' +
                ", islandArea=" + islandArea +
                ", rating=" + rating +
                '}';
    }

}
