package com.example.ocrtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.*;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ocrtest.ai.OcrProc;

import org.json.JSONArray;
import org.json.JSONObject;

public class OcrActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        SharedPreferences sharedPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);

        final String ocrApiGwUrl = sharedPref.getString("ocr_api_gw_url", "");
        final String ocrSecretKey = sharedPref.getString("ocr_secret_key", "");

        Button ocrTranslateBtn;

        ocrTranslateBtn = (Button) findViewById(R.id.btn_ocr_translate);
        ocrTranslateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                OcrActivity.PapagoNmtTask nmtTask = new OcrActivity.PapagoNmtTask();
                nmtTask.execute(ocrApiGwUrl, ocrSecretKey);
            }
        });
    }

    public class PapagoNmtTask extends AsyncTask<String, String, String> {

        @Override
        public String doInBackground(String... strings) {

            return OcrProc.main(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(String result) {

            ReturnThreadResult(result);
        }
    }

    public void ReturnThreadResult(String result) {
        System.out.println("###  Retrun Thread Result");
        String translateText = "";

        String rlt = result;
        try {
            JSONObject jsonObject = new JSONObject(rlt);

            JSONArray jsonArray  = jsonObject.getJSONArray("images");

            for (int i = 0; i < jsonArray.length(); i++ ){

                JSONArray jsonArray_fields  = jsonArray.getJSONObject(i).getJSONArray("fields");

                for (int j=0; j < jsonArray_fields.length(); j++ ){

                    String inferText = jsonArray_fields.getJSONObject(j).getString("inferText");
                    translateText += inferText;
                    translateText += " ";
                }
            }

            TextView txtResult = (TextView) findViewById(R.id.textView_ocr_result);
            txtResult.setText(translateText);

        } catch (Exception e){

        }
    }
}
