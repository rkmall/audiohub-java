package com.rupesh.audiohubapp.view.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.rupesh.audiohubapp.activities.MainProjectActivity;
import com.rupesh.audiohubapp.R;
import com.rupesh.audiohubapp.model.Project;

public class ProjectListAdapter extends FirebaseRecyclerAdapter<Project, ProjectListAdapter.ProjectListViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProjectListAdapter(@NonNull FirebaseRecyclerOptions<Project> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProjectListViewHolder holder, int position, @NonNull final Project model) {
        holder.projectName.setText(model.getProjectName());
        holder.projectDate.setText(model.getCreatedOn());

        // Get the project Id by getting the position of the ViewHolder
        //final String holderProjectIdPosition = getRef(position).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Navigate to MainProjectActivity and pass Project model as object (whole)
                Intent mainProjectIntent = new Intent(v.getContext(), MainProjectActivity.class);
                mainProjectIntent.putExtra("project", model);
                v.getContext().startActivity(mainProjectIntent);

                //Toast.makeText(v.getContext(), "Project created successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    // This method is responsible for inflating the recycleView
    @NonNull
    @Override
    public ProjectListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_project_row_item, parent, false);

        return new ProjectListViewHolder(view);
    }

    class ProjectListViewHolder extends RecyclerView.ViewHolder {
        // Declare TextView for the recycler card
        TextView projectName;
        TextView projectDate;

        public ProjectListViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.textViewProjectName);
            projectDate = itemView.findViewById(R.id.textViewProjectDate);
        }
    }
}
