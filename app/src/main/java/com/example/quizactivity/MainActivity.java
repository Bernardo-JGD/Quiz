package com.example.quizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Guardo los id de los radioButton
    private int ids_answers[] = {
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_question = (TextView) findViewById(R.id.text_Question);
        //tomo directamente el contenido de values/string (en el xml)
        text_question.setText(R.string.question_content);

        String[] answers = getResources().getStringArray(R.array.answers);

        //Recomencación para cuando son muchas respuestas. Se rellena el texto de cada una con un ciclo
        for(int i = 0; i<ids_answers.length; i++){
            RadioButton rb = (RadioButton) findViewById(ids_answers[i]);
            //rb.setText(String.format("Respuesta %d", i));//Esto es lo que aparecerá en pantalla
            rb.setText(answers[i]);
        }

        //seleeciono el número de la respuesta correcta desde el archivo values/string
        final int correct_answer = getResources().getInteger(R.integer.correct_answer);
        final RadioGroup group = (RadioGroup) findViewById(R.id.radioGrupo);

        Button btn_check = (Button) findViewById(R.id.btnCheck);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = group.getCheckedRadioButtonId(); //selecciono el id (es un número) de la respuesta seleccionada por el usuario
                //Si no se selecciona una respuesta, retorna -1
                int answer = -1;
                for(int i = 0; i < ids_answers.length; i++){
                    if(ids_answers[i] == id){//si alguno de los id de cada respuesta es igual a la respuesta seleccionada
                        answer = i;//guard el indice de la respuesta seleccionada
                    }
                }//Si no coincidio ninguna respuesta, queda el -1

                if(answer == correct_answer){//si la respuesta seleccionada es igual a la respuesta correcta
                    Toast.makeText(MainActivity.this, R.string.respuestaBien, Toast.LENGTH_LONG).show();
                }else{
                    if(answer == -1){
                        Toast.makeText(MainActivity.this, R.string.sinRespuesta, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, R.string.respuestaMal, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}