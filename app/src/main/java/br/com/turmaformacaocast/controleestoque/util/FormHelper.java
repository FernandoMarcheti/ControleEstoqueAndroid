package br.com.turmaformacaocast.controleestoque.util;

import android.widget.EditText;

public final class FormHelper {

    private FormHelper() {
        super();
    }

    public static boolean checkRequireFields(String requiredMessage, EditText... editTexts) {

        boolean hasRequired = false;

        for (EditText editText : editTexts) {
            String textValue = editText.getText().toString();
            if (textValue.trim().isEmpty()) {
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }
        return hasRequired;
    }

}
