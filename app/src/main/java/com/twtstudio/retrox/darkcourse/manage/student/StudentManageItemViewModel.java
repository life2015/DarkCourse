package com.twtstudio.retrox.darkcourse.manage.student;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.v4.content.ContextCompat;

import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.info.StudentInfoViewModel;
import com.twtstudio.retrox.darkcourse.user.info.bean.StudentBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 12/05/2017.
 */

public class StudentManageItemViewModel extends BaseObservable{

    private static int postion = 0;

    private String sid;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    private StudentBean student = new StudentBean();

    public final ObservableField<String> totalScore = new ObservableField<>();
    public final ObservableField<String> totalCredit = new ObservableField<>();

    public StudentManageItemViewModel(String sid) {
        postion++;
        this.sid = sid;
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
        StudentApiClient.studentApi.query(sid)
                .subscribeOn(Schedulers.io())
                .map(studentBeanApiResponse -> studentBeanApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setStudent,new RxErrorHandler());

        StudentApiClient.studentApi.getTotalScore(sid)
                .subscribeOn(Schedulers.io())
                .map(doubleApiResponse -> doubleApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(totalScore::set,new RxErrorHandler());

        StudentApiClient.studentApi.getTotalCredit(sid)
                .subscribeOn(Schedulers.io())
                .map(doubleApiResponse -> doubleApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(totalCredit::set,new RxErrorHandler());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentManageItemViewModel that = (StudentManageItemViewModel) o;

        if (color != that.color) return false;
        return student != null ? student.equals(that.student) : that.student == null;

    }

    @Override
    public int hashCode() {
        int result = color;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }
}
