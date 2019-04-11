package antho.com.realestatemanager.model.agent;
/** Agent **/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/** Data model for agent objects**/
@AutoValue
public abstract class Agent
{
    @Json(name="name")
    public abstract String name();
    @Json(name="id")
    public abstract String id();
    @Json(name="phone")
    public abstract String phone();
    public static JsonAdapter<Agent> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Agent.MoshiJsonAdapter(moshi);
    }
}
