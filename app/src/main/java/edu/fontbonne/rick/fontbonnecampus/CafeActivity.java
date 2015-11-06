package edu.fontbonne.rick.fontbonnecampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CafeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        HttpURLConnection connection = null;
        int week = 0;
        String[] menu = new String[7];
        try
        {
            /*URL url = new URL("http://url.com/number.xml");
            connection = (HttpURLConnection)url.openConnection();
            InputStream is = connection.getInputStream();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = documentBuilder.parse(is);
            week = Integer.getInteger(xmlDoc.getElementsByTagName("weeknumber").item(0).getTextContent());*/
            week = 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (connection != null)
                connection.disconnect();
        }

        try
        {
            URL url = new URL("http://url.com/menu" + week + ".xml");
            connection = (HttpURLConnection)url.openConnection();
            InputStream is = connection.getInputStream();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = documentBuilder.parse(is);
            NodeList days = xmlDoc.getElementsByTagName("menu").item(0).getChildNodes();
            for (int i = 0; i < days.getLength(); i++)
            {
                NodeList meals = days.item(i).getChildNodes();
                for (int j = 0; j < meals.getLength(); j++)
                {
                    NodeList dishes = meals.item(j).getChildNodes();
                    for (int k = 0; k < dishes.getLength(); k++)
                    {
                        menu[i] = menu[i] + "\n" + dishes.item(k).getTextContent();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (connection != null)
                connection.disconnect();
        }

        TextView menuText1 = (TextView) findViewById(R.id.menuText1);
        TextView menuText2 = (TextView) findViewById(R.id.menuText2);
        TextView menuText3 = (TextView) findViewById(R.id.menuText3);
        TextView menuText4 = (TextView) findViewById(R.id.menuText4);
        TextView menuText5 = (TextView) findViewById(R.id.menuText5);
        TextView menuText6 = (TextView) findViewById(R.id.menuText6);
        TextView menuText7 = (TextView) findViewById(R.id.menuText7);

        menuText1.setText(menu[0]);
        menuText2.setText(menu[1]);
        menuText3.setText(menu[2]);
        menuText4.setText(menu[3]);
        menuText5.setText(menu[4]);
        menuText6.setText(menu[5]);
        menuText7.setText(menu[6]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cafe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
