package team.dmqqd.chengjitong;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private static final String TAG = "PersonAdapter";

    private Context mContext;

    private List<Person> mPersonList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView subjectView;
        TextView scoreView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = view.findViewById(R.id.person_sketchy_icon);
            subjectView = view.findViewById(R.id.person_sketchy_subject);
            scoreView = view.findViewById(R.id.person_sketchy_score);
        }
    }

    public PersonAdapter(List<Person> personList) {
        this.mPersonList = personList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.person_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Person person = mPersonList.get(position);
                String subject = person.getTerm().getSubject().get(position);//学科
                Integer score_INT = person.getHmSubScore().get(subject);
                int score = score_INT.intValue();//单科成绩
                int rank = person.getRank();
                float rank_percent = person.getRank_percent();
                Intent intent = new Intent(mContext,PersonDetailActivity.class);
                intent.putExtra("subject",subject);
                intent.putExtra("score",score);
                intent.putExtra("rank",rank);
                intent.putExtra("rank_percent",rank_percent);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = mPersonList.get(position);
        String subject = person.getTerm().getSubject().get(position);
        Integer score_INT = person.getHmSubScore().get(subject);
        int score = score_INT.intValue();
        holder.subjectView.setText(subject);
        holder.scoreView.setText("" + score);
        Glide.with(mContext).load(R.drawable.ic_launcher_background).into(holder.imageView);//改图标
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

}