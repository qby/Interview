// IUserService.aidl
package com.qibenyu.explore;

// Declare any non-default types here with import statements

import com.qibenyu.explore.IConnectCallback;
interface IUserService {

    String getUserName();

    void connectService(int i, IConnectCallback calback);
}
