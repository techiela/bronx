package tec.hie.la.bronx.fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.adapter.ActivitiesAdapter;
import tec.hie.la.bronx.databinding.FragmentActivitiesBinding;

public class ActivitiesFragment extends Fragment {

    private FragmentActivitiesBinding binding;

    private View view;

    public static final String ARG_PAGE = "ARG_PAGE";

    public static ActivitiesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ActivitiesFragment fragment = new ActivitiesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState アプリの前回終了時の状態を保持する
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activities, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentActivitiesBinding.bind(getView());
        list();
    }

    public void list() {
        // TODO use application context
        PackageManager packageManager = getActivity().getApplicationContext().getPackageManager();
        PackageInfo info = null;
        try {
            // TODO use application context
            info = packageManager.getPackageInfo(getActivity().getApplicationContext().getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ApplicationInfo test = info.applicationInfo;
        ActivityInfo[] list = info.activities;
        ActivitiesAdapter activitiesAdapter = new ActivitiesAdapter(getActivity(), Arrays.asList(list));
        binding.activities.setAdapter(activitiesAdapter);

        int firstItemIndex = 0;
        View item = activitiesAdapter.getView(firstItemIndex, null, binding.activities);
        item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        ViewGroup.LayoutParams layoutParams = binding.activities.getLayoutParams();
        layoutParams.height = item.getMeasuredHeight() * activitiesAdapter.getCount() +
                (binding.activities.getDividerHeight() * (activitiesAdapter.getCount() - 1));
        binding.activities.setLayoutParams(layoutParams);
    }
}