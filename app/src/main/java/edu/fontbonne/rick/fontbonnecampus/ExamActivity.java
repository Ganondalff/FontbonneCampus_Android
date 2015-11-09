package edu.fontbonne.rick.fontbonnecampus;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        new ConnectTask().execute();
    }

    class ConnectTask extends AsyncTask<Void, Void, List<String[]>>
    {
        protected List<String[]> doInBackground(Void... arg0)
        {
            HttpURLConnection connection = null;
            List<String[]> entries = null;

            try
            {
                URL url = new URL("http://www.primetechconsult.com/fontbonnecampusapp/exam_schedule.csv");
                connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                CSVReader reader = new CSVReader(new InputStreamReader(is));
                entries = reader.readAll();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (connection != null)
                    connection.disconnect();
            }
            return entries;
        }

        protected void onPostExecute(List<String[]> result) {

            try {
                TableLayout examTable = (TableLayout) findViewById(R.id.exam_table);

                for(int i = 0; i < result.size(); i++)
                {
                    TableRow examRow = new TableRow(getApplicationContext());

                    for (int j = 0; j < 4; j++)
                    {
                        TextView examCol = new TextView(getApplicationContext());
                        examCol.setText(result.get(i)[j]);
                        examCol.setTextColor(Color.BLACK);
                        examRow.addView(examCol);
                    }

                    examTable.addView(examRow);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
