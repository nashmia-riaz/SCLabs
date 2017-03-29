/**
 * Created by nashm on 29/03/2017.
 */
public class areaLocation {
    private int id;
    private String country;
    private String region;
    private String city;
    private int postalCode;
    private float latitude;
    private float longitude;
    private int metroCode;
    private int areaCode;

    public areaLocation(){}

    public areaLocation(String co, float la, float lo){
        this.country = co;
        this.latitude = la;
        this.longitude = lo;
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

    public void setPostalCode(int pc){
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

    public int getPostalCode(){
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
