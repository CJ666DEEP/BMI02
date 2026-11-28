package com.example.bmi01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//new import
import android.app.AlertDialog;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {
    EditText vHeight, vWeight;
    Button submitButton;

    //新增
    Button aboutButton;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vHeight     = findViewById(R.id.heightET);
        vWeight     = findViewById(R.id.weightET);
        submitButton = findViewById(R.id.reportBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String height = vHeight.getText().toString();
                String weight = vWeight.getText().toString();
                if (height.equals("") || weight.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.bmi_warning, Toast.LENGTH_LONG).show();
                } else {
                    savePreferences(height, weight);                 // 第 47–49 页
                    Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("height", height);              // 只传字符串
                    bundle.putString("weight", weight);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        // 新增：点击About BMI 按钮 弹出对话框
        //根据 id `aboutBtn` 找到布局里的 Button 控件，赋值给 aboutButton 变量
        aboutButton = (Button) findViewById(R.id.aboutBtn);
        //给按钮绑定点击监听器
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog.Builder：对话框构造器；`MainActivity.this` 是上下文，当前页面
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.about_button); //设置对话框标题，引用 strings 资源
                builder.setMessage(R.string.about_msg);  //设置对话框正文内容
                //添加【确定】按钮，文字是 OK
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                builder.create(); //根据上面配置创建对话框对象
                builder.show(); //显示对话框
            }
        });



    }

    public void savePreferences(String h, String w) {
        SharedPreferences pref = getSharedPreferences("BMI", MODE_PRIVATE);
        pref.edit().putString("height", h).putString("weight", w).apply();
    }
    public void loadPreferences() {
        SharedPreferences pref = getSharedPreferences("BMI", MODE_PRIVATE);
        vHeight.setText(pref.getString("height", ""));
        vWeight.setText(pref.getString("weight", ""));
    }
    @Override protected void onStart() {
        super.onStart();
        loadPreferences();
    }
}
