package com.wdx.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wdx.model.info.UserInfo;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/8/31 17:56
 */
public class UserViewModel extends ViewModel {
    public UserInfo userInfo;


    public UserViewModel(){
        if(userInfo == null){
            userInfo = new UserInfo();
        }
    }
    public void requestServerInfo(String name) {
        this.userInfo.setName(name);
    }


}
