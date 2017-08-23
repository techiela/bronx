package tec.hie.la.bronx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.adapter.FilesAdapter;
import tec.hie.la.bronx.databinding.FragmentFilesBinding;

public class FilesFragment extends Fragment {

    private FragmentFilesBinding binding;

    private View view;

    public static final String ARG_PAGE = "ARG_PAGE";

    public static FilesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FilesFragment fragment = new FilesFragment();
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
        view = inflater.inflate(R.layout.fragment_files, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentFilesBinding.bind(getView());
        list();
    }

    public void list() {
        FilesAdapter filesAdapter = new FilesAdapter(getActivity(), Arrays.asList(getActivity().getFilesDir().listFiles()));
        binding.files.setAdapter(filesAdapter);

        int firstItemIndex = 0;
        View item = filesAdapter.getView(firstItemIndex, null, binding.files);
        item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        ViewGroup.LayoutParams layoutParams = binding.files.getLayoutParams();
        layoutParams.height = item.getMeasuredHeight() * filesAdapter.getCount() +
                (binding.files.getDividerHeight() * (filesAdapter.getCount() - 1));
        binding.files.setLayoutParams(layoutParams);
    }
}