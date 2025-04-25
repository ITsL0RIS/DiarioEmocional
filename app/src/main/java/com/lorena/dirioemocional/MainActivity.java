package com.lorena.dirioemocional;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private RadioGroup radioGroupDisability;
    private RadioButton radioButtonYes, radioButtonNo;
    private Spinner spinnerGender;
    private CheckBox checkBoxSubscribe;
    private Button buttonClear, buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar os componentes da interface
        editTextName = findViewById(R.id.editTextName);
        radioGroupDisability = findViewById(R.id.radioGroupDisability);
        radioButtonYes = findViewById(R.id.radioButtonYes);
        radioButtonNo = findViewById(R.id.radioButtonNo);
        spinnerGender = findViewById(R.id.spinnerGender);
        checkBoxSubscribe = findViewById(R.id.checkBoxSubscribe);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSave = findViewById(R.id.buttonSave);

        // Configurar as opções do Spinner para Gênero
        String[] genderOptions = {"Feminino", "Masculino", "Outro"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderOptions);
        spinnerGender.setAdapter(genderAdapter);

        // Ação do botão Limpar
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpar os campos
                editTextName.setText("");
                radioGroupDisability.clearCheck();
                checkBoxSubscribe.setChecked(false);
                spinnerGender.setSelection(0); // Define o primeiro item como selecionado (Feminino)
                Toast.makeText(MainActivity.this, "Campos limpos!", Toast.LENGTH_SHORT).show();
            }
        });

        // Ação do botão Salvar
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter valores dos campos
                String name = editTextName.getText().toString().trim();
                int selectedDisabilityId = radioGroupDisability.getCheckedRadioButtonId();
                boolean isSubscribed = checkBoxSubscribe.isChecked();
                String selectedGender = spinnerGender.getSelectedItem().toString();
                String disability = "";

                if (selectedDisabilityId == R.id.radioButtonYes) {
                    disability = "Sim";
                } else if (selectedDisabilityId == R.id.radioButtonNo) {
                    disability = "Não";
                }

                // Validar os campos
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nome não pode estar vazio!", Toast.LENGTH_SHORT).show();
                    editTextName.requestFocus();
                    return;
                }

                if (selectedDisabilityId == -1) {
                    Toast.makeText(MainActivity.this, "Selecione se possui deficiência!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Exibir os valores (temporário - será substituído pela persistência)
                String message = "Nome: " + name + "\n" +
                        "Possui deficiência: " + disability + "\n" +
                        "Gênero: " + selectedGender + "\n" +
                        "Inscrito: " + isSubscribed;

                Toast.makeText(MainActivity.this, "Dados salvos:\n" + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}