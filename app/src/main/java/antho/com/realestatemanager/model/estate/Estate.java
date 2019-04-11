package antho.com.realestatemanager.model.estate;
/** Estate **/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.time.ZonedDateTime;

import antho.com.realestatemanager.model.agent.Agent;
/** Data model for estate objects **/
public interface Estate
{
    String getType();
    String getPrice();
    String getSurface();
    String getRooms();
    String getDescription();
    String getPhotoUrl();
    String getAddress();
    String getPointsOfInterest();
    boolean getStatus();
    ZonedDateTime getCreationDate();
    ZonedDateTime getSaleDate();
    //Agent getAgent();
    /*@Json(name="type")
    public abstract String type();
    @Json(name="price")
    public abstract String price();
    @Json(name="surface")
    public abstract String surface();
    @Json(name="rooms")
    public abstract String rooms();
    @Json(name="description")
    public abstract String description();
    @Json(name="thumbnail")
    public abstract String photoUrl();
    @Json(name="address")
    public abstract String address();
    @Json(name="poi")
    public abstract String pointsOfInterest();
    @Json(name="status")
    public abstract boolean status();
    @Json(name="creation_date")
    public abstract ZonedDateTime creationDate();
    @Json(name="sale_date")
    public abstract ZonedDateTime saleDate();
    @Json(name="agent")
    public abstract Agent agent();

    public static JsonAdapter<Estate> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Estate.MoshiJsonAdapter(moshi);
    }*/
}
