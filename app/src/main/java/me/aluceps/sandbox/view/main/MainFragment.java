package me.aluceps.sandbox.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.disposables.CompositeDisposable;
import me.aluceps.sandbox.R;
import me.aluceps.sandbox.databinding.FragmentMainBinding;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.view.BaseFragment;

public class MainFragment extends BaseFragment implements MainContract.View {

    private FragmentMainBinding binding;

    private CompositeDisposable disposable = new CompositeDisposable();

    private MainPresenter presenter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.load();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void initializePresenter() {
        presenter = new MainPresenter();
        presenter.setView(this);
    }

    @Override
    public void setEvent(ConnpassEvent.Event event) {
        binding.title.setText(event.getTitle());
        binding.catchcopy.setText(event.getCatchcopy());
        binding.description.setText(Html.fromHtml(event.getDescription(), Html.FROM_HTML_MODE_COMPACT));
    }

    @Override
    public void clear() {
        binding.title.setText("");
        binding.catchcopy.setText("");
        binding.description.setText("");
    }
}
