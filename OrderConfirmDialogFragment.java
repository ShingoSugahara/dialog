package com.example.listviewsample3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class OrderConfirmDialogFragment extends DialogFragment {
   private ArrayAdapter<String> adapter;
   private List<String> menuList ;
   private int position;

    OrderConfirmDialogFragment(ArrayAdapter<String>adapter,List<String>menuList,int position){
        this.adapter=adapter;
        this.menuList=menuList;
        this.position=position;

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_msg);
        builder.setPositiveButton(R.string.dialog_ok,new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dialog_no,new DialogButtonClickListener());
        AlertDialog dialog = builder.create();
        return dialog;

    }
    private class DialogButtonClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

            String msg ="";
            switch(which){
                case DialogInterface.BUTTON_POSITIVE:
                    msg = getString(R.string.dialog_ok_toast);
                    menuList.remove(position);
                    adapter.notifyDataSetChanged();
                    break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        msg=getString(R.string.dialog_ng_toast);
                        break;
            }
            Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        }
    }
}
