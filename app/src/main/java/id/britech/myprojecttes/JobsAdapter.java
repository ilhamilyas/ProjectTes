package id.britech.myprojecttes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.britech.myprojecttes.service.ResponseJobs;
import id.britech.myprojecttes.service.itemModel;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    private List<ResponseJobs> getItemList;
    private Activity activity;

    public JobsAdapter(Activity activity) {
        this.getItemList = new ArrayList<>();
        this.activity = activity;
    }

    public void setGetItemList(List<ResponseJobs> getItemList) {
        this.getItemList = getItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_job, parent, false);
        return new JobsAdapter.ViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsAdapter.ViewHolder holder, int position) {
            holder.company.setText(getItemList.get(position).getCompany());
            holder.desc.setText(getItemList.get(position).getTitle());
        Picasso.get()
                .load(getItemList.get(position).getCompanyLogo())
                .into(holder.companyLogo);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailJobsActivity.class);
            intent.putExtra("id", getItemList.get(position).getId());
            activity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return getItemList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView company, desc,location;
        ImageView companyLogo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            company = itemView.findViewById(R.id.companyName);
            desc = itemView.findViewById(R.id.desc);
            location = itemView.findViewById(R.id.location);
            companyLogo = itemView.findViewById(R.id.companyLogo);
        }
    }
}
