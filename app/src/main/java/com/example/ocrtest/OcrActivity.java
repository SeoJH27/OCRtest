package com.example.ocrtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ocrtest.ai.OcrProc;
import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;

public class OcrActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        SharedPreferences sharedPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);

        final String ocrApiGwUrl = sharedPref.getString("ocr_api_gw_url", "");
        final String ocrSecretKey = "TWNtR1BxZ3dVZ0twVXBWZmVRTU9LandXenBRd05oRWI=";
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
        System.out.println("###  Return Thread Result");
        StringBuilder translateText = new StringBuilder();

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("images");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONArray jsonArray_fields = jsonArray.getJSONObject(i).getJSONArray("fields");

                for (int j = 0; j < jsonArray_fields.length(); j++) {

                    String inferText = jsonArray_fields.getJSONObject(j).getString("inferText");
                    translateText.append(inferText);
                    translateText.append(" ");
                }
            }

            TextView txtResult = findViewById(R.id.textView_ocr_result);
            txtResult.setText(translateText.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
