package team.dmqqd.chengjitong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ChsTermActivity extends AppCompatActivity {

    private TermAdapter adapter;
    private int openCode;
    private List<String> termList = new ArrayList<>();//需要获取学期数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chs_term);
        Intent intent = getIntent();
        openCode = intent.getIntExtra("term",-1);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.term_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TermAdapter(termList,openCode);
        recyclerView.setAdapter(adapter);
    }
}
