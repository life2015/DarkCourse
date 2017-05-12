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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentBean that = (StudentBean) o;

        if (enter_age != that.enter_age) return false;
        if (enter_date != null ? !enter_date.equals(that.enter_date) : that.enter_date != null)
            return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (classX != null ? !classX.equals(that.classX) : that.classX != null) return false;
        return sid != null ? sid.equals(that.sid) : that.sid == null;

    }

    @Override
    public int hashCode() {
        int result = enter_date != null ? enter_date.hashCode() : 0;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + enter_age;
        result = 31 * result + (classX != null ? classX.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        return result;
    }
}
