package color.measurement.com.from_cp20.module.database.lustre;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import color.measurement.com.from_cp20.R;
import color.measurement.com.from_cp20.base.BaseFragment;

/**
 * Created by wpc on 2017/2/28.
 */
@SuppressLint("ValidFragment")
public class LustreDBFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_lustre,container,false);

        return v;
    }
}
