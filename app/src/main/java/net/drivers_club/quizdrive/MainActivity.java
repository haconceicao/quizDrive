package net.drivers_club.quizdrive;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int scoreFinal = 0;
    int scoreRadioQ01 = 0;
    int scoreRadioQ02 = 0;
    int scoreRadioF = 0;
    boolean Qst1Right = false;
    boolean Qst2Right = false;
    boolean Qst3Right = false;
    private RadioGroup RadioGroup01;
    private RadioGroup RadioGroup02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        myToolbar.setLogo(R.drawable.logo_dc_fb);
        RadioGroup01 = (RadioGroup) findViewById(R.id.radioGQst01);
        RadioGroup02 = (RadioGroup) findViewById(R.id.radioGQst02);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.qst01_01 :
                if (checked)
                    scoreRadioQ01 = 2;
                    Qst1Right = true;
                    break;
            case R.id.qst01_02 :
                if (checked)
                    scoreRadioQ01 = 0;
                    Qst1Right = false;
                    break;
            case R.id.qst02_01:
                if (checked)
                    scoreRadioQ02 = 0;
                    Qst2Right = false;
                    break;
            case R.id.qst02_02:
                if (checked)
                    scoreRadioQ02 = 2;
                    Qst2Right = true;
                    break;
        }
    }

    public void submitTest(View view) {

        scoreRadioF = scoreRadioQ01 + scoreRadioQ02;
        scoreFinal = scoreRadioF;
        scoreRadioF = 0;
        EditText editName = (EditText) findViewById(R.id.name);
        String name = editName.getText().toString();

        boolean q3A1 = ((CheckBox) findViewById(R.id.Qst3_01)).isChecked();
        boolean q3A2 = ((CheckBox) findViewById(R.id.Qst3_02)).isChecked();
        boolean q3A3 = ((CheckBox) findViewById(R.id.Qst3_03)).isChecked();

        if (q3A1 && !q3A3)  {
            scoreFinal = scoreFinal + 1;
        }

        if (q3A2 && !q3A3) {
            scoreFinal = scoreFinal + 1;
            if (q3A1) {
                scoreFinal = scoreFinal + 1;
                Qst3Right = true;
            }
        }

        if (q3A3) {
            if (scoreFinal > 3 ) {
                scoreFinal = scoreFinal - 3;
            }
        }

        if (scoreFinal <0) {
            scoreFinal = 0;
        }

        Context context = getApplicationContext();
        String messageFinal;
        messageFinal = "Hello " + name + "!! Question 1: " + Qst1Right + "; Question 2: " + Qst2Right + "; Question 3: " + Qst3Right + ". FINAL SCORE: " + scoreFinal;
        Toast toastFinal = Toast.makeText(context, messageFinal, Toast.LENGTH_SHORT);
        toastFinal.show();
        scoreFinal = 0;

        //RadioGroup01.clearCheck();
        //RadioGroup02.clearCheck();

        return;


    }
}