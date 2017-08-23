package tec.hie.la.bronx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.adapter.DatabasesAdapter;
import tec.hie.la.bronx.databinding.FragmentDatabasesBinding;

public class DatabasesFragment extends Fragment {

    private FragmentDatabasesBinding binding;

    private View view;

    public static final String ARG_PAGE = "ARG_PAGE";

    public static DatabasesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DatabasesFragment fragment = new DatabasesFragment();
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
        view = inflater.inflate(R.layout.fragment_databases, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentDatabasesBinding.bind(getView());
        list();
    }

    public void list() {

        DatabasesAdapter databasesAdapter = new DatabasesAdapter(getActivity(), Arrays.asList(getActivity().databaseList()));
        binding.databases.setAdapter(databasesAdapter);

        int firstItemIndex = 0;
        View item = databasesAdapter.getView(firstItemIndex, null, binding.databases);
        item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        ViewGroup.LayoutParams layoutParams = binding.databases.getLayoutParams();
        layoutParams.height = item.getMeasuredHeight() * databasesAdapter.getCount() +
                (binding.databases.getDividerHeight() * (databasesAdapter.getCount() - 1));
        binding.databases.setLayoutParams(layoutParams);
    }
}