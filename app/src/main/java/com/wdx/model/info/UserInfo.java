package com.wdx.model.info;

import androidx.lifecycle.MutableLiveData;
import java.io.Serializable;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/8/31 18:10
 */
public class UserInfo implements Serializable {
    MutableLiveData<String> userName;

    public MutableLiveData<String> getName() {
        if (userName == null) {
            userName = new MutableLiveData<>();
        }
        return userName;
    }

    public void setName(String name) {
        if (userName == null) {
            userName = new MutableLiveData<>();
        }
        userName.setValue(name);
    }
}
