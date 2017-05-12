package com.twtstudio.retrox.darkcourse.user.course;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.kelin.mvvmlight.messenger.Messenger;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseItemViewModel implements ViewModel {

    public String courseName = " ";
    public String teacherName = " ";
    public String credit = " ";
    public String cid;
    public final ObservableField<String> score = new ObservableField<>();

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setTitle(courseName)
                .setMessage("You want to exit this course?")
                .setPositiveButton("Yes", (dialog1, which) -> {
                    StudentApiClient.studentApi.exitCourse(cid)
                            .subscribeOn(Schedulers.io())
                            .map(booleanApiResponse -> booleanApiResponse.data)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aBoolean -> {
                                if (aBoolean){
                                    Toasty.success(view.getContext(),courseName+"\nExit Success").show();
                                    Messenger.getDefault().sendNoMsg(CourseListViewModel.TOKEN_MY_COURSE_REFRESH);
                                }
                            },new RxErrorHandler());
                }).setNegativeButton("No",(dialog1, which) -> {}).create();
        dialog.show();
    });

    public void getScore(){
        StudentApiClient.studentApi.getCourseScore(cid)
                .subscribeOn(Schedulers.io())
                .map(stringApiResponse -> stringApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(score::set,new RxErrorHandler());
    }

    private CourseItemViewModel(String courseName, String teacherName, Object credit) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.credit = String.valueOf(credit);
        postion++;
    }

    public CourseItemViewModel(MyCoursesBean.DataBean dataBean) {
        this(dataBean.cname, dataBean.teacher, dataBean.credit);
        cid = dataBean.cid;
        getScore();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseItemViewModel that = (CourseItemViewModel) o;

        if (color != that.color) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null)
            return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null)
            return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        return cid != null ? cid.equals(that.cid) : that.cid == null;

    }

    @Override
    public int hashCode() {
        int result = courseName != null ? courseName.hashCode() : 0;
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + color;
        return result;
    }
}
