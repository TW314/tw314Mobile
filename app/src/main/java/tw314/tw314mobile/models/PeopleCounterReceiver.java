package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import retrofit2.Call;
import tw314.tw314mobile.connectionHandler.ConnectionHandler;
import tw314.tw314mobile.services.TicketService;

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