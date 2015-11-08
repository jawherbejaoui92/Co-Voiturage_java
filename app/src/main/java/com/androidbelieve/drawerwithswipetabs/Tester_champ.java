package com.androidbelieve.drawerwithswipetabs;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Jawher B on 01/11/2015.
 */
public class Tester_champ extends AppCompatActivity {

    public void TestLonguer(final EditText champ, final String msg) {
        champ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() < 3)
                    champ.setError(msg.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() < 3)
                    champ.setError(msg.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() < 3)
                    champ.setError(msg.toString());
            }
        });
    }

    /******************************************************/

    public void TestVide(final EditText champ, final String msg) {
        champ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() == 0) {
                    champ.setError(msg.toString());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() == 0) {
                    champ.setError(msg.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() == 0) {
                    champ.setError(msg.toString());
                }
            }
        });
    }

    /******************************************************/

    public void TestMotdePasse(final EditText champ1, final EditText champ2, final String msg1, final String msg2) {
        champ1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ1.getText().toString().length() != champ2.getText().length())
                    champ2.setError(msg1.toString());
                else if ((champ1.getText().toString().length() <3) || champ2.getText().length()<3)
                champ2.setError(msg2.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ1.getText().toString().length() != champ2.getText().length())
                    champ2.setError(msg1.toString());
                else if ((champ1.getText().toString().length() <3) || champ2.getText().length()<3)
                    champ2.setError(msg2.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ1.getText().toString().length() != champ2.getText().length())
                    champ2.setError(msg1.toString());
                else if ((champ1.getText().toString().length() <3) || champ2.getText().length()<3)
                    champ2.setError(msg2.toString());

            }
        });
    }

    public void TestLonguerTel(final EditText champ,final String msg) {
        champ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() < 8)
                    champ.setError(msg.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() < 8)
                    champ.setError(msg.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() < 8)
                    champ.setError(msg.toString());
            }
        });
    }
}
