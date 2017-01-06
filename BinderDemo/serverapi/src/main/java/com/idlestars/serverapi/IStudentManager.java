package com.idlestars.serverapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IStudentManager extends IInterface {

    String DESCRIPTOR = "com.idlestars.binderdemo.serviceapi.IStudentManager";
    int TRANSACTION_GET_AGE = (IBinder.FIRST_CALL_TRANSACTION + 0);

    int getAge(int studentId) throws RemoteException;
}
