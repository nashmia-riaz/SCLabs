/**
 * Created by nashm on 29/03/2017.
 */
public class areaLocation {
    private int id;
    private String country;
    private String region;
    private String city;
    private String postalCode;
    private float latitude;
    private float longitude;
    private int metroCode;
    private int areaCode;

    public areaLocation(){}

    public areaLocation(int id, String co, String re, String ci, String pc, float lat, float lon, int mc, int ac){
        this.id = id;
        this.country = co;
        this.region = re;
        this.city = ci;
        this.postalCode = pc;
        this.latitude = lat;
        this.longitude = lon;
        this.metroCode = mc;
        this.areaCode = ac;
    }

    public areaLocation(int id, String co, String re, String ci, String pc, float lat, float lon){
        this.id = id;
        this.country = co;
        this.region = re;
        this.city = ci;
        this.postalCode = pc;
        this.latitude = lat;
        this.longitude = lon;
    }
/***********Setters********************/

    public void setId(int newid){
        this.id = newid;
    }
    public void setCountry(String co){
        this.country = co;
    }

    public void setRegion(String re){
        this.region = re;
    }

    public void setCity(String ci){
        this.city = ci;
    }

    public void setPostalCode(String pc){
        this.postalCode = pc;
    }

    public void setLatitude(float la){
        this.latitude = la;
    }

    public void setLongitude(float lo){
        this.longitude = lo;
    }

    public void setMetroCode(int mc){
        this.metroCode = mc;
    }

    public void setAreaCode(int ac){
        this.areaCode = ac;
    }
/*************************************/

/**************Getters****************/
    public int getId(){
        return id;
    }

    public String getCountry(){
        return country;
    }

    public String getRegion(){
        return region;
    }

    public String getCity(){
        return city;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public float getLatitude(){
        return latitude;
    }

    public float getLongitude(){
        return longitude;
    }

    public int getMetroCode(){
        return metroCode;
    }

    public int getAreaCode(){
        return areaCode;
    }
/*************************************/
}
