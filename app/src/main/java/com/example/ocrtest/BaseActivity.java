package com.example.ocrtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int itemId = item.getItemId();

        if (itemId == R.id.menu_auth){
            intent = new Intent(getApplicationContext(), AuthActivity.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.menu_home){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            return true;
        }
        else if(itemId == R.id.menu_ocr) {
            intent = new Intent(getApplicationContext(), OcrActivity.class);
            startActivity(intent);

            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}