package team.dmqqd.chengjitong;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.RadarEntry;
import android.graphics.Color;


public class PersonDetailActivity extends AppCompatActivity {
    private List<String> xAxisValue = new ArrayList<>();//X轴数据源
    private RadarChart radarChart;//雷达图



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        TextView textView_subject = (TextView) findViewById(R.id.person_detail_subject);
        TextView textView_score = (TextView) findViewById(R.id.person_detail_score_number);
        TextView textView_rank = (TextView) findViewById(R.id.person_detail_rank_num);
        TextView textView_rank_percent = (TextView) findViewById(R.id.person_detail_percent_number);

        //获取person信息
        Person person = new Person();

        Intent intent = getIntent();

        String subject = intent.getStringExtra("subject");//学科
        int score = intent.getIntExtra("score",-1);//单科成绩
        int rank = intent.getIntExtra("rank",-1);//排名
        float rank_percent = intent.getFloatExtra("rank_percent",-1);
        DecimalFormat df = new DecimalFormat("00.00%");//排名百分比

        textView_subject.setText(subject);
        textView_score.setText("" + score);
        textView_rank.setText("" + rank);
        textView_rank_percent.setText("" + df.format(rank_percent));


        //改参数
        initRadar();
        initLine();
    }

    private void initRadar(){
        radarChart = findViewById(R.id.radarChart);
        radarChart.getDescription().setEnabled(false);
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));

        List<RadarEntry> radarEntries = new ArrayList<>();
        /*改数据
        radarEntries.add(new RadarEntry(80));
        radarEntries.add(new RadarEntry(85));
        radarEntries.add(new RadarEntry(90));
        radarEntries.add(new RadarEntry(70));
        radarEntries.add(new RadarEntry(95));*/

        RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "数据一");
        // 实心填充区域颜色
        radarDataSet.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        // 是否实心填充区域
        radarDataSet.setDrawFilled(true);
        RadarData radarData = new RadarData(radarDataSet);
        radarChart.setData(radarData);
    }

    private void initLine(){

    }
}
