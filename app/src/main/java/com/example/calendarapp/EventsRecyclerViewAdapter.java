package com.example.calendarapp;

        import android.app.Activity;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;


        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {
    ArrayList<event_model> Events;
    Context ctx;
    Activity activity;

    public EventsRecyclerViewAdapter(ArrayList<event_model> events, Context ctx) {
        this.Events = events;
        this.ctx = ctx;
        this.activity=(Activity) ctx;
    }

    public void setPosts(ArrayList<event_model> events) {
        this.Events = events;
        notifyDataSetChanged();
    }

    //DOUBT
    @NonNull
    @Override
    public EventsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsRecyclerViewAdapter.ViewHolder holder, int position) {
        event_model Event=Events.get(position);
        //DOUBT
        holder.title.setText(Event.getTitle());

        holder.date.setText("Date: " + Event.getDate());
        holder.time.setText("Time: " + Event.getTime());
        holder.description.setText("Description: " + Event.getDescription());
        holder.duration.setText("Duration: " + Event.getDuration());

        if(Event.getDuration().equals("null")){
            holder.duration.setVisibility(View.GONE);
            holder.date.setText("Submission Date: " + Event.getDate());
            holder.time.setText("Submission Time: " + Event.getTime());
        }else{
            holder.duration.setVisibility(View.VISIBLE);
        }

        holder.dustbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DEBUG
                DataBaseHelper dataBaseHelper = new DataBaseHelper(ctx);
                dataBaseHelper.deleteEvent(Event);
                Events.remove(Event);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DOUBT
                Intent intent=new Intent(activity,Edit_Event.class);
                intent.putExtra("type",Event.getType());
                intent.putExtra("Id",Event.getID());
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(Event.getID() + " Yahan ID print kiya hai1 ! ");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,date,time,description,duration;
        private RelativeLayout eventListItemParent;
        ImageView edit,dustbin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventListItemParent=itemView.findViewById(R.id.events_listParent);
            title=itemView.findViewById(R.id.Title);
            date=itemView.findViewById(R.id.Date);
            time=itemView.findViewById(R.id.Time);
            description=itemView.findViewById(R.id.Description);
            duration=itemView.findViewById(R.id.Duration);
            edit=itemView.findViewById(R.id.Edit);
            dustbin=itemView.findViewById(R.id.Dustbin);
        }
    }
}
