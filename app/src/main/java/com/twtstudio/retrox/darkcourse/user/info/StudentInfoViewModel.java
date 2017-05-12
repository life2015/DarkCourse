package com.twtstudio.retrox.darkcourse.user.info;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.info.bean.StudentBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 09/05/2017.
 */

public class StudentInfoViewModel extends BaseObservable implements ViewModel {

    private StudentBean student = new StudentBean();

    public final ObservableField<String> totalScore = new ObservableField<>();
    public final ObservableField<String> totalCredit = new ObservableField<>();


    public final ReplyCommand refreshCommand = new ReplyCommand(this::getData);

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean refreshState = new ObservableBoolean(false);
    }

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

    public void getData() {
        viewStyle.refreshState.set(true);
        StudentApiClient.studentApi.getInfo()
                .subscribeOn(Schedulers.io())
                .map(studentBeanApiResponse -> studentBeanApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(()->viewStyle.refreshState.set(false))
                .subscribe(this::setStudent, new RxErrorHandler());

        StudentApiClient.studentApi.getTotalScore()
                .subscribeOn(Schedulers.io())
                .map(doubleApiResponse -> doubleApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(totalScore::set, new RxErrorHandler());

        StudentApiClient.studentApi.getTotalCredit()
                .subscribeOn(Schedulers.io())
                .map(doubleApiResponse -> doubleApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(totalCredit::set, new RxErrorHandler());
    }
}
