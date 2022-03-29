package lk.isoft.firstjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] array = new String[370089];
    String wordo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findTheWord();
        TextView word = findViewById(R.id.textView);
        EditText result = findViewById(R.id.resu);
        Button cbut = findViewById(R.id.checkb);
        int max = 370088;
        int min = 0;
        int randomInt = (int)Math.floor(Math.random()*(max - min + 1) + min);
        wordo = array[randomInt];
        word.setText(removeLetters(wordo));
        final int[] tries = {3};

        cbut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String d = String.valueOf(result.getText());
                if(d.equals(wordo)){
                    Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
                    result.setText("");
                    int randomInt = (int)Math.floor(Math.random()*(max - min + 1) + min);
                    wordo = array[randomInt];
                    String finalWord = removeLetters(wordo);
                    word.setText(finalWord);
                }
                else{

                    if(tries[0] == 0){
                        Toast.makeText(getApplicationContext(), "The word was " + wordo , Toast.LENGTH_LONG).show();
                        result.setText("");
                        int randomInt = (int)Math.floor(Math.random()*(max - min + 1) + min);
                        wordo = array[randomInt];
                        String finalWord = removeLetters(wordo);
                        word.setText(finalWord);
                        tries[0] = 3;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                        tries[0]--;
                    }
                }
            }
        });
    }
    private void findTheWord() {
        InputStream pathname = getResources().openRawResource(R.raw.names);
        BufferedReader br = new BufferedReader(new InputStreamReader(pathname));
        String line = null;

        int i = 0;
        while(true){
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            array[i] = line;
            //System.out.println(array[i]);  //This line is for testing
            i++;
        }

    }
    private String removeLetters(String word){
        String[] wordSp;
        wordSp = wordo.split("");
        int c = 0;
        String finalWord = "";
        while (c<(wordSp.length)/2){
            c++;
            int maxWordLetters = wordSp.length - 1;
            int minWordLetters = 0;
            int rannum = (int)Math.floor(Math.random()*(maxWordLetters - minWordLetters + 1) + minWordLetters);
            wordSp[rannum] = " _ ";
        }
        for (String s:wordSp){
            finalWord = finalWord + s;
        }
        System.out.println(wordo);
        return finalWord;
    }
}