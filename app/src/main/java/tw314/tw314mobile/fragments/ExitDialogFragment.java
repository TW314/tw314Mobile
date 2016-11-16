package tw314.tw314mobile.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import tw314.tw314mobile.interfaces.AlertDialogInterface;

/**
 * Created by pedro on 16/11/16.
 */

public class ExitDialogFragment extends DialogFragment {

    AlertDialogInterface alertDialogInterface;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            alertDialogInterface = (AlertDialogInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " deve implementar GiveUpDialogFragment");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Usa builder para construir AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Deseja realmente sair?")
                .setMessage("Não se preocupe! Você será avisado do andamento do seu Ticket.")
                // Seta botao de confirmacao/acao positiva
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id){
                        // TODO: Fazer atualizacao da informacao statusTicket para 4
                        // Seta instancia do Ticket como vazia
                        alertDialogInterface.onDialogPositiveClick(ExitDialogFragment.this);
                    }
                })
                // Seta botao de cancelamento/acao negativa
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id){
                        alertDialogInterface.onDialogNegativeClick(ExitDialogFragment.this);
                    }
                });
        // Cria e retorna instancia de criacao do AlertDialog
        return builder.create();
    }

}
