package com.example.freestorm.androidtest;

import Help.TinySouClient;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	private String TinySou_Token = "token fc0e0c3eedab24673c4e";
    private String TinySou_Url = "http://api.tinysou.com/v1/engines/wym/collections/page/search";
    //搜索内容
    private String Search_Content = null;

    private Handler handler = new Handler() {
        // 处理子线程给我们发送的消息。
        @Override
        public void handleMessage(android.os.Message msg) {
            String content = msg.obj.toString();
            TextView textView = (TextView) findViewById(R.id.showContent);
            textView.setText(content);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onClick(final View view){
        new Thread(new Runnable() {
            public void run() {
                EditText editText = (EditText) findViewById(R.id.tinyso_edit_message);
                if ("".equals(editText.getText().toString().trim())) {
                    return;
                }
                Search_Content = editText.getText().toString();
                TinySouClient client = new TinySouClient(TinySou_Token, "post", TinySou_Url, Search_Content);
                String result = client.Search();
                Message message = Message.obtain();
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
    }
}
