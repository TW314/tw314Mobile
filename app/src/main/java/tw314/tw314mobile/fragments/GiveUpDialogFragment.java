package tw314.tw314mobile.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import tw314.tw314mobile.interfaces.AlertDialogInterface;

/**
 * Created by pedro on 13/11/16.
 */

public class GiveUpDialogFragment extends DialogFragment {
    // Interface dos metodos de clique positivo e negativo
    AlertDialogInterface alertDialogInterface;

    // Metodo que insere o Dialog na Activity
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            alertDialogInterface = (AlertDialogInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " deve implementar GiveUpDialogFragment");
        }
    }

    // Metodo que cria o Dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Usa builder para construir AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Deseja realmente desistir?")
               .setMessage("Ao desistir, não será possível usar este ticket para atendimento.")
                // Seta botao de confirmacao/acao positiva
               .setPositiveButton("Desistir", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialogInterface, int id){
                       // TODO: Fazer atualizacao da informacao statusTicket para 4
                       // Seta instancia do Ticket como vazia
                       alertDialogInterface.onDialogPositiveClick(GiveUpDialogFragment.this);
                   }
               })
                // Seta botao de cancelamento/acao negativa
               .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialogInterface, int id){
                   }
               });
        // Cria e retorna instancia de criacao do AlertDialog
        return builder.create();
    }
}
