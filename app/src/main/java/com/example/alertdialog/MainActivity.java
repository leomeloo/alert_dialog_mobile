package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button simples, checkbox, view, verificarView;

    private AlertDialog alerta;

    CheckBox myCheckbox;

    //VAI RECEBER UM LAYOUT "INFLAR UM LAYOUT"
    View myView;

    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simples = findViewById(R.id.simples);

        //AÇÃO DO BOTAO SIMPLES
        simples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CarregaAlertDialog();

            }
        });

        checkbox = findViewById(R.id.checkbox);
        //AÇÃO DO BOTAO CHECKBOX
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CarregaMultiplosCheckbox();
            }
        });

        view = findViewById(R.id.view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CarregarAlertView();
            }
        });

        verificarView = findViewById(R.id.verificarView);

        verificarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check)
                    Toast.makeText(getApplicationContext(), "Check está selecionado", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Check não está selecionado", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void CarregarAlertView() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        myView = getLayoutInflater().inflate(R.layout.layout_alert, null);

        myCheckbox = myView.findViewById(R.id.checkBox1);

        builder.setTitle("CheckBox ");
        builder.setView(myView);

        check = false;

        myCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    check = true;
                else
                    check = false;
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();

    }

    //MÉTODO DO BOTAO CHECKBOX
    private void CarregaMultiplosCheckbox() {

        //CRIAR UMA SEQUENCIA DE CHARS
        CharSequence[] charSequences = new CharSequence[]{"Corinthians", "Bahia", "Cruzeiro"};

        final boolean[] checados = new boolean[charSequences.length];

        //CRIANDO UM GERENCIADOR PARA O ALERT
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Qual dos clubes é o melhor do Brasil?");

        builder.setMultiChoiceItems(charSequences, checados, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {

                checados[position] = isChecked;
            }
        });

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                StringBuilder texto = new StringBuilder("O melhor é o ");

                if(checados[0])
                    texto.append("Corinthians").append("!");

                if(checados[1])
                    texto.append("Bahia").append("!").append("teste");

                if(checados[2])
                    texto.append("Cruzeiro").append("!");

                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
            }
        });

        alerta = builder.create();
        alerta.show();

    }

    //MÉTODO DO BOTAO SIMPLES
    private void CarregaAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("RESPONDA");
        builder.setMessage("Corinthians é o melhor do mundo?!");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"VAI CORINTHIANS !", Toast.LENGTH_LONG).show();
            }
        });

                builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "SAI PORCO", Toast.LENGTH_LONG).show();
                    }
                });

                alerta = builder.create();
                alerta.show();

    }

    //CONFIGURAÇÕES DO ALERTA COM CHECKBOX

}
