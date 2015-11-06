package edu.fontbonne.rick.fontbonnecampus;

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

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        HttpURLConnection connection = null;
        TableLayout examTable = (TableLayout) findViewById(R.id.exam_table);

        try
        {
            URL url = new URL("http://url.com/exam_schedule.csv");
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null)
            {
                TableRow examRow = new TableRow(getApplicationContext());
                TextView examCol1 = new TextView(getApplicationContext());
                TextView examCol2 = new TextView(getApplicationContext());
                TextView examCol3 = new TextView(getApplicationContext());
                TextView examCol4 = new TextView(getApplicationContext());

                examCol1.setText(nextLine[0]);
                examCol2.setText(nextLine[1]);
                examCol3.setText(nextLine[2]);
                examCol4.setText(nextLine[3]);

                examRow.addView(examCol1);
                examRow.addView(examCol2);
                examRow.addView(examCol3);
                examRow.addView(examCol4);

                examTable.addView(examRow);
            }
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
    }
}
