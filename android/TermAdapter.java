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

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private static final String TAG = "TermAdapter";

    private Context mContext;

    private List<String> mTermList;
    private int mOpenCode;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView termView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            termView = view.findViewById(R.id.term_text);
        }
    }

    public TermAdapter(List<String> termList,int openCode) {
        mTermList = termList;
        mOpenCode = openCode;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.term_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String term = mTermList.get(position);

                if(mOpenCode == MainActivity.OPEN_MINE){
                    Intent intent = new Intent(mContext,PersonActivity.class);
                    intent.putExtra("term",term);
                    mContext.startActivity(intent);
                }
                if(mOpenCode == MainActivity.OPEN_CLASS){
                    Intent intent = new Intent(mContext,ClassActivity.class);
                    intent.putExtra("term",term);
                    mContext.startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String term = mTermList.get(position);
        holder.termView.setText(term);
    }

    @Override
    public int getItemCount() {
        return mTermList.size();
    }

}