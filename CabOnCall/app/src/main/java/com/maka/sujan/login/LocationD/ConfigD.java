package com.maka.sujan.login.LocationD;

/**
 * Created by dell on 7/15/2016.
 */

public class ConfigD {

    // public static final String URL_ADD="http://10.0.2.2/coc/addLongLat.php";
    public static final String URL_ADD="http://192.168.0.150/LocDriver/addLongLat.php";
    public static final String URL_DELETE_LOC = "http://192.168.0.150/LocDriver/removeLongLat.php?id=";

    public static final String URL_RATING = "http://192.168.0.150/rating/getAllRating.php";
    public static final String URL_ADDU="http://192.168.0.150/LocDriver/addUpdatedLongLat.php";
    public static final String URL_DELETE_LOCU = "http://192.168.0.150/LocDriver/removeUpdatedLongLat.php?phone=";

  /*  public static final String URL_ADD="http://192.168.43.111/LocDriver/addLongLat.php";
    public static final String URL_DELETE_LOC = "http://192.168.43.111/LocDriver/removeLongLat.php?id=";

    public static final String URL_RATING = "http://192.168.43.111/rating/getAllRating.php";
    public static final String URL_ADDU="http://192.168.43.111/LocDriver/addUpdatedLongLat.php";
    public static final String URL_DELETE_LOCU = "http://192.168.43.111/LocDriver/removeUpdatedLongLat.php?phone=";
*/
    public static final String KEY_EMP_ID = "id";
    public static final String Longitude = "longitude";
    public static final String Latitude = "latitude";

    public static final String KEY_EMP_IDU = "phone";
    public static final String LongitudeU = "longitudeU";
    public static final String LatitudeU = "latitudeU";


    public static final String TAG_JSON_ARRAY_RATING="result";
    public static final String TAG_R_ID = "r_id";
    public static final String TAG_RATING = "rating";
    public static final String TAG_D_ID = "d_id";
    public static final String TAG_U_ID = "u_id";


}
