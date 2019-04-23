package com.example.yogeshbhosage.mysmsreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.button);
        tv=findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fin;
                try {
                    fin=openFileInput("msg");
                    InputStreamReader in=new InputStreamReader(fin);
                    BufferedReader bf=new BufferedReader(in);

                    StringBuffer sb=new StringBuffer();
                    do{
                        sb.append(bf.readLine()).append("\n");

                    }while(bf.readLine()!=null);

                    tv.setText(sb.toString());
                    Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
