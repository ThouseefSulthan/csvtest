package in.presencesoft.csvtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.presencesoft.csvtest.R;
import in.presencesoft.csvtest.pojos.FactListPojo;


public class FactListAdapter extends RecyclerView.Adapter<FactListHolder> {

    private Context context;
    private ArrayList<FactListPojo> pojos;

//    private HashSet unfoldedIndexes = new HashSet<>();


    public FactListAdapter(Context context, ArrayList<FactListPojo> pojo) {
        this.context = context;
        this.pojos = pojo;
    }

    @NonNull
    @Override
    public FactListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fact, parent, false);
        return new FactListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FactListHolder holder, final int position) {
        final FactListPojo list = pojos.get(position);

        holder.lblTitle.setText(list.getTitle());
        holder.lblDescription.setText(list.getDescription());

        String url = list.getImagehref();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return pojos.size();
    }
}
