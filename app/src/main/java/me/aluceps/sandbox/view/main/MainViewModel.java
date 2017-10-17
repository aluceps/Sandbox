package me.aluceps.sandbox.view.main;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import me.aluceps.sandbox.databinding.CellEventBinding;
import me.aluceps.sandbox.model.ConnpassEvent;

public class MainViewModel extends RecyclerView.ViewHolder {

    public CellEventBinding binding;

    public String title;

    public String catchcopy;

    public boolean showCatchCopy;

    public Spanned description;

    public MainViewModel(View itemView) {
        super(itemView);
        binding = CellEventBinding.bind(itemView);
    }

    public void set(ConnpassEvent.Event model) {
        title = model.getTitle();
        catchcopy = model.getCatchcopy();
        showCatchCopy = !model.getCatchcopy().isEmpty();
        description = Html.fromHtml(model.getDescription(), Html.FROM_HTML_MODE_LEGACY);
        binding.setEvent(this);
    }
}
