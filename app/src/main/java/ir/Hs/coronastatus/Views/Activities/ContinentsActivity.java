package ir.Hs.coronastatus.Views.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import java.util.ArrayList;
import ir.Hs.coronastatus.CoronaDB;
import ir.Hs.coronastatus.Views.models.CountryTable;
import ir.Hs.coronastatus.R;

public class ContinentsActivity extends AppCompatActivity {
    Context context;
   // List<CountryTable>  Continents_list;
    String[] continents;
    CoronaDB coronaDB;
    TextView word_count_tv,world_percent_tv;
    CountryTable world,North_America,South_America,Europe,Oceania,Asia,Africa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continents);
        word_count_tv=findViewById(R.id.world_count_tv);
        world_percent_tv=findViewById(R.id.word_percents_tv);


        coronaDB = Room.databaseBuilder(context, CoronaDB.class, "CoronaDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
       // continents = new String[]{"North America", "South America" ,"Europe", "Oceania", "Asia", "Africa"};
       // Continents_list = coronaDB.coronaDao().getContinents(continents);
        world=coronaDB.coronaDao().getWorld();
        North_America=coronaDB.coronaDao().get_by_name("North America");
        South_America=coronaDB.coronaDao().get_by_name("South America");
        Europe=coronaDB.coronaDao().get_by_name("Europe");
        Oceania=coronaDB.coronaDao().get_by_name("Oceania");
        Asia=coronaDB.coronaDao().get_by_name("Asia");
        Africa=coronaDB.coronaDao().get_by_name("Africa");
        //make a array list of continents names (every time take name and add)
        if (world != null) {
            word_count_tv.setText("آمار تعدادی جهان" + "\n" +
                    "\n"+
                    " مبتلا = "      +  world.getCases()    +" نفر "+ "\n" +
                    " مرگ = "   +  world.getDeaths()   +" نفر "+ "\n" +
                    " بهبودی = " +  world.getRecovered()+" نفر "+ "\n" +
                    " بیمار فعال = " +  world.getDeaths()   +" نفر "+ "\n");
        world_percent_tv.setText("آمار درصدی جهان" + "\n" +
                "\n"+"\n"+
                "  مرگ = "          +  String.format("%.2f",world.getDeaths_percent())    +" درصد "+ "\n" +
                "  بهبودی = "       +  String.format("%.2f",world.getRecovered_percent()) +" درصد "+ "\n" +
                " بیمار فعال = "    +  String.format("%.2f",world.getActive_percent())    +" درصد "+ "\n");}
        PieChart_mpandroid();
    }
    void PieChart_mpandroid() {
        PieChart pieChart =findViewById(R.id.chart);
// creating data values
        ArrayList entries = new ArrayList<>();
        ArrayList labels = new ArrayList();
        labels.add("آمریکای شمالی");
        labels.add("اقیانوسیه");
        labels.add("آسیا");
        labels.add("آفریقا");
        labels.add("اروپا");
        labels.add("آمریکای جنوبی");
// ترتیب وارد کردن لیبل ها مهم است و از صفر شروع می شود--هنگام ورود انتری ها باید متناظر اطلاعات وارد کرد
        entries.add(new Entry(North_America.getCases(), 0));
        entries.add(new Entry(Oceania.getCases(), 1));
        entries.add(new Entry(Asia.getCases(), 2));
        entries.add(new Entry(Africa.getCases(), 3));
        entries.add(new Entry(Europe.getCases(), 4));
        entries.add(new Entry(South_America.getCases(), 5));
        PieDataSet dataset = new PieDataSet(entries, "قاره ها");
        PieData data = new PieData(labels, dataset); // initialize Piedata
        int[] colors={Color.BLUE,Color.BLACK,Color.GREEN,Color.GRAY,Color.RED,Color.MAGENTA};
       // dataset.setColors();
        pieChart.setCenterText(
        " اروپا  = "      +String.format("%.2f",((float)Europe.getCases()/(float)world.getCases())*100)+" % "+"\n"+
        " آمریکا شمالی= " +String.format("%.2f",((float)North_America.getCases()/(float)world.getCases())*100) +" % "+"\n"+
        "اسیا= "          +String.format("%.2f",((float)Asia.getCases()/(float)world.getCases())*100)+" % "+"\n"+
        "  آمریکا جنوبی= "+String.format("%.2f",((float)South_America.getCases()/(float)world.getCases())*100)+" % "+"\n"+
        "  اقیانوسیه   = "+String.format("%.2f",((float)Oceania.getCases()/(float)world.getCases())*100)+" % "+"\n"+
        " آفریقا  = "     +String.format("%.2f",((float)Africa.getCases()/(float)world.getCases())*100)+" % "+"\n");
        pieChart.setCenterTextSize(15);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setHoleColor(Color.TRANSPARENT);
        data.setValueTextSize(15);
        data.setValueTextColor(Color.WHITE);
        pieChart.setDescription("اطلاعات قاره ها و جهان " );
        pieChart.setDescriptionTextSize(200);
        pieChart.setDescriptionColor(Color.RED);
//     .setTextSize(16f); //sets the size of the label text in density pixels min = 6f, max = 24f, default is 10f, font size will be in dp
//  pieChart.getDescription().setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark)); //the color of the font
//  pieChart.getDescription().setTextAlign(Paint.Align.RIGHT);
        pieChart.setData(data); //set data into chart
        pieChart.animateY(3000);
    }

}
