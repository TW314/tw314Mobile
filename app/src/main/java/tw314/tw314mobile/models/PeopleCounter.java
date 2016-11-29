package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

public class PeopleCounter {

    @SerializedName("pessoas_na_frente")
    private int pessoasNaFrente;

    public static PeopleCounter peopleCounter;

    public static void setPeopleCounter(PeopleCounter peopleCounter){
        PeopleCounter.peopleCounter = peopleCounter;
    }

    public static PeopleCounter getPeopleCounter(){
        return peopleCounter;
    }

    public int getPessoasNaFrente() {
        return pessoasNaFrente;
    }

    public void setPessoasNaFrente(int pessoasNaFrente) {
        this.pessoasNaFrente = pessoasNaFrente;
    }

}