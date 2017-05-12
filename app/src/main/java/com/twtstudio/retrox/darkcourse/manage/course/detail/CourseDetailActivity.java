package com.twtstudio.retrox.darkcourse.manage.course.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.BaseActivity;
import com.twtstudio.retrox.darkcourse.databinding.ActivityCourseScoreBinding;
import com.twtstudio.retrox.darkcourse.login.LoginActivity;
import com.twtstudio.retrox.darkcourse.manage.ManageActivity;
import com.twtstudio.retrox.darkcourse.manage.course.CourseAddActivity;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 13/05/2017.
 */

public class CourseDetailActivity extends BaseActivity {

    private String cid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");
        ActivityCourseScoreBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_course_score);
        CourseScoreListViewModel viewModel = new CourseScoreListViewModel(cid);
        binding.setViewModel(viewModel);
        setTitle("");
        setSupportActionBar(binding.toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.score_menu_average:

                CourseApiClient.courseApi.getAverageScore(cid)
                        .subscribeOn(Schedulers.io())
                        .map(stringApiResponse -> stringApiResponse.data)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            new AlertDialog.Builder(this).setTitle("Average Score")
                                    .setMessage(s)
                                    .show();
                        },new RxErrorHandler());

                break;
            case R.id.score_menu_manage:
                new AlertDialog.Builder(this)
                        .setTitle("Manage")
                        .setMessage("Do you want to delete this course ? \n or update the course")
                        .setNeutralButton("Update Information",(dialog, which) -> {

                            // update
                            Intent intent = new Intent(this,CourseAddActivity.class);
                            intent.putExtra("cid",cid);
                            intent.putExtra("update",true);
                            this.startActivity(intent);

                        })
                        .setPositiveButton("Yes!",(dialog, which) -> {
                            CourseApiClient.courseApi.deleteCourse(cid)
                                    .subscribeOn(Schedulers.io())
                                    .map(booleanApiResponse -> booleanApiResponse.data)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(aBoolean -> {
                                        if (aBoolean) {
                                            Toasty.success(this,"Delete success!").show();
//                                    Messenger.getDefault().sendNoMsg(CourseListViewModel.TOKEN_MY_COURSE_REFRESH);
                                        }
                                    }, new RxErrorHandler());
                        }).setNegativeButton("No",(dialog, which) -> {})
                .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
