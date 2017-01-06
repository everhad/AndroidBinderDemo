package com.idlestars.binderdemo;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.idlestars.binderdemo.serviceapi.StudentManagerStub;

public class RemoteService extends Service {
    StudentManagerStub mBinder = new StudentManagerStub() {

        @Override
        public int getAge(int studentId) throws RemoteException {
            return Math.abs(studentId % 30);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Binder", "onBind called. Process = " + MyApp.getProcessName()
            + ", AppName = " + MyApp.getAppName());

        return mBinder;
    }
}
