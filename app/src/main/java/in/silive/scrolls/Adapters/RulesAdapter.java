package in.silive.scrolls.Adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import in.silive.scrolls.R;

/**
 * Created by AKG002
 * thats all.
 */
public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.ViewHolder>{
    String[] topics;
    Context context;
    private int lastPosition;

    public RulesAdapter(Context context, String[] topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public RulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_num_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final RulesAdapter.ViewHolder holder, final int position) {
        holder.tvTopic.setText(topics[position]);
        holder.itemView.setVisibility(View.INVISIBLE);
        holder.tvNum.setText(""+(position+1));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                holder.itemView.setVisibility(View.VISIBLE);
                setAnimation(holder.itemView,position);
            }
        }, (int)(10+50*position));

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return topics.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTopic;
        TextView tvNum;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTopic = (TextView)itemView.findViewById(R.id.tv);
            tvNum = (TextView)itemView.findViewById(R.id.tvNum);
        }
    }
}
