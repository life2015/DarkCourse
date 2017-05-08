package com.twtstudio.retrox.darkcourse.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.twtstudio.retrox.darkcourse.APP;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 2017/2/4.
 */

public class RxErrorHandler implements Action1<Throwable> {

    private Context mContext;

    public RxErrorHandler(Context context) {
        mContext = context;
    }

    public RxErrorHandler() {
        mContext = APP.getContext();
    }

    @Override
    public void call(Throwable throwable) {

        if (throwable instanceof IOException) {
            IOException error = (IOException) throwable;
            Toasty.error(mContext, "网络错误", Toast.LENGTH_SHORT).show();
            postThrowable(error);
            Logger.e(error, "error");
        } else if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            postThrowable(exception);
            Logger.e(exception, "http_error");
            try {
                String errorJson = exception.response().errorBody().string();
                Logger.e(errorJson);
                JSONObject errJsonObject = new JSONObject(errorJson);
                int errcode = errJsonObject.getInt("err_code");
                String message = errJsonObject.getString("message");
                Logger.e("错误码：" + errcode + "  message:" + message);
                Toasty.error(mContext, "错误：" + message , Toast.LENGTH_SHORT).show();
//                handleApiError(errcode);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Toasty.error(mContext,"Http ErrCode："+exception.response().code(),Toast.LENGTH_SHORT).show();
            }
        } else {
            postThrowable(throwable);
            throwable.printStackTrace();
        }
    }

    /**
     * post throwable to server
     * @param throwable
     */
    private void postThrowable(Throwable throwable){
//        BuglyLog.e("捕获的异常",throwable.getMessage(),throwable);
        throwable.printStackTrace();
    }

    private void handleApiError(int err_code) {
        switch (err_code) {
            case 10000:
            case 10001:
            case 10002:
                break;
            case 20001:
                break;
            default:

                break;
        }
    }
}