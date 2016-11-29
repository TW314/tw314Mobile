package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

public class PeopleCounterReceiver {

    @SerializedName("pessoas_na_frente")
    private int pessoasNaFrente;

    public static PeopleCounterReceiver peopleCounterReceiver;

    public static void setPeopleCounterReceiver(PeopleCounterReceiver peopleCounterReceiver){
        PeopleCounterReceiver.peopleCounterReceiver = peopleCounterReceiver;
    }

    public static PeopleCounterReceiver getPeopleCounterReceiver(){
        return peopleCounterReceiver;
    }

    public int getPessoasNaFrente() {
        return pessoasNaFrente;
    }

    public void setPessoasNaFrente(int pessoasNaFrente) {
        this.pessoasNaFrente = pessoasNaFrente;
    }

}