package cuit.xsgw.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;

import cuit.xsgw.R;

public class AddUserDialogFragment extends DialogFragment {

    public interface AddUserCallback {
        public void addUser(String id, String phone, String name, String address, String idCard, String passwd, String sex, String disease);

        public void Erro(String e, View v);
    }

    public AddUserDialogFragment(AddUserCallback callback) {
        this.callback = callback;
    }

    AddUserCallback callback;

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "ViewDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_user, null);
        MaterialButton submit = view.findViewById(R.id.submit_button);
        submit.setOnClickListener(v -> {
            if (callback != null) {
                TextInputEditText id = view.findViewById(R.id.id_edit_text);
                TextInputEditText name = view.findViewById(R.id.name_edit_text);
                TextInputEditText password = view.findViewById(R.id.password_edit_text);
                TextInputEditText phone = view.findViewById(R.id.phone_edit_text);
                TextInputEditText address = view.findViewById(R.id.address_edit_text);
                TextInputEditText idCard = view.findViewById(R.id.idcard_edit_text);
                MaterialRadioButton checkBoxmale = view.findViewById(R.id.male);
                MaterialRadioButton checkBoxfemale = view.findViewById(R.id.female);
                MaterialRadioButton checkBoxtnb = view.findViewById(R.id.tnb);
                MaterialRadioButton checkBoxgxy = view.findViewById(R.id.gxy);

                String sex, disease;

                if (checkBoxmale.isChecked())
                    sex = "男";
                else if (checkBoxfemale.isChecked())
                    sex = "女";
                else {
                    callback.Erro("请选择性别", view);
                    return;
                }
                if (checkBoxtnb.isChecked())
                    disease = "糖尿病";
                else if (checkBoxgxy.isChecked())
                    disease = "高血压";
                else {
                    callback.Erro("请选择疾病", view);
                    return;
                }
                if ((phone.getText().toString().equals(""))){
                    callback.Erro("请输入手机号", view);
                    return;
                }
                if ((id.getText().toString().equals(""))){
                    callback.Erro("请输入用户名", view);
                    return;
                }
                if ((name.getText().toString().equals(""))){
                    callback.Erro("请输入姓名", view);
                    return;
                }
                if ((address.getText().toString().equals(""))){
                    callback.Erro("请输入住址", view);
                    return;
                }
                if ((idCard.getText().toString().equals(""))){
                    callback.Erro("请输入身份证号", view);
                    return;
                }
                if ((password.getText().toString().equals(""))){
                    callback.Erro("请输入密码", view);
                    return;
                }
                callback.addUser(id.getText().toString(), phone.getText().toString(), name.getText().toString(), address.getText().toString(), idCard.getText().toString(), password.getText().toString(), sex, disease);
            }
        });
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
