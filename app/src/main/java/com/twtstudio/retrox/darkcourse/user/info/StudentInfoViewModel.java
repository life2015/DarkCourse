package com.twtstudio.retrox.darkcourse.user.info;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.info.bean.StudentBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 09/05/2017.
 */

public class StudentInfoViewModel extends BaseObservable{

    private StudentBean student = new StudentBean();

    public final ObservableField<String> totalScore = new ObservableField<>();
    public final ObservableField<String> totalCredit = new ObservableField<>();

    public StudentInfoViewModel() {
        getData();
    }

    @Bindable
    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
        notifyPropertyChanged(BR.student);
    }

    public void getData(){
        StudentApiClient.studentApi.getInfo()
                .subscribeOn(Schedulers.io())
                .map(studentBeanApiResponse -> studentBeanApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setStudent,new RxErrorHandler());

        StudentApiClient.studentApi.getTotalScore()
                .subscribeOn(Schedulers.io())
                .map(doubleApiResponse -> doubleApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(totalScore::set,new RxErrorHandler());

        StudentApiClient.studentApi.getTotalCredit()
                .subscribeOn(Schedulers.io())
                .map(doubleApiResponse -> doubleApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(totalCredit::set,new RxErrorHandler());
    }
}
