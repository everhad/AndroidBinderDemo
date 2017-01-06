package com.idlestars.binderdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.idlestars.binderdemo.serviceapi.IStudentManager;
import com.idlestars.binderdemo.serviceapi.StudentManagerStub;

public class MainActivity extends AppCompatActivity {
    IStudentManager studentManager;

    EditText et_stu_id;
    TextView tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_stu_id = (EditText) findViewById(R.id.et_stu_id);
        tv_age = (TextView) findViewById(R.id.tv_age);

        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
        Log.d("Binder", "call bindService. " + MyApp.getAppDescription());
    }

    public void getAge(View view) {
        if (studentManager != null) {
            int stuId = Integer.valueOf(et_stu_id.getText().toString());
            try {
                int age = studentManager.getAge(stuId);

                tv_age.setText("age: " + age);
            } catch (Exception e) {
                Log.d("Binder", "getAge Exception: " + e.getMessage());
            }
        }
    }

    ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Binder", "onServiceConnected service = " + service);

            if (service != null) {
                Log.d("Binder", "onServiceConnected service is " + service.getClass().getName());

                try {
                    service.linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {
                            studentManager = null;
                            Log.d("Binder", "binderDied");
                        }
                    }, 0);
                } catch (RemoteException e) {
                }
            }
            studentManager = StudentManagerStub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            studentManager = null;
            Log.d("Binder", "onServiceDisconnected ");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
