package com.twtstudio.retrox.darkcourse.user.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.databinding.FragmentMyInfoBinding;

/**
 * Created by retrox on 09/05/2017.
 */

public class StudentInfoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMyInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_info,container,false);
        binding.setViewModel(new StudentInfoViewModel());
        return binding.getRoot();
    }
}
