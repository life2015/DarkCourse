package com.twtstudio.retrox.darkcourse.model;

import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;
import com.twtstudio.retrox.darkcourse.user.course.bean.StudentData;
import com.twtstudio.retrox.darkcourse.user.info.bean.StudentBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by retrox on 06/05/2017.
 */

public interface StudentApi {

    @GET("login")
    Observable<StudentData> login(@Query("sid") String sid);

    @GET("login")
    Observable<ApiResponse<StudentBean>> query(@Query("sid") String sid);

    @GET("getMyCourses")
    Observable<MyCoursesBean> getMyCourses();

    @GET("choose")
    Observable<ApiResponse<Boolean>> chooseCourse(@Query("cid") String cid);

    @GET("exitCourse")
    Observable<ApiResponse<Boolean>> exitCourse(@Query("cid") String cid);

    @GET("getTotalScore")
    Observable<ApiResponse<String>> getTotalScore();

    @GET("getTotalScore")
    Observable<ApiResponse<String>> getTotalScore(@Query("sid") String sid);

    @GET("getTotalCredit")
    Observable<ApiResponse<String>> getTotalCredit();

    @GET("getTotalCredit")
    Observable<ApiResponse<String>> getTotalCredit(@Query("sid") String sid);

    @GET("login")
    Observable<ApiResponse<StudentBean>> getInfo();

    @GET("getAllStudent")
    Observable<ApiResponse<List<StudentBean>>> getAllStudent();

    @GET("getCourseScore")
    Observable<ApiResponse<String>> getCourseScore(@Query("cid") String cid);
}
