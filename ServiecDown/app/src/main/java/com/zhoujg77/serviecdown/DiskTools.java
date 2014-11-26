package com.zhoujg77.serviecdown;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 建刚 on 2014/11/26.
 */
public class    DiskTools {



    public static  boolean saveToDisk(String fileName,byte [] data){
        boolean flag =false;
        File file = Environment.getExternalStorageDirectory();
        Log.i("flag", "SDKA keyong" +Environment.MEDIA_MOUNTED);
        Log.i("flag", "路径----------" + Environment.getExternalStorageDirectory());
     if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            FileOutputStream outputStream = null;
            try {    outputStream = new FileOutputStream(new File(file,fileName));
                outputStream.write(data,0,data.length);
                flag = true;
                Log.i("flag", "--------------++++++++++++" + flag);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

       }


        return flag;

    }
}
