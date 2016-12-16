package xsf.linttest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import xsf.linttest.LintTest.LintTestBean;

import static xsf.linttest.R.id.activity_main;

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
        System.out.println("test");

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        Log.d("test lint", "msg");

        new Message();
        Message.obtain();
        handler.obtainMessage();
        handler.sendEmptyMessage(1);
        getLayoutInflater().inflate(R.layout.time, (ViewGroup) findViewById(activity_main));
    }

    private void initViews() {
        btnClick = (Button) findViewById(R.id.One);
        btnClick.setOnClickListener(this);
        tvShow = (TextView) findViewById(R.id.Show);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.One:
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
