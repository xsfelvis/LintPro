package xsf.linttest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xsf.linttest.LintTest.LintTestBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnClick;
    private TextView tvShow;
    private TextView tvTest;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        initViews();
        init();
    }

    private void init() {
        Log.d("test lint", "msg");
        new Message();
        Message.obtain();
        handler.obtainMessage();
        handler.sendEmptyMessage(1);
    }

    private void initViews() {
        btnClick = (Button) findViewById(R.id.buttonOne);
        btnClick.setOnClickListener(this);
        tvShow = (TextView) findViewById(R.id.textShow);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOne:
                showText();
                break;
        }
    }

    private void showText() {

        LintTestBean lintTestBean = new LintTestBean("xsf", "666");
        tvShow.setText(lintTestBean.name + "\n" +
                lintTestBean.age + "\n");

    }
}
