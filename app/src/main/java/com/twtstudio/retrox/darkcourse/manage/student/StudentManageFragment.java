package com.twtstudio.retrox.darkcourse.manage.student;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.databinding.FragmentStudentManageBinding;

/**
 * Created by retrox on 11/05/2017.
 */

public class StudentManageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentStudentManageBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_manage,container,false);
        binding.srlCourseManage.setColorSchemeResources(ColorPalette.getColors());
        binding.setViewModel(new StudentManageListViewModel());
        return binding.getRoot();
    }
}
