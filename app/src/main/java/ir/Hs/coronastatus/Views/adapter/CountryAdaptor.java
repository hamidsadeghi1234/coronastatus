package ir.Hs.coronastatus.Views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ir.Hs.coronastatus.Views.models.CountryTable;
import ir.Hs.coronastatus.R;
import ir.Hs.coronastatus.Views.Activities.CountryDetailsAvtivity;

public class CountryAdaptor extends RecyclerView.Adapter <CountryAdaptor.CountriesViewHolder>{
        private Context context;
        private List<CountryTable> countryTables;
        public CountryAdaptor (Context context,List<CountryTable> countryTables){ this.context = context;this.countryTables = countryTables; }
        @NonNull
        @Override
        public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(R.layout.country_item,parent,false);
            return new CountriesViewHolder(view); }
        @Override
        public void onBindViewHolder(@NonNull CountriesViewHolder holder, final int position) {
            final CountryTable countryTable = countryTables.get(position);
            holder.country_tv    .setText(countryTable.getCountry());
            holder.cases_tv      .setText(String.valueOf(countryTable.getCases())      );
            holder.todayCases_tv .setText(String.valueOf(countryTable.getTodayCases()) );
            holder.deaths_tv     .setText(String.valueOf(countryTable.getDeaths())     );
            holder.todayDeaths_tv.setText(String.valueOf(countryTable.getTodayDeaths()));
         //   holder.recovered_tv  .setText(String.valueOf(countryTable.getRecovered())  );
            holder.row_tv  .setText(" "+(position+1) );
            holder.country_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v ) {
                    Intent intent=new Intent(context, CountryDetailsAvtivity.class);
                    intent.putExtra("selected_country", countryTable);
                    //    intent.putExtra("name", country.getName());
                    //  intent.putExtra("email", country.getEmail());
                    context.startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() { return countryTables.size(); }
        public   class CountriesViewHolder extends RecyclerView.ViewHolder{
            private TextView country_tv;
            private TextView cases_tv;
            private TextView todayCases_tv;
            private TextView deaths_tv;
            private TextView todayDeaths_tv;
         //   private TextView recovered_tv;
            private TextView  row_tv;
            public CountriesViewHolder(@NonNull View itemView) {
                super(itemView);
                country_tv    =itemView.findViewById(R.id.country_tv    );
                cases_tv      =itemView.findViewById(R.id.cases_tv      );
                todayCases_tv =itemView.findViewById(R.id.cases_today_tv );
                deaths_tv     =itemView.findViewById(R.id.deaths_tv     );
                todayDeaths_tv=itemView.findViewById(R.id.deaths_today_tv);
           //     recovered_tv  =itemView.findViewById(R.id.recovered_tv  );
                row_tv    =itemView.findViewById(R.id.row_tv    );}
        }
    }

