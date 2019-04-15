package team.dmqqd.chengjitong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {

    private ClassAdapter adapter;
    private List<Person> classList = new ArrayList<>();//获取Class数据
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        button = (Button)findViewById(R.id.class_total);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.class_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ClassAdapter(classList);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(ClassActivity.this,perTotalActivity.class);
            }
        });
    }
}
