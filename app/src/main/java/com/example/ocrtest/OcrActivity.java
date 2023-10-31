package com.example.ocrtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ocrtest.ai.OcrProc;
import org.json.JSONArray;
import org.json.JSONObject;

public class OcrActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        final String ocrApiGwUrl = "https://wuruk0p342.apigw.ntruss.com/custom/v1/25116/1b481823b785af66fad5bb3187dcf8904363afa68fdcde0339e1089e1db637ee/general";
        final String ocrSecretKey = "bU9yZm5uUGx6V2dKcVRDTkttUlRlWFZiUWtXaEN0VXQ=";
        Button ocrTranslateBtn;

        ocrTranslateBtn = (Button) findViewById(R.id.btn_ocr_translate);
        ocrTranslateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ThreadClass thc = new ThreadClass();
                thc.execute(ocrApiGwUrl, ocrSecretKey);
            }
        });
    }

    public class ThreadClass extends AsyncTask<String, String, String> {

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
        Log.i("text", result);

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
            Log.i("text", translateText.toString());

        } catch (Exception e) {
            Log.e("error?", e.toString());
        }
    }
}
