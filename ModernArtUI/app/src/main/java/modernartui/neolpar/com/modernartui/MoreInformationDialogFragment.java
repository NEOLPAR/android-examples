package modernartui.neolpar.com.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by NEOLPAR on 28/10/14.
 */
public class MoreInformationDialogFragment extends DialogFragment {
    public static String TAG_NAME = "MoreInformationDialogFragment";

    @Override    
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_more_information, null))
                // Add action buttons
                .setPositiveButton(R.string.action_more_information_content_visit_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        startImplicitActivation();
                    }
                })
                .setNegativeButton(R.string.action_more_information_content_cancel_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MoreInformationDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void startImplicitActivation() {
        //  - Create a base intent for viewing a URL
        String url = "http://www.moma.org";
        Uri uri = Uri.parse(url);
        Intent baseIntent = new Intent(Intent.ACTION_VIEW, uri);

        //  - Create a chooser intent, for choosing which Activity
        // will carry out the baseIntent
        Intent chooserIntent = Intent.createChooser(baseIntent, url);

        //  - Start the chooser Activity, using the chooser intent
        // Verify the intent will resolve to at least one activity
        if (baseIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(chooserIntent);
        }
    }
}
