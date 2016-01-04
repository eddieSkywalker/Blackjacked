package electrifyingstudios.blackjacked.com;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by MacbookPro on 1/2/16.
 */
public class MyAlertDialogFragment extends DialogFragment {

    public class MyDialogFragment extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
            getDialog().setTitle("Simple Dialog");

            Button dismiss = (Button) rootView.findViewById(R.id.dismiss);
            dismiss.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            return rootView;
        }
    }

}
