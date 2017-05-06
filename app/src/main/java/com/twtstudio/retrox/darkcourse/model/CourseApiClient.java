package com.twtstudio.retrox.darkcourse.model;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseApiClient {
    public static CourseApi courseApi = DefaultRetrofitBuilder
            .getBuilder()
            .baseUrl("http://"+UrlProvider.getUrl()+"course/")
            .build()
            .create(CourseApi.class);
}
