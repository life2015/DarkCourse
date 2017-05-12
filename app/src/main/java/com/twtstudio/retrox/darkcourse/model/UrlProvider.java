package com.twtstudio.retrox.darkcourse.model;

/**
 * Created by retrox on 06/05/2017.
 */

public class UrlProvider {
    static String url = "172.23.129.15:8080";
    public static String getUrl(){
        return "http://"+UrlProvider.url+"/";
    }

    public static void setUrl(String url) {
        UrlProvider.url = url;
    }

}
