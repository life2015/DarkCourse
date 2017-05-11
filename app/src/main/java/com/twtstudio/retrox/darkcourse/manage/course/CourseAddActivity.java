package com.twtstudio.retrox.darkcourse.manage.course;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.BaseActivity;
import com.twtstudio.retrox.darkcourse.databinding.ActivityCourseAddBinding;

/**
 * Created by retrox on 11/05/2017.
 */

public class CourseAddActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDarkStatusIcon(true);
        ActivityCourseAddBinding activityCourseAddBinding = DataBindingUtil.setContentView(this,R.layout.activity_course_add);
        setTitle("");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String cid = intent.getStringExtra("cid");
        boolean update = intent.getBooleanExtra("update",false);

        if (update){
            activityCourseAddBinding.setViewModel(new CourseAddViewModel(update,cid));
        }else {
            activityCourseAddBinding.setViewModel(new CourseAddViewModel());
        }

    }

}
