package com.dieunn.mystore_admin.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dieunn.mystore_admin.R;
import com.dieunn.mystore_admin.activity.AdminActivity;
import com.dieunn.mystore_admin.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class DangNhapFragment extends Fragment {
    private TextInputLayout layoutEmail;
    private TextInputLayout layoutMatKhau;
    private TextInputEditText inputEmail;
    private TextInputEditText inputMatKhau;
    private Button btnDangNhap;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        return inflater.inflate(R.layout.fragment_dang_nhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        onEvent();
    }

    private void initView(View view) {
        layoutEmail = view.findViewById(R.id.input_layout_dang_nhap_email);
        layoutMatKhau = view.findViewById(R.id.input_layout_dang_nhap_mat_khau);
        inputEmail = view.findViewById(R.id.input_dang_nhap_email);
        inputMatKhau = view.findViewById(R.id.input_dang_nhap_mat_khau);
        btnDangNhap = view.findViewById(R.id.btn_dang_nhap);
    }

    private void onEvent() {
        removeErrorOnTextChange();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    // nhập tên tài khoản là admin và mật khẩu trong file Constants.java, nếu đúng thì chuyển sang tài khoản admin
                    // bỏ qua bước đăng nhập bằng Firebase auth
                    if (inputEmail.getText().toString().equals("admin") && inputMatKhau.getText().toString().equals(Constants.ADMIN_PASSWORD)) {
                        Toast.makeText(requireContext(), "Chào mừng bạn đến với trang quản trị ứng dụng!", Toast.LENGTH_SHORT).show();
                        requireActivity().startActivity(new Intent(requireActivity(), AdminActivity.class));

                    }
                }
            }
        });
    }

    private boolean validateForm() {
        removeErrorOnTextChange();
        boolean isFormValid = true;

        if (inputEmail.getText().length() == 0) {
            layoutEmail.setError("Không đúng định dạng email!");
            isFormValid = false;
        }


        if (inputMatKhau.getText().length() == 0) {
            layoutMatKhau.setError("Bạn chưa nhập mật khẩu!");
            isFormValid = false;
        }


        return isFormValid;
    }

    private void removeErrorOnTextChange() {
        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                layoutEmail.setError(null);
                layoutEmail.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                layoutMatKhau.setError(null);
                layoutMatKhau.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
