package ir.Hs.coronastatus.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ir.Hs.coronastatus.CoronaDB;
import ir.Hs.coronastatus.Views.models.CountryTable;
import ir.Hs.coronastatus.R;
import ir.Hs.coronastatus.Views.adapter.CountryAdaptor;
import ir.Hs.coronastatus.Views.models.Country;
import ir.Hs.coronastatus.interfaces.APIClient;
import ir.Hs.coronastatus.interfaces.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    Context context;
    CountryTable world;
    Button update_btn,Continents_btn;
    CoronaDB coronaDB;
    float death_percent;
    TextView cases_tv,deaths_tv,cases_today_tv,deaths_today_tv,last_update_tv, world_cases_tv,world_recovered_tv,
    world_deaths_tv,most_tv,max_test_tv,max_cases_tv,max_deaths_tv,max_recovered_percent_tv,max_deaths_percent_tv,max_recovered_tv;
    List<CountryTable> just_countries;
    String[] UnWanted={ "Total:","North America","Europe","Asia","South America","Oceania","Africa","World",""};
    Toolbar toolbar;
boolean update=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        تنظیمات_اولیه();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<List<Country>> call=apiInterface.getCountries();
                Toast toast= makeText(getApplicationContext(),
                        "منتظر بمانید \nدرحال بروز رسانی", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
                    call.enqueue(new Callback<List<Country>>() {
                        @Override
                        public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                            if (response.isSuccessful()) {
                                coronaDB.coronaDao().deleteAll();
                                List<Country> countries = response.body();
                                for (Country country : countries) {
                                 float death_percent=((float)country.getDeaths()/(float)country.getCases())*100;
                                 float recovered_percent=((float)country.getRecovered()/(float)country.getCases())*100;
                                 float active_percent=((float)country.getActive()/(float)country.getCases())*100;
                                 float critical_percent_of_actives=((float)country.getCritical()/(float)country.getCases())*100;
                                 float not_critical_percent_of_actives=((float)100-(death_percent+recovered_percent+critical_percent_of_actives));
                                 coronaDB.coronaDao().insertAll(country.getCountry(), country.getCases(),
                                 country.getDeaths(),country.getRecovered(),country.getTodayCases(),
                                 country.getTodayDeaths(),country.getActive(),country.getCritical(),
                                 country.getTotalTests(), death_percent,recovered_percent, active_percent,
                                 critical_percent_of_actives,not_critical_percent_of_actives,1); }
                                just_countries= coronaDB.coronaDao().filter_Unwanted(UnWanted);
                               // update_btn.setText(" اطلاعات " + just_countries.size() + " کشور بروز شد");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                                String currentDateandTime = sdf.format(new Date());
                                last_update_tv.setText(currentDateandTime);
                                update_statistics();
                                update=true;
                                Toast toast= makeText(context.getApplicationContext(),
                                        "بروز رسانی با موفقیت انجام شد", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP,0,0);
                                toast.show();

                            }}
                        @Override
                        public void onFailure(Call<List<Country>> call, Throwable t)
                        { Toast toast= makeText(getApplicationContext(),
                                "خطا در ارتباط \n " +
                                        "اتصال اینترنت را بررسی کنید" +
                                        "\n یا مجددا تلاش کنید", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP,0,0);
                            toast.show(); }});
            }});

        deaths_tv.      setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) { List<CountryTable> countryTables = coronaDB.coronaDao().sortByDeaths     (UnWanted);setUpRecyclerView(countryTables); }});
        cases_tv.       setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) { List<CountryTable> countryTables = coronaDB.coronaDao().sortByCases      (UnWanted);setUpRecyclerView(countryTables); }});
    //    recovered_tv.   setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) { List<CountryTable> countryTables = coronaDB.coronaDao().sortByRecovered  (UnWanted);setUpRecyclerView(countryTables); }});
        cases_today_tv. setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) { List<CountryTable> countryTables = coronaDB.coronaDao().sortByTodayCases (UnWanted);setUpRecyclerView(countryTables); }});
        deaths_today_tv.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) { List<CountryTable> countryTables = coronaDB.coronaDao().sortByTodayDeaths(UnWanted);setUpRecyclerView(countryTables); }});
     //   Continents_btn. setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) { Intent intent=new Intent(context,ContinentsActivity.class);startActivity(intent); }});
    }
    void تنظیمات_اولیه(){
    cases_tv           =findViewById(R.id.cases_tv          );
    deaths_tv          =findViewById(R.id.deaths_tv         );
  //  recovered_tv       =findViewById(R.id.recovered_tv      );
    cases_today_tv     =findViewById(R.id.cases_today_tv    );
    deaths_today_tv    =findViewById(R.id.deaths_today_tv   );
    update_btn         =findViewById(R.id.update_btn        );
//    Continents_btn     =findViewById(R.id.Continents_btn    );
    last_update_tv     =findViewById(R.id.last_update_tv    );
    world_cases_tv     =findViewById(R.id.world_cases_tv    );
    world_recovered_tv =findViewById(R.id.world_recovered_tv);
    world_deaths_tv    =findViewById(R.id.world_deaths_tv   );
    most_tv            =findViewById(R.id.most_tv           );
    max_test_tv        =findViewById(R.id.max_test_tv       );
    max_cases_tv       =findViewById(R.id.max_cases_tv      );
    max_deaths_tv      =findViewById(R.id.max_deaths_tv     );
    max_recovered_tv   =findViewById(R.id.max_recovered_tv  );
    max_recovered_percent_tv =findViewById(R.id.max_recovered_percent_tv);
    max_deaths_percent_tv    =findViewById(R.id.max_deaths_percent_tv   );
    coronaDB = Room.databaseBuilder(context, CoronaDB.class, "CoronaDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build();
        update_statistics();
    }
@SuppressLint({"SetTextI18n", "DefaultLocale"})
private void update_statistics(){
    just_countries= coronaDB.coronaDao().filter_Unwanted(UnWanted);
    if(just_countries.size()>10) {
        setUpRecyclerView(just_countries);
        world = coronaDB.coronaDao().getWorld();
        world_cases_tv.setText("کل مبتلایان جهان \n " + String.format("%,d", world.getCases()));
        world_deaths_tv.setText( "  مرگ  \n"         + String.format("%,d", world.getDeaths()));
        world_recovered_tv.setText( "  بهبودی  \n"         + String.format("%,d", world.getRecovered()));
        most_tv.setText("  رتبه های اول جهان در موارد مختلف  ");
        max_test_tv.setText     ("بیشترین تعداد تست "+"\n"+ coronaDB.coronaDao().most_test_country(coronaDB.coronaDao().most_test(UnWanted))  +"\n" +String.format("%,d", coronaDB.coronaDao().most_test(UnWanted)));
        max_cases_tv.setText     ("بیشترین ابتلا "+"\n"+ coronaDB.coronaDao().most_cases_country(coronaDB.coronaDao().most_cases(UnWanted))  +"\n" +String.format("%,d", coronaDB.coronaDao().most_cases(UnWanted)));
        max_recovered_tv.setText("بیشترین تعداد بهبود "+"\n"+ coronaDB.coronaDao().most_recovered_country(coronaDB.coronaDao().most_recovered(UnWanted))  +"\n" +String.format("%,d", coronaDB.coronaDao().most_recovered(UnWanted)));
        max_deaths_tv.setText("بیشترین تعداد مرگ "+"\n"+ coronaDB.coronaDao().most_deaths_country(coronaDB.coronaDao().most_deaths(UnWanted))  +"\n" +String.format("%,d", coronaDB.coronaDao().most_deaths(UnWanted)));
        max_deaths_percent_tv.setText("بیشترین درصد مرگ "+"\n"+ coronaDB.coronaDao().most_deaths_percent_country(coronaDB.coronaDao().most_deaths_percent(UnWanted))  +"\n" +String.format("%.2f", coronaDB.coronaDao().most_deaths_percent(UnWanted)));
        max_recovered_percent_tv.setText("بیشترین درصد بهبود "+"\n"+ coronaDB.coronaDao().most_recovered_percent_country(coronaDB.coronaDao().most_recovered_percent(UnWanted))  +"\n" +String.format("%.2f", coronaDB.coronaDao().most_recovered_percent(UnWanted)));
    }
}

    private void setUpRecyclerView(List<CountryTable> countryTables){
        RecyclerView recyclerView=findViewById(R.id.recycler_view1);
        CountryAdaptor countryAdaptor=new CountryAdaptor(MainActivity.this,countryTables);
        //recyclerView.setLayoutManager((new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)));
        // recyclerView.setLayoutManager((new LinearLayoutManager(RetrofitActivity.this,LinearLayoutManager.HORIZONTAL,false)));
        recyclerView.setLayoutManager((new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false)));
        recyclerView.setAdapter(countryAdaptor);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if      (id == R.id.guide_menu) {
            Toast toast= makeText(getApplicationContext(),
                    " لمس  نام کشور = مشاهده اطلاهات بیشتر آن  \n"
                    +   "لمس نام هر ستون = مرتب سازی لیست طبق آن"
                    , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();}
        else if (id == R.id.developer_menu)   {
            Toast toast= makeText(getApplicationContext(),
                    "حمید صادقی(حسن)\n" +
                            "آیدی تلگرام \n" +
                            "@Hamid_sadeghi", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show(); }
        return super.onOptionsItemSelected(item);
    }
}

