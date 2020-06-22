package in.presencesoft.csvtest.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.presencesoft.csvtest.R;


public class FactListHolder extends RecyclerView.ViewHolder {

    TextView lblTitle,lblDescription;

    ImageView img;
    public FactListHolder(@NonNull View itemView) {
        super(itemView);
        lblTitle=itemView.findViewById(R.id.lblTitle);
        lblDescription=itemView.findViewById(R.id.lblDescription);
        img=itemView.findViewById(R.id.img);
    }
}
