package com.maka.sujan.login.Map;


public class Config {

   /* //Address of our scripts of the CRUD
    public static final String URL_ADD="http://10.0.2.2/CRUD/addEmp.php";
    public static final String URL_GET_ALL = "http://10.0.2.2/CRUD/getAllEmp.php";
    public static final String URL_GET_EMP = "http://10.0.2.2/CRUD/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://10.0.2.2/CRUD/updateEmp.php";
    public static final String URL_DELETE_LOC = "http://10.0.2.2/CRUD/deleteEmp.php?id=";*/

   public static final String URL_GET_ALL = "http://192.168.0.150/coc/getLongLat.php";
   public static final String URL_DRIVER_INFO = "http://192.168.0.150/driver/include/getDriverInfo.php";
   public static final String URL_RATING = "http://192.168.0.150/rating/getAllRating.php";
   public static final String URL_ADD="http://192.168.0.150/rating/addRating.php";
   public static final String URL_DELETE_RATING = "http://192.168.0.150/rating/deleteRating.php?duID=";
   public static final String URL_GET_UPDATE = "http://192.168.0.150/coc/getLongLatUpdate.php";

/*
 public static final String URL_GET_ALL = "http://192.168.43.111/coc/getLongLat.php";
 public static final String URL_DRIVER_INFO = "http://192.168.43.111/driver/include/getDriverInfo.php";
 public static final String URL_RATING = "http://192.168.43.111/rating/getAllRating.php";
 public static final String URL_ADD="http://192.168.43.111/rating/addRating.php";
 public static final String URL_DELETE_RATING = "http://192.168.43.111/rating/deleteRating.php?duID=";
 public static final String URL_GET_UPDATE = "http://192.168.43.111/coc/getLongLatUpdate.php";
*/



 //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_LAT = "latitude";
    public static final String TAG_LONG = "longitude";

    public static final String TAG_JSON_ARRAY_UPDATE="resultU";
    public static final String TAG_PHONE_UPDATE = "phone";
    public static final String TAG_LAT_UPDATE = "latitudeU";
    public static final String TAG_LONG_UPDATE = "longitudeU";


    public static final String TAG_JSON_ARRAY_DRIVER="result";
    public static final String TAG_ID_DRIVER = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PHONE = "phone";

    public static final String KEY_RATING = "rating";
    public static final String DRIVER_ID = "driverID";
    public static final String USER_ID = "userID";
    public static final String DRIVERUSER_ID = "duID";




    public static final String TAG_JSON_ARRAY_RATING="result";
    public static final String TAG_R_ID = "r_id";
    public static final String TAG_RATING = "rating";
    public static final String TAG_D_ID = "d_id";
    public static final String TAG_U_ID = "u_id";



}