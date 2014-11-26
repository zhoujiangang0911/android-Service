package com.zhoujg77.serviecdown;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by 建刚 on 2014/11/26.
 */
public class DownService  extends Service{

    private  String IMAGE_PATH ="http://www.baidu.com/img/bd_logo1.png";
        private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                stopSelf();
                Toast.makeText(getApplicationContext(),"下载成功",Toast.LENGTH_LONG).show();
            }

        }
    };
    public DownService() {
    }

    public class MyThread implements Runnable{


        @Override
           public void run() {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post  =new HttpPost(IMAGE_PATH);
            try{
                HttpResponse response  = httpClient.execute(post);
                if (response.getStatusLine().getStatusCode() == 200) {
                        byte [] result = EntityUtils.toByteArray(response.getEntity());
                    Log.i("asdfsdafasdf",result.length+"");
                    boolean flag = DiskTools.saveToDisk("aa.png",result);
                    Log.i("flag","--------------"+flag);
                    if (flag){

//                        Message message = Message.obtain();
//                        message.what = 1;
                        handler.sendEmptyMessage(1);
                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("asdf","asdfasdf");
       new Thread( new MyThread()).start();
        Toast.makeText(getApplicationContext(),"开始下载",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
