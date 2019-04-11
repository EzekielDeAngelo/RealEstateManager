package antho.com.realestatemanager.db.entity;
/** Entity class **/
import java.time.ZonedDateTime;

import androidx.room.Entity;
import antho.com.realestatemanager.model.agent.Agent;
import antho.com.realestatemanager.model.estate.Estate;
/** Describes an estate entity**/
@Entity(tableName = "estate_table")
public class EstateEntity implements Estate
{
    public String type;
    public String price;
    public String surface;
    public String rooms;
    public String description;
    public String photoUrl;
    public String address;
    public String pointsOfInterest;
    public boolean status;
    public ZonedDateTime creationDate;
    public ZonedDateTime saleDate;
    public Agent agent;
    // Constructor
    public EstateEntity(String type, String price, String surface, String rooms, String description, String photoUrl, String address, String pointsOfInterest, boolean status, ZonedDateTime creationDate, ZonedDateTime saleDate/*, Agent agent*/)
    {
        this.type = type;
        this.price = price;
        this.surface = surface;
        this.rooms = rooms;
        this.description = description;
        this.photoUrl = photoUrl;
        this.address = address;
        this.pointsOfInterest = pointsOfInterest;
        this.status = status;
        this.creationDate = creationDate;
        this.saleDate = saleDate;
        //this.agent = agent;
    }
    // Getter and setter methods
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    @Override
    public String getSurface() { return surface; }
    public void setSurface(String surface) { this.surface = surface; }

    public String getRooms() { return rooms; }
    public void setRooms(String rooms) { this.rooms = rooms; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPointsOfInterest() { return pointsOfInterest; }
    public void setPointsOfInterest(String pointsOfInterest) { this.pointsOfInterest = pointsOfInterest; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ZonedDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(ZonedDateTime creationDate) { this.creationDate = creationDate; }

    public ZonedDateTime getSaleDate() { return saleDate; }
    public void setSaleDate(ZonedDateTime saleDate) { this.saleDate = saleDate; }

    /*public Agent getAgent() { return agent; }
    public void setAgent(Agent agent) { this.agent = agent; }*/


}
