package ir.Hs.coronastatus.Views.models;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country")
    private String country;
    @SerializedName("cases")
    private int cases;
    @SerializedName("todayCases")
    private int todayCases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("todayDeaths")
    private int todayDeaths;
    @SerializedName("recovered")
    private int recovered;
    @SerializedName("active")
    private int active;
    @SerializedName("critical")
    private int critical;
    @SerializedName("totalTests")
    private int totalTests;

    private float deaths_percent;
    private float recovered_percent;
    private float active_percent;
    private float critical_percent_of_actives;
    private float not_critical_percent_of_actives;
    private int rank;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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
