package com.idlestars.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.idlestars.serverapi.StudentManagerStub;


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
        Log.d("Binder", "onBind called. " + MyApp.getAppDescription());

        return mBinder;
    }
}
