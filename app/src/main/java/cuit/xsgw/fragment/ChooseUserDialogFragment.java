package cuit.xsgw.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class ChooseUserDialogFragment extends DialogFragment {
    private String[] items;
    private DialogInterface.OnClickListener onClickListener;
    private String title;

    public void show(String title, String[] items, DialogInterface.OnClickListener onClickListener,
                     FragmentManager fragmentManager) {
        this.title = title;
        this.items = items;
        this.onClickListener = onClickListener;
        show(fragmentManager, "ItemsDialogFragment");
    }

    public String getitem(int i)
    {
        return items[i];
    }

    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(title).setItems(items, onClickListener);
        return builder.create();
    }
}

