package com.wdx.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.wdx.common.base.BaseConfig;
import com.wdx.model.info.UserInfo;
import com.wdx.model.params.UserParams;
import com.wdx.model.repository.BaseViewModel;
import com.wdx.model.repository.CommonRepository;
import com.wdx.model.response.UserInfoResult;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/8/31 17:56
 */
public class UserViewModel extends BaseViewModel {

    public UserInfo userInfo;

    private LiveData<UserInfo> getUser(String id) {
        return null;
    }

    LiveData<String> userId;
    LiveData<UserInfo> user = Transformations.switchMap(userId, id -> getUser(id));

    LiveData<UserInfo> userLiveData;
    LiveData<String> userName = Transformations.map(userLiveData, user -> {
        // userLiveData.name + " " + userLiveData.lastName;
        return null;
    });


    public UserViewModel() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
    }

    public void requestServerInfo(String name) {
        this.userInfo.setName(name);

    }

    public void requestData() {
        /* MyOkHttpUtils.getInstances().request_okPost(this,BaseConfig.LOGIN,new LoginInfoResult(),loginParams,mUserCallBack,myProgressDialog);*/
        UserParams userParams = new UserParams();
        userParams.name = "wdx";
        userParams.setRequestParams();
        CommonRepository.getResInstance()
                .requestDataPost(BaseConfig.LOGIN, new UserInfoResult(), userParams,
                        mReposityCallBack);
    }

    @Override
    public void onHttpResponse(Object result) {
        super.onHttpResponse(result);
        if (result instanceof UserInfoResult) {
            UserInfoResult userInfoResult = (UserInfoResult) result;
            userInfo = userInfoResult.userInfo;
        }
    }
}
