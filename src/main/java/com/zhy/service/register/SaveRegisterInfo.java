package com.zhy.service.register;

import com.zhy.model.UserRegisterInfo;
import com.zhy.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 21:24 2018/1/16
 * Describe:
 */
@Service
public class SaveRegisterInfo {

    @Autowired
    RegisterRepository registerRepository;

    public void saveRegister(UserRegisterInfo userRegisterInfo){

        if(userRegisterInfo.getObey() == null){
            userRegisterInfo.setObey("false");
        }

        registerRepository.save(userRegisterInfo);

    }

}
