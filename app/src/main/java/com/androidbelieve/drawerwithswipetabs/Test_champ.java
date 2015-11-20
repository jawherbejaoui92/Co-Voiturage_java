package com.androidbelieve.drawerwithswipetabs;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Jawher B on 30/10/2015.
 */
public class Test_champ {





    public void TestLonguer(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {
                       @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() < 4) {
                    champ.setError(msg.toString());

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() < 4) {
                    champ.setError(msg.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() < 4) {
                    champ.setError(msg.toString());

                }
            }
        });
    }

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

    public void TestMotdePasse(final EditText champ1, final EditText champ2, final String msg) {

        champ2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!(champ1.getText().toString().equals(champ2.getText().toString()))) {
                    champ2.setError(msg.toString());

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(champ1.getText().toString().equals(champ2.getText().toString()))) {
                    champ2.setError(msg.toString());


                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(champ1.getText().toString().equals(champ2.getText().toString()))) {
                    champ2.setError(msg.toString());


                }
            }
        });
    }

    public void TestLonguerTel(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() < 8) {
                    champ.setError(msg.toString());


                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() < 8) {
                    champ.setError(msg.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() < 8) {
                    champ.setError(msg.toString());

                }
            }
        });

    }




}


