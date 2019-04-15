package team.dmqqd.chengjitong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private CardView cardView_mine;
    private CardView cardView_class;
    private String isMonitor;
    public static final int OPEN_MINE = 0;
    public static final int OPEN_CLASS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent = getIntent();
        isMonitor = intent.getStringExtra("isMonitor");
    }

    private void initView(){
        cardView_mine = (CardView)findViewById(R.id.cardview_mine);
        cardView_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTerm(OPEN_MINE);
            }
        });
        cardView_class = (CardView)findViewById(R.id.cardview_class);
        cardView_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTerm(OPEN_CLASS);
            }
        });
        if(isMonitor.equals("0")){
            cardView_class.setVisibility(View.GONE);
        }
    }

    private void chooseTerm(int openCode){
        Intent intent = new Intent(this,ChsTermActivity.class);
        intent.putExtra("term",openCode);
        startActivity(intent);

        //获得表的数据
        String term = "大一上";
    }
}
