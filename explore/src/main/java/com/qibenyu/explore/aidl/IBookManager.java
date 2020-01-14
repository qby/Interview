package com.qibenyu.explore.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public interface IBookManager extends IInterface {

    public static abstract class Stub extends Binder implements IBookManager {
        private static final String DESCRIPTOR = "com.qibenyu.explore.aldl.IBookManager";

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IBookManager asInterface(IBinder binder) {
            if (binder == null) return null;

            IInterface iInterface = binder.queryLocalInterface(DESCRIPTOR);
            if (iInterface != null && iInterface instanceof IBookManager) {
                return (IBookManager) iInterface;
            }

            return new Proxy(binder);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

            String des = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION:
                    reply.writeString(DESCRIPTOR);
                    break;
                case TRANSACTION_getBookAuthor:
                    data.enforceInterface(DESCRIPTOR);
                    String s = data.readString();
                    String _result = this.getBookAuthor(s);
                    reply.writeNoException();

                    break;
                case TRANSACTION_getUserName:
                    break;

            }

            return true;

        }

        private static class Proxy implements IBookManager {

            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public String getBookAuthor(String bookName) throws RemoteException {
                return null;
            }

            @Override
            public String getUserName() throws RemoteException {
                return null;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

        }

        static final int TRANSACTION_getBookAuthor = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getUserName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    public String getBookAuthor(String bookName) throws RemoteException;

    public String getUserName() throws RemoteException;
}
