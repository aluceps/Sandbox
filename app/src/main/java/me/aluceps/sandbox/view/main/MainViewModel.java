package me.aluceps.sandbox.view.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import me.aluceps.sandbox.BR;
import me.aluceps.sandbox.model.ConnpassEvent;

public class MainViewModel extends RecyclerView.ViewHolder {

    public ViewDataBinding binding;

    public String title;

    public String catchcopy;

    public boolean showCatchCopy;

    public Spanned description;

    public MainViewModel(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void set(MainAdapter.Type type, ConnpassEvent.Event model) {
        if (type == MainAdapter.Type.DATA) {
            title = model.getTitle();
            catchcopy = model.getCatchcopy();
            showCatchCopy = !model.getCatchcopy().isEmpty();
            description = Html.fromHtml(model.getDescription(), Html.FROM_HTML_MODE_LEGACY);
        }
        binding.setVariable(BR.event, this);
    }
}
