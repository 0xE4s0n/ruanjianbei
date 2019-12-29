package cuit.xsgw.fragment;

import android.app.ProgressDialog;
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

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cuit.xsgw.LoginActivity;
import cuit.xsgw.R;
import cuit.xsgw.net.Api;
import cuit.xsgw.net.Http;
import cuit.xsgw.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginFragment extends Fragment {
    private ProgressDialog progDialog;
    private View view;
    private TextInputLayout passwordTextInput;
    private TextInputEditText passwordEditText;
    private TextInputEditText usernameEditText;
    private MaterialButton nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fram, container, false);
        passwordTextInput = view.findViewById(R.id.password_text_input);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        usernameEditText = view.findViewById(R.id.address_edit_text);
        nextButton = view.findViewById(R.id.next_button);

        nextButton.setEnabled(false);
        nextButton.setOnClickListener(view1 -> {
            getActivity().runOnUiThread(() ->
            {
                progDialog = new ProgressDialog(getContext());
                progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progDialog.setIndeterminate(false);
                progDialog.setCancelable(false);
                progDialog.setMessage("登录中...");
                progDialog.show();
            });
            String name = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            HashMap<String, String> user = new HashMap<>();
            user.put("id", name);
            user.put("password", password);
            isPasswordValid(user);
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

    private void isPasswordValid(Map data) {
        new Thread(() -> {
            try {
                new Http().Get(Api.Login, data, null, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        progDialog.dismiss();
                        ToastUtil.show(view, "网络错误：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        try {
                            if (response.isSuccessful()) {
                                String code;
                                code = response.body().string();
                                if (Integer.parseInt(code) == 0) {
                                    getActivity().runOnUiThread(() -> {
                                        progDialog.dismiss();
                                        passwordTextInput.setError("密码错误");
                                        ToastUtil.show(view, "密码错误");
                                    });
                                } else if (Integer.parseInt(code) == 1) {
                                    getActivity().runOnUiThread(() -> {
                                        progDialog.dismiss();
                                        passwordTextInput.setError(null);
                                        usernameEditText.setError(null);
                                        ((LoginActivity) getActivity()).intent();
                                        ToastUtil.show(view, "登录成功");
                                    });
                                } else if (Integer.parseInt(code) == -1) {
                                    getActivity().runOnUiThread(() -> {
                                        progDialog.dismiss();
                                        usernameEditText.setError("用户不存在");
                                        ToastUtil.show(view, "用户不存在");
                                    });
                                } else {
                                    getActivity().runOnUiThread(() -> {
                                        progDialog.dismiss();
                                        ToastUtil.show(view, "未知错误");
                                    });
                                }
                            } else {
                                getActivity().runOnUiThread(() -> {
                                    progDialog.dismiss();
                                    ToastUtil.show(view, "登录失败");
                                });
                            }
                        } catch (IOException e) {
                            getActivity().runOnUiThread(() -> {
                                ToastUtil.show(view, e.getMessage());
                            });
                        }
                    }
                });
            } catch (IOException e) {
                ToastUtil.show(view, e.getMessage());
            }
        }).start();
    }

    private boolean isInputValid(Editable text) {
        return text != null && text.length() > 0;
    }
}