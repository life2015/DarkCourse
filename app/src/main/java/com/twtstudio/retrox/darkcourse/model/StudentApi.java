package com.twtstudio.retrox.darkcourse.model;

import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;
import com.twtstudio.retrox.darkcourse.user.course.bean.StudentData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by retrox on 06/05/2017.
 */

public interface StudentApi {

    @GET("login")
    Observable<StudentData> login(@Query("sid") String sid);

    @GET("getMyCourses")
    Observable<MyCoursesBean> getMyCourses();

    @GET("choose")
    Observable<ApiResponse<Boolean>> chooseCourse(@Query("cid") String cid);

    @GET("exitCourse")
    Observable<ApiResponse<Boolean>> exitCourse(@Query("cid") String cid);
}
