package com.twtstudio.retrox.darkcourse.model;

/**
 * Created by retrox on 06/05/2017.
 */

public class ApiResponse<T> {

    /**
     * data : @something
     * err_code : @the error code
     * message : @the message about the response
     */

    public T data;
    public String err_code;
    public String message;

}
