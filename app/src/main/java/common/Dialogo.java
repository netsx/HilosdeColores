package common;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class Dialogo extends DialogFragment {

    public Dialogo() {
        // Empty constructor required for DialogFragment
    }

    public static Dialogo newInstance(String title, String mensaje) {
        Dialogo frag = new Dialogo();
        Bundle args = new Bundle();

        args.putString("title", title);
        args.putString("mensaje", mensaje);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String mensaje = getArguments().getString("mensaje");

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Reiniciar",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
                     getActivity().finish();
                     startActivity(getActivity().getIntent());

            }
        });

        return alertDialogBuilder.create();
    }
}