package com.idlestars.serverapi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class StudentManager implements IStudentManager {

    IBinder mRemote;

    public StudentManager(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public int getAge(int studentId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int age = 0;

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeInt(studentId);
            mRemote.transact(TRANSACTION_GET_AGE, data, reply, 0);
            reply.readException();
            age = reply.readInt();
        } finally {
            data.recycle();
            reply.recycle();
        }

        return age;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
