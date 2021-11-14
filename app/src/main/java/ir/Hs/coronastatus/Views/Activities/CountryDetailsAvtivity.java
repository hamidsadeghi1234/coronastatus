package ir.Hs.coronastatus.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;

import ir.Hs.coronastatus.Views.models.CountryTable;
import ir.Hs.coronastatus.R;

public class CountryDetailsAvtivity extends AppCompatActivity {
    String country_name;
    CountryTable selected_country;
    Context context;
   TextView guide_tv;
    float death_percent,recovered_percent,critical_percent_of_actives,not_critical_percent_of_actives;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details_avtivity);
        guide_tv=findViewById(R.id.guide_tv);
        Intent intent = getIntent();
        selected_country = (CountryTable) intent.getSerializableExtra("selected_country");
        guide_tv.setText("اطلاعات کشور " + selected_country.getCountry()+"\n"+
        "تعداد کل تست های انجام شده این کشور = " + selected_country.getTotalTests()+ " نفر" +"\n"
        + "تعداد کل مبتلایان این کشور= " + selected_country.getCases()+" نفر");
        PieChart_mpandroid();
//        donate_chart.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v){  }});
//        pie_chart.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v){PieChart_AChartEngine();}});

    }
    void PieChart_mpandroid() {
    PieChart pieChart =findViewById(R.id.country_details_chart);
// creating data values
    ArrayList entries = new ArrayList<>();
    ArrayList labels = new ArrayList();
    labels.add("مرگ");
    labels.add("بهبود");
    labels.add("بیمار فعال");
    entries.add(new Entry(selected_country.getDeaths(), 0));
    entries.add(new Entry(selected_country.getRecovered(), 1));
    entries.add(new Entry(selected_country.getActive(), 2));
    PieDataSet dataset = new PieDataSet(entries, "مبتلایان");
    PieData data = new PieData(labels, dataset); // initialize Piedata
    dataset.setColors(ColorTemplate.COLORFUL_COLORS);
    pieChart.setCenterText(
    " مرگ  = "        +String.format("%.2f",selected_country.getDeaths_percent())                 +" % "+"\n"+
    "بهبودی= "        +String.format("%.2f",selected_country.getRecovered_percent())              +" % "+"\n"+
    " بیمار بحرانی= " +String.format("%.2f",selected_country.getCritical_percent_of_actives())    +" % "+"\n"+
    "   بیمار عادی= " +String.format("%.2f",selected_country.getNot_critical_percent_of_actives())+" % "+"\n");
    pieChart.setCenterTextSize(18);
    pieChart.setCenterTextColor(Color.WHITE);
    pieChart.setHoleColor(Color.TRANSPARENT);
    data.setValueTextSize(17);
    data.setValueTextColor(Color.WHITE);
    pieChart.setDescription("اطلاعات کشور " + selected_country.getCountry());
    pieChart.setDescriptionTextSize(200);
    pieChart.setDescriptionColor(Color.RED);
       //     .setTextSize(16f); //sets the size of the label text in density pixels min = 6f, max = 24f, default is 10f, font size will be in dp
//  pieChart.getDescription().setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark)); //the color of the font
//  pieChart.getDescription().setTextAlign(Paint.Align.RIGHT);
    pieChart.setData(data); //set data into chart
    pieChart.animateY(1000);
    }
//    void PieChart_AChartEngine() {
//    String[] lable = new String[]{"مرگ", "بهبود", "بیمار فعال"};// Pie Chart Section Value
//    int[] count = {selected_country.getDeaths(), selected_country.getRecovered(),selected_country.getActive()};// Color of each Pie Chart Sections
//    int[] colors = { Color.RED, Color.GREEN,Color.YELLOW};// Instantiating CategorySeries to plot Pie Chart
//    CategorySeries categorySeries = new CategorySeries("سری موضوعی");// Adding a slice with its values and name to the Pie Chart
//        for(int i = 0; i<count.length;i++) { categorySeries.add(lable[i], count[i]); }// Instantiating a renderer for the Pie Chart
//    DefaultRenderer defaultRenderer = new DefaultRenderer();
//        for(int i = 0; i<count.length;i++) {
//        SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
//        seriesRenderer.setColor(colors[i]);
//        seriesRenderer.setDisplayBoundingPoints(true);
//        // Adding colors to the chart
//        defaultRenderer.setBackgroundColor(Color.BLACK);
//        defaultRenderer.setApplyBackgroundColor(true);
//        // Adding a renderer for a slice
//        defaultRenderer.addSeriesRenderer(seriesRenderer);}
//        defaultRenderer.setChartTitle("اطلاعات کشور " + selected_country.getCountry());
//        defaultRenderer.setChartTitleTextSize(50);
//        defaultRenderer.setBackgroundColor(Color.GRAY);
//        defaultRenderer.setLabelsTextSize(40);
//        defaultRenderer.setLegendTextSize(30);
//        defaultRenderer.setZoomButtonsVisible(true);
//        LinearLayout chartContainer =findViewById(R.id.chart_frame);
//        GraphicalView mChart = ChartFactory.getPieChartView(context, categorySeries, defaultRenderer);
//        chartContainer.addView(mChart);
//    }
}


