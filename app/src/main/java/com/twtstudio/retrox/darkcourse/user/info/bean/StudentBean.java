package com.twtstudio.retrox.darkcourse.user.info.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by retrox on 01/05/2017.
 */
public class StudentBean {


    /**
     * enter_date : 2015-09-17
     * sname : 冀辰阳
     * sex : 男
     * enter_age : 18
     * class : 软件一班
     * sid : 3015204342
     */

    public String enter_date;
    public String sname;
    public String sex;
    public int enter_age;
    @SerializedName("class")
    public String classX;
    public String sid;
}
