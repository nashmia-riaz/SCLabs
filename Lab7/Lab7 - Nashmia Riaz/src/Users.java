/**
 * Created by Taha on 29/03/2017.
 */
import javax.persistence.*;


public class Users {
    

    private String userid;
    private String scheme;
    private int usersid;
    private float totaltime;
    private String totalstate;
    private float t1time;
    private String t1state;
    private float t2time;
    private String t2state;
    private float t3time;
    private String t3state;
    private float t4time;
    private String t4state;
    private float t5time;
    private String t5state;
    private float t6time;
    private String t6state;
    private float t7time;
    private String t7state;

    public Users(String userid, String scheme, float totaltime, String totalstate, float t1time, String t1state, float t2time, String t2state, float t3time, String t3state, float t4time, String t4state, float t5time, String t5state, float t6time, String t6state, float t7time, String t7state) {
        this.userid = userid;
        this.scheme = scheme;
        this.totaltime = totaltime;
        this.totalstate = totalstate;
        this.t1time = t1time;
        this.t1state = t1state;
        this.t2time = t2time;
        this.t2state = t2state;
        this.t3time = t3time;
        this.t3state = t3state;
        this.t4time = t4time;
        this.t4state = t4state;
        this.t5time = t5time;
        this.t5state = t5state;
        this.t6time = t6time;
        this.t6state = t6state;
        this.t7time = t7time;
        this.t7state = t7state;
    }

    public Users() {
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public int getUsersid() {
        return usersid;
    }

    public void setUsersid(int userid) {
        this.usersid = userid;
    }

    public float getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(float totaltime) {
        this.totaltime = totaltime;
    }

    public String getTotalstate() {
        return totalstate;
    }

    public void setTotalstate(String totalstate) {
        this.totalstate = totalstate;
    }

    public float getT1time() {
        return t1time;
    }

    public void setT1time(float t1time) {
        this.t1time = t1time;
    }

    public String getT1state() {
        return t1state;
    }

    public void setT1state(String t1state) {
        this.t1state = t1state;
    }

    public float getT2time() {
        return t2time;
    }

    public void setT2time(float t2time) {
        this.t2time = t2time;
    }

    public String getT2state() {
        return t2state;
    }

    public void setT2state(String t2state) {
        this.t2state = t2state;
    }

    public float getT3time() {
        return t3time;
    }

    public void setT3time(float t3time) {
        this.t3time = t3time;
    }

    public String getT3state() {
        return t3state;
    }

    public void setT3state(String t3state) {
        this.t3state = t3state;
    }

    public float getT4time() {
        return t4time;
    }

    public void setT4time(float t4time) {
        this.t4time = t4time;
    }

    public String getT4state() {
        return t4state;
    }

    public void setT4state(String t4state) {
        this.t4state = t4state;
    }

    public float getT5time() {
        return t5time;
    }

    public void setT5time(float t5time) {
        this.t5time = t5time;
    }

    public String getT5state() {
        return t5state;
    }

    public void setT5state(String t5state) {
        this.t5state = t5state;
    }

    public float getT6time() {
        return t6time;
    }

    public void setT6time(float t6time) {
        this.t6time = t6time;
    }

    public String getT6state() {
        return t6state;
    }

    public void setT6state(String t6state) {
        this.t6state = t6state;
    }

    public float getT7time() {
        return t7time;
    }

    public void setT7time(float t7time) {
        this.t7time = t7time;
    }

    public String getT7state() {
        return t7state;
    }

    public void setT7state(String t7state) {
        this.t7state = t7state;
    }
}