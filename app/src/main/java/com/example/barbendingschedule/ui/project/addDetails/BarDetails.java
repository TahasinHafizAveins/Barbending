package com.example.barbendingschedule.ui.project.addDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.Model.BarType;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class BarDetails extends AppCompatActivity implements BarDetailContract.View {
    BarDetailPresenter mPresenter;

    private TextInputLayout tiTitle, tiNoBar, tiDia, tia, tib, tic, tid, tie, tif;
    private TextInputEditText etTitle, etNoBar, etDia, eta, etb, etc, etd, ete, etf;
    private Spinner spBarType;
    private Button submit, cancel;
    private int position;
    private double a = 0.0, b = 0.0, c = 0.0, d = 0.0, e = 0.0, f = 0.0;

    private BarTypeAdapter barTypeAdapter;
    private String project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_details);
        this.project_id = getIntent().getStringExtra(Constants.PROJECT_ID);

        mPresenter = new BarDetailPresenter(this);

        spBarType = findViewById(R.id.sp_Type);

        tiTitle = findViewById(R.id.ti_Title);
        tiNoBar = findViewById(R.id.tiNo_bar);
        tiDia = findViewById(R.id.ti_Dia);
        tia = findViewById(R.id.ti_a);
        tib = findViewById(R.id.ti_b);
        tic = findViewById(R.id.ti_c);
        tid = findViewById(R.id.ti_d);
        tie = findViewById(R.id.ti_e);
        tif = findViewById(R.id.ti_f);


        etTitle = findViewById(R.id.et_Title);
        etNoBar = findViewById(R.id.etNo_bar);
        etDia = findViewById(R.id.et_Dia);
        eta = findViewById(R.id.et_a);
        etb = findViewById(R.id.et_b);
        etc = findViewById(R.id.et_c);
        etd = findViewById(R.id.et_d);
        ete = findViewById(R.id.et_e);
        etf = findViewById(R.id.et_f);


        invisibleFields();

        spBarType = findViewById(R.id.sp_Type);

        spBarType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("spiner:", i + "");

                BarType barType = (BarType) barTypeAdapter.getItem(i);
                visibleItems(barType);
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        barTypeAdapter = new BarTypeAdapter(this);
        spBarType.setAdapter(barTypeAdapter);
        mPresenter.getAllBarType();


        submit = findViewById(R.id.btn_Submit);
        cancel = findViewById(R.id.btn_Cancel);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int  numBar = 0, dia = 0;

                String title = etTitle.getText().toString().trim();
                String numBar1 = etNoBar.getText().toString().trim();
                String dia1 = etDia.getText().toString().trim();
                String txt_a = eta.getText().toString().trim();
                String txt_b = etb.getText().toString().trim();
                String txt_c = etc.getText().toString().trim();
                String txt_d = etd.getText().toString().trim();
                String txt_e = ete.getText().toString().trim();
                String txt_f = etf.getText().toString().trim();

                try {
                    a = Double.parseDouble(txt_a);

                } catch (Exception e) {

                }
                try {
                    b = Double.parseDouble(txt_b);
                } catch (Exception e) {

                }
                try {
                    c = Double.parseDouble(txt_c);

                } catch (Exception e) {

                }
                try {
                    d = Double.parseDouble(txt_d);

                } catch (Exception e) {

                }
                try {
                    e = Double.parseDouble(txt_e);

                } catch (Exception e) {

                }
                try {
                    f = Double.parseDouble(txt_f);

                } catch (Exception e) {

                }
                try {
                    numBar = Integer.valueOf(numBar1);

                } catch (Exception e) {

                }
                try {
                    dia = Integer.valueOf(dia1);

                } catch (Exception e) {

                }
                Bar bar = new Bar(position, title, numBar, dia, a, b, c, d, e, f);

                boolean valid = mPresenter.validation(bar);
                if (!valid) {
                    return;
                }
                Log.d("BarDia", "" + bar.getDia());
                bar.setProject_id(project_id);
                bar.setBar_img(((BarType) barTypeAdapter.getItem(position)).getImg_url());
                mPresenter.submit(bar);

                //Sending Values of bars

                //finish();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void visibleItems(BarType barType) {
        if (barType.isA()) {
            eta.setVisibility(View.VISIBLE);
        } else {
            eta.setVisibility(View.GONE);
        }
        if (barType.isB()) {
            etb.setVisibility(View.VISIBLE);
        } else {
            etb.setVisibility(View.GONE);
        }
        if (barType.isC()) {
            etc.setVisibility(View.VISIBLE);
        } else {
            etc.setVisibility(View.GONE);
        }
        if (barType.isD()) {
            etd.setVisibility(View.VISIBLE);
        } else {
            etd.setVisibility(View.GONE);
        }
        if (barType.isE()) {
            ete.setVisibility(View.VISIBLE);
        } else {
            ete.setVisibility(View.GONE);
        }
        if (barType.isF()) {
            etf.setVisibility(View.VISIBLE);
        } else {
            etf.setVisibility(View.GONE);
        }
    }

    private void invisibleFields() {
        eta.setVisibility(View.GONE);
        etb.setVisibility(View.GONE);
        etc.setVisibility(View.GONE);
        etd.setVisibility(View.GONE);
        ete.setVisibility(View.GONE);
        etf.setVisibility(View.GONE);
    }

    @Override
    public void showError(int fieldId, String massage) {
        switch (fieldId) {
            case 1:
                tiTitle.setError(massage);
                etTitle.requestFocus();
                break;
            case 2:
                tiNoBar.setError(massage);
                etNoBar.requestFocus();
                break;
            case 3:
                tiDia.setError(massage);
                etDia.requestFocus();
                break;
            case 4:
                Log.d("lengthError", massage);
                Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void clearError() {
        tiTitle.setErrorEnabled(false);
        tiNoBar.setErrorEnabled(false);
        tiDia.setErrorEnabled(false);
    }

    @Override
    public void renderTypes(List<BarType> barTypeList) {
        barTypeAdapter.addAll(barTypeList);
    }

    @Override
    public void sendData(Bar bar) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BAR, bar);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();

    }

}
