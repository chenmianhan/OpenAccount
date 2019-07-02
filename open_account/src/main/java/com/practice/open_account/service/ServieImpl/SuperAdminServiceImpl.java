package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.config.shiro.common.UserToken;
import com.practice.open_account.dao.SuperAdminDAO;
import com.practice.open_account.service.SuperAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/****
 *@author:hwy
 *@date: 2019/7/011510
 *@descrption:
 */
@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Resource
    private SuperAdminDAO superAdminDAO;

    @Override
    @Transactional
    public int getSecurityUnderAdmin(int admin_id){
        return superAdminDAO.getSecurityUnderAdmin(admin_id);
    }

    @Override
    @Transactional
    public int addAdmin(String name, String account, String password, int store){
        int max_id = superAdminDAO.getMaxId();
        superAdminDAO.addAdminToEmployee(max_id+1, name, account, password);
        superAdminDAO.addAdminToAdminManager(max_id+1, store);
        return 1;
    }

    @Override
    @Transactional
    public int modifyAdmin(int employee_id, String name, String password, String account){
        return superAdminDAO.modifyAdmin(employee_id, name, password, account);
    }

    @Override
    @Transactional
    public int addStore(){
        // Aborting
        return 0;
    }

    @Override
    @Transactional
    public int deleteStore(int store){
        int userNum = superAdminDAO.getUsersNumUnderStore(store);
        if (userNum > 0){
            return 0;
        }
        else {
            List<Integer> adminList = superAdminDAO.getAdminUnderStore(store);
            List<Integer> auditorList = superAdminDAO.getAuditorUnderStore(store);
            for (int i = 0; i < adminList.size(); i++) {
                superAdminDAO.deleteEmployeeUnderStore(adminList.get(i));
                System.out.println(adminList.get(i));
            }
            for (int i = 0; i < auditorList.size(); i++) {
                superAdminDAO.deleteEmployeeUnderStore(auditorList.get(i));
            }
            superAdminDAO.deleteStoreFromAdminManager(store);
            superAdminDAO.deleteStoreFromAuditorManager(store);
            superAdminDAO.deleteStoreFromSecurity(store);
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteUser(int user_id){
        String customer_id = superAdminDAO.getCustomerId(user_id);
        superAdminDAO.deleteUserInTradeAccount(customer_id);
        superAdminDAO.deleteUserInFundAccount(customer_id);
        superAdminDAO.deleteUserInCustomerAccount(user_id);
        superAdminDAO.deleteUserInAccountInfo(user_id);
        return 1;
    }
}
