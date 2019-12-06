package cuit.xsgw.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cuit.xsgw.LoginActivity;
import cuit.xsgw.R;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fram, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        final MaterialButton nextButton = view.findViewById(R.id.next_button);

        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError("密码错误");
                } else {
                    passwordTextInput.setError(null); // Clear the error
                    ((LoginActivity) getActivity()).intent();
                }
            }
        });

        TextWatcher afterTextChange = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isInputValid(passwordEditText.getText()) && isInputValid(usernameEditText.getText())) {
                    passwordTextInput.setError(null);
                    nextButton.setEnabled(true);
                } else
                    nextButton.setEnabled(false);
            }
        };
        passwordEditText.addTextChangedListener(afterTextChange);
        usernameEditText.addTextChangedListener(afterTextChange);
        return view;
    }

    private boolean isPasswordValid(Editable text) {
        //TODO 检查密码
        if (text.toString().equals("admin"))
            return true;
        else
            return false;
    }

    private boolean isInputValid(Editable text) {
        return text != null && text.length() > 0;
    }
}