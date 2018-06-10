package com.example.android.jsonfetch;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progress;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ArrayList<Word> words=new ArrayList<Word>();

        Button click = (Button) findViewById(R.id.button);
        progress =(ProgressBar)findViewById(R.id.pbar);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                fetchData process = new fetchData();
                process.execute();
            }
        });


    }

        public class fetchData extends AsyncTask<Void,Void,Void> {
            String data="";
            String name1="";
            String phase1="";
            int duration1;
            String duration2="";
            Integer startTime;


            final ArrayList<Word> words=new ArrayList<Word>();
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected Void doInBackground(Void... voids) {


                try {
                    URL url= new URL("http://codeforces.com/api/contest.list?gym=false");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    String line = " ";
                    while (line!=null)
                    {
                        line=bufferedReader.readLine();
                        data=data+line;
                    }
                    JSONObject JOO = new JSONObject(this.data);

                    JSONArray JA = (JSONArray) JOO.get("result");
                    for(int i=0;i<JA.length();i++)
                    {
                        JSONObject JO = (JSONObject) JA.get(i);
                        name1 =(String) JO.get("name");
                        phase1 = (String) JO.get("phase");
                        duration1 = (int) JO.get("durationSeconds");
                        startTime = (Integer) JO.get("startTimeSeconds");
//                        long duration1 = Long.parseLong(duration);
                        int hours = (int) duration1/ 3600;
                        int temp = (int) duration1- hours * 3600;
                        int mins = temp / 60;
                        temp = temp - mins * 60;
                        int secs = temp;

                        if(mins==0)
                        {
                            duration2= "Duration : " + hours+ " hrs";
                        }
                        else
                        {
                            duration2= "Duration : " + hours+ " hrs "+mins+" mins";
                        }

                        Date date = new Date(startTime*1000L);
                        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                        String java_date = jdf.format(date);
                        words.add(new Word(name1, phase1,duration2,java_date));
                    }
                }
                catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                final ListView listView = (ListView) findViewById(R.id.list);
                WordAdapter adapter = new WordAdapter(MainActivity.this, words);

                listView.setAdapter(adapter);
                progress.setVisibility(View.INVISIBLE);
            }
        }
}
