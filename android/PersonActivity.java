package team.dmqqd.chengjitong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {
    private PersonAdapter adapter;
    private List<Person> personList = new ArrayList<>();//获取person数据
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        button = (Button)findViewById(R.id.person_total);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.person_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(PersonActivity.this,perTotalActivity.class);
            }
        });
    }
}
