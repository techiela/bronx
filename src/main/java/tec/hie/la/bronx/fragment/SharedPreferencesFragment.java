package tec.hie.la.bronx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.Arrays;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.adapter.SharedPreferencesAdapter;
import tec.hie.la.bronx.databinding.FragmentSharedPreferencesBinding;

public class SharedPreferencesFragment extends Fragment {

    private FragmentSharedPreferencesBinding binding;

    private View view;

    public static final String ARG_PAGE = "ARG_PAGE";

    public static SharedPreferencesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SharedPreferencesFragment fragment = new SharedPreferencesFragment();
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
        view = inflater.inflate(R.layout.fragment_shared_preferences, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentSharedPreferencesBinding.bind(getView());
        list();
    }

    public void list() {
        File sharedPreferencesDir = new File(getActivity().getApplicationInfo().dataDir, "shared_prefs");
        if (!sharedPreferencesDir.exists() || !sharedPreferencesDir.isDirectory()) {
            return;
        }
        SharedPreferencesAdapter sharedPreferencesAdapter = new SharedPreferencesAdapter(getActivity(), Arrays.asList(sharedPreferencesDir.listFiles()));
        binding.sharedpreferences.setAdapter(sharedPreferencesAdapter);

        int firstItemIndex = 0;
        View item = sharedPreferencesAdapter.getView(firstItemIndex, null, binding.sharedpreferences);
        item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        ViewGroup.LayoutParams layoutParams = binding.sharedpreferences.getLayoutParams();
        layoutParams.height = item.getMeasuredHeight() * sharedPreferencesAdapter.getCount() +
                (binding.sharedpreferences.getDividerHeight() * (sharedPreferencesAdapter.getCount() - 1));
        binding.sharedpreferences.setLayoutParams(layoutParams);
    }
}