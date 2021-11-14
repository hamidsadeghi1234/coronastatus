package ir.Hs.coronastatus.interfaces;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ir.Hs.coronastatus.Views.models.CountryTable;
//  where country="North America","Europe","Asia","South America","Oceania","Africa","World"
//country,cases,todayCases,deaths,todayDeaths,recovered,active,critical,casesPerOneMillion,deathsPerOneMillion,totalTests,testsPerOneMillion
@Dao
    public interface CoronaDao {

    @Query("Select * from CountryTable where country in (:continents) ")
    List<CountryTable> getContinents(String[] continents);


    @Query("Select * from CountryTable where country='World' ")
    CountryTable getWorld();

    @Query("Select * from CountryTable where country=:name ")
    CountryTable get_by_name(String name);

        @Query("Select * from CountryTable")
        List<CountryTable> getAll();
//
        @Query("select * from CountryTable where country not in (:UnWanted) ")
        List<CountryTable> filter_Unwanted(String[] UnWanted);

    @Query("insert into CountryTable (country,cases,deaths,recovered,todayCases,todayDeaths," +
            "active,critical,totalTests,deaths_percent,recovered_percent,active_percent," +
            "critical_percent_of_actives,not_critical_percent_of_actives,rank)" +
            "values(:country,:cases,:deaths,:recovered,:todayCases,:todayDeaths,:active,:critical," +
            ":totalTests,:deaths_percent,:recovered_percent,:active_percent," +
            ":critical_percent_of_actives,:not_critical_percent_of_actives,:rank)")
    void insertAll(String country,int cases,int deaths,int recovered,int todayCases,int todayDeaths,int active,
                   int critical,int totalTests, float deaths_percent, float recovered_percent,float active_percent,
                   float critical_percent_of_actives,float not_critical_percent_of_actives,int rank);

    @Query("SELECT * FROM CountryTable where country not in (:UnWanted)ORDER BY deaths DESC")
    List<CountryTable> sortByDeaths(String[] UnWanted);

    @Query("SELECT * FROM CountryTable where country not in (:UnWanted)ORDER BY recovered DESC")
    List<CountryTable> sortByRecovered(String[] UnWanted);

    @Query("SELECT * FROM CountryTable where country not in (:UnWanted)ORDER BY cases DESC")
    List<CountryTable> sortByCases(String[] UnWanted);

    @Query("SELECT * FROM CountryTable where country not in (:UnWanted)ORDER BY todayDeaths DESC")
    List<CountryTable> sortByTodayDeaths(String[] UnWanted);

    @Query("SELECT * FROM CountryTable where country not in (:UnWanted)ORDER BY todayCases DESC")
    List<CountryTable> sortByTodayCases(String[] UnWanted);


    @Query("SELECT max(cases) FROM CountryTable where country not in (:UnWanted)")
    int most_cases(String[] UnWanted);
    @Query("SELECT country FROM CountryTable where cases=:max")
    String most_cases_country(int max);

    @Query("SELECT max(deaths) FROM CountryTable where country not in (:UnWanted)")
    int most_deaths(String[] UnWanted);
    @Query("SELECT country FROM CountryTable where deaths=:max")
    String most_deaths_country(int max);

    @Query("SELECT max(recovered) FROM CountryTable where country not in (:UnWanted)")
    int most_recovered(String[] UnWanted);
    @Query("SELECT country FROM CountryTable where recovered=:max")
    String most_recovered_country(int max);

    @Query("SELECT max(recovered_percent) FROM CountryTable where country not in (:UnWanted) and cases>10000")
    float most_recovered_percent(String[] UnWanted);
    @Query("SELECT country FROM CountryTable where recovered_percent=:max")
    String most_recovered_percent_country(float max);

    @Query("SELECT max(deaths_percent) FROM CountryTable where country not in (:UnWanted) and cases>10000")
    float most_deaths_percent(String[] UnWanted);
    @Query("SELECT country FROM CountryTable where deaths_percent=:max")
    String most_deaths_percent_country(float max);

    @Query("SELECT max(totalTests) FROM CountryTable where country not in (:UnWanted) and totalTests>1000000")
    int most_test(String[] UnWanted);
    @Query("SELECT country FROM CountryTable where totalTests=:max")
    String most_test_country(int max);



//    @Query("SELECT * FROM CountryTable ORDER BY deaths ASC")
//    List<CountryTable> sortByDeaths();
//
//    @Query("insert into Country (country,cases,todayCases,deaths,todayDeaths,recovered,active,critical,casesPerOneMillion,deathsPerOneMillion,totalTests,testsPerOneMillion)" +
//                "values (:country,:cases,:todayCases,:deaths,:todayDeaths,:recovered,:active,:critical,:casesPerOneMillion,:deathsPerOneMillion,:totalTests,:testsPerOneMillion)")
//        void insertAll(String country,int cases,int todayCases,int deaths,int todayDeaths,int recovered,int active,int critical,int casesPerOneMillion,int deathsPerOneMillion,int totalTests,int testsPerOneMillion);
//

    @Query("DELETE FROM CountryTable")
     void deleteAll();

}

//        @Query("update MatchTable set match_name=:match_name  ,  groups_count=:groups_count ," +
//                " words_count_per_person=:words_count_per_person , all_words_length=:all_words_length" +
//                " , rest_words_length=:rest_words_length     where match_id=:match_id ")
//        void update_match(int match_id,String match_name,int groups_count,int words_count_per_person,
//                          int  all_words_length,int rest_words_length);

//        @Query("insert into WordTable (match_id,word_name,word_level)values (:matchName,:word_name,:word_level)")
//        void insert_word(int matchName,String word_name,int word_level);
//
//        @Query("Select word_name from WordTable where match_id=:match_id")
//        String[] get_words_by_match_id(int match_id);
//
//        @Query("update  WordTable set word_level=:word_level where match_id=:match_id and word_name=:word_name ")
//        void increase_word_level(int match_id,String word_name,int word_level);
//
//        @Query("DELETE FROM MatchTable WHERE match_id = :match_id") abstract
//        void delete_match(int match_id);