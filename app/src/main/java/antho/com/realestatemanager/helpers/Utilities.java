package antho.com.realestatemanager.helpers;
/** Utilities **/
import android.content.Context;
import android.net.wifi.WifiManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** Utilities class provided by Openclassrooms for the project **/
public class Utilities
{
     // Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
    public static int convertDollarToEuro(int dollars)
    {
        return (int) Math.round(dollars * 0.812);
    }
     // Conversion de la date d'aujourd'hui en un format plus approprié
    public static String getTodayDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);
        return dateFormat.format(new Date());
    }
    // Vérification de la connexion réseau
    public static Boolean isInternetAvailable(Context context)
    {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        return wifi.isWifiEnabled();
    }
}
