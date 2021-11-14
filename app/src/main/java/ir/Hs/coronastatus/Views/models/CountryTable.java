package ir.Hs.coronastatus.Views.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//country,cases,todayCases,deaths,todayDeaths,recovered,active,critical,casesPerOneMillion,deathsPerOneMillion,totalTests,testsPerOneMillion
    @Entity
    public class CountryTable implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int country_id;
        @ColumnInfo(name = "country")@Nullable
        private String country;
        @ColumnInfo(name = "cases" )@Nullable
        private int cases ;
        @ColumnInfo(name = "todayCases")@Nullable
        private int todayCases;
        @ColumnInfo(name = "deaths")@Nullable
        private int deaths;
        @ColumnInfo(name = "todayDeaths")@Nullable
        private int todayDeaths;
        @ColumnInfo(name = "recovered")@Nullable
        private int recovered;
        @ColumnInfo(name = "active")@Nullable
        private int active;
        @ColumnInfo(name = "critical")@Nullable
        private int critical;
        @ColumnInfo(name = "totalTests")@Nullable
        private int totalTests;
        @ColumnInfo(name = "deaths_percent")@Nullable
        private float deaths_percent;
        @ColumnInfo(name = "recovered_percent")@Nullable
        private float recovered_percent;
        @ColumnInfo(name = "active_percent")@Nullable
        private float active_percent;
        @ColumnInfo(name = "critical_percent_of_actives")@Nullable
        private float critical_percent_of_actives;
        @ColumnInfo(name = "not_critical_percent_of_actives")@Nullable
        private float not_critical_percent_of_actives;
        @ColumnInfo(name = "rank")@Nullable
        private int rank;

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    public void setCountry(@Nullable String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public float getDeaths_percent() {
        return deaths_percent;
    }

    public void setDeaths_percent(float deaths_percent) {
        this.deaths_percent = deaths_percent;
    }

    public float getRecovered_percent() {
        return recovered_percent;
    }

    public void setRecovered_percent(float recovered_percent) {
        this.recovered_percent = recovered_percent;
    }

    public float getActive_percent() {
        return active_percent;
    }

    public void setActive_percent(float active_percent) {
        this.active_percent = active_percent;
    }

    public float getCritical_percent_of_actives() {
        return critical_percent_of_actives;
    }

    public void setCritical_percent_of_actives(float critical_percent_of_actives) {
        this.critical_percent_of_actives = critical_percent_of_actives;
    }

    public float getNot_critical_percent_of_actives() {
        return not_critical_percent_of_actives;
    }

    public void setNot_critical_percent_of_actives(float not_critical_percent_of_actives) {
        this.not_critical_percent_of_actives = not_critical_percent_of_actives;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}