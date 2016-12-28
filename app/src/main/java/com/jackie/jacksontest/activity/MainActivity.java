package com.jackie.jacksontest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jackie.jacksontest.R;
import com.jackie.jacksontest.util.JacksonUtil;
import com.jackie.jacksontest.util.Stu;

import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
public class MainActivity extends Activity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn1);
        textView = (TextView) findViewById(R.id.tv);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parserJson();
            }
        });
    }

    private void parserJson() {
        try {
            InputStreamReader isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("assets/" + "stu.json"), "utf-8");
            //从assets获取json文件
            BufferedReader bfr = new BufferedReader(isr);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bfr.readLine()) != null) {
                stringBuilder.append(line);
            }//将JSON数据转化为字符串
            //textView.setText(stringBuilder.toString());
            List<Stu> stuList = JacksonUtil.readValue(stringBuilder.toString(), new TypeReference<List<Stu>>() {
            });
            textView.setText(stuList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
