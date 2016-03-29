package electrifyingstudios.blackjacked.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by MacbookPro on 1/2/16.
 */
public class MyAlertDialogFragment extends DialogFragment {

    public static MyAlertDialogFragment newInstance(int title)
    {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

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

//public class MyAlertDialogFragment extends DialogFragment {
//
//    public static MyAlertDialogFragment newInstance(int title) {
//        MyAlertDialogFragment frag = new MyAlertDialogFragment();
//        Bundle args = new Bundle();
//        args.putInt("title", title);
//        frag.setArguments(args);
//        return frag;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        int title = getArguments().getInt("title");
//
//        return new AlertDialog.Builder(getActivity())
////                .setIcon(R.drawable.red_background_image)
//                .setTitle(R.string.app_name)
//                .create();
//
//
//    }
//}
