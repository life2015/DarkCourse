package com.twtstudio.retrox.darkcourse.user.course.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by retrox on 07/05/2017.
 */

public class StudentData {


    /**
     * data : {"enter_date":"2015-09-17","sname":"冀辰阳","sex":"男","enter_age":18,"class":"软件一班","sid":"3015204342"}
     * err_code : -1
     * message : success
     */

    public DataBean data;
    public int err_code;
    public String message;

    public static class DataBean {
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
}
