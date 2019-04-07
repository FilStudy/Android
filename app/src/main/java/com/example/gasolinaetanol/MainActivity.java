package com.example.gasolinaetanol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView precoGasol;
    private SeekBar precoGasolina;

    private TextView precoEt;
    private SeekBar precoEtanol;


    private TextView resultado;
    private ImageView imageView;

    private double precoG;
    private double precoE;
    private double divisao;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoGasol = (TextView) findViewById(R.id.precoGasol);
        precoGasolina = (SeekBar) findViewById(R.id.precoGasolina);

        precoEt = (TextView) findViewById(R.id.precoEt);
        precoEtanol = (SeekBar) findViewById(R.id.precoEtanol);

        resultado = (TextView) findViewById(R.id.resultado);

        imageView = (ImageView) findViewById(R.id.imageView);

        calculoMelhor();

        precoGasolina.setOnSeekBarChangeListener(observer);
        precoEtanol.setOnSeekBarChangeListener(observer);
    }
    private void calculoMelhor (){
        divisao = precoE / precoG;
        precoGasol.setText(currencyFormat.format(precoG));
        precoEt.setText(currencyFormat.format(precoE));
        if (divisao >= 0.7){
            imageView.setImageResource(R.drawable.gasolina);
            resultado.setText(getString(R.string.melhorPreco, getString(R.string.gasolina)));
        }
        else{
            imageView.setImageResource(R.drawable.etanol);
            resultado.setText(getString(R.string.melhorPreco,getString(R.string.etanol)));
        }
    }

    private SeekBar.OnSeekBarChangeListener observer =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (seekBar.getId() == R.id.precoGasolina){
                        precoG = progress / 100.;
                    }
                    else{
                        precoE = progress / 100.;
                    }
                    calculoMelhor();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
