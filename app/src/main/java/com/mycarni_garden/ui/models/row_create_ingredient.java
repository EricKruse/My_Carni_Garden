package com.mycarni_garden.ui.models;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.mycarni_garden.R;

public class row_create_ingredient {
    private EditText editTextMaterial;
    private EditText editTextParts;

    public row_create_ingredient(View columnView) {
        editTextMaterial = columnView.findViewById(R.id.editText_material);
        editTextParts = columnView.findViewById(R.id.editText_parts);
    }

    public boolean isFilled() {
        return !(TextUtils.isEmpty(editTextMaterial.getText()) && TextUtils.isEmpty(editTextParts.getText()));
    }

    public void setOnTextChangedListener(TextWatcher textWatcher) {
        editTextMaterial.addTextChangedListener(textWatcher);
        editTextParts.addTextChangedListener(textWatcher);
    }

    public void removeOnTextChangedListener(TextWatcher textWatcher){
        editTextMaterial.removeTextChangedListener(textWatcher);
        editTextParts.removeTextChangedListener(textWatcher);
    }
}
