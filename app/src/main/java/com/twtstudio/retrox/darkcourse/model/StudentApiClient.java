package com.twtstudio.retrox.darkcourse.model;

/**
 * Created by retrox on 06/05/2017.
 */

public class StudentApiClient {
    public static StudentApi studentApi = DefaultRetrofitBuilder.getBuilder()
            .baseUrl(UrlProvider.getUrl()+"student/")
            .build().create(StudentApi.class);
}
