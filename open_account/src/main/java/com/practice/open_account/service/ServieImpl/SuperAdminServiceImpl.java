package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import java.sql.Date;
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
                superAdminDAO.deleteEmployee(adminList.get(i));
                System.out.println(adminList.get(i));
            }
            for (int i = 0; i < auditorList.size(); i++) {
                superAdminDAO.deleteEmployee(auditorList.get(i));
            }
            superAdminDAO.deleteStoreFromAdminManager(store);
            superAdminDAO.deleteStoreFromAuditorManager(store);
            superAdminDAO.deleteStoreFromSecurity(store);
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteUser(int user_id) {
        String customer_id = superAdminDAO.getCustomerId(user_id);
        superAdminDAO.deleteUserInTradeAccount(customer_id);
        superAdminDAO.deleteUserInFundAccount(customer_id);
        superAdminDAO.deleteUserInCustomerAccount(user_id);
        superAdminDAO.deleteUserInAccountInfo(user_id);
        superAdminDAO.deleteUserInUser(user_id);
        return 1;
    }

    @Override
    @Transactional
    public int deleteAdmin(int admin_id) {
        superAdminDAO.deleteAdminInAdminManager(admin_id);
        superAdminDAO.deleteEmployee(admin_id);
        return 1;
    }

    @Override
    @Transactional
    public JSONArray getStore(String store) {
        String store_like = "%" + store + "%";
        List<JSONObject> jsArray = superAdminDAO.getStore(store_like);
        JSONArray res = new JSONArray();
        for (int i = 0; i < jsArray.size(); i++) {
            JSONObject js = jsArray.get(i);
            int security_id = js.getInteger("security_id");
            int users_num = superAdminDAO.getUsersNumUnderStore(security_id);
            js.put("user_number", users_num);
            res.add(js);
        }
        return res;
    }

    @Override
    @Transactional
    public JSONArray getAllStore() {
        List<JSONObject> jsArray = superAdminDAO.getAllStore();
        JSONArray res = new JSONArray();
        for (int i = 0; i < jsArray.size(); i++) {
            JSONObject js = jsArray.get(i);
            int security_id = js.getInteger("security_id");
            int users_num = superAdminDAO.getUsersNumUnderStore(security_id);
            js.put("user_number", users_num);
            res.add(js);
        }
        return res;
    }

    @Override
    @Transactional
    public JSONArray getAdminByStore(String store) {
        JSONArray storeArray = this.getStore(store);
        JSONArray res = new JSONArray();
        for (int i = 0; i < storeArray.size(); i++) {
            JSONObject storeJs = storeArray.getJSONObject(i);
            String storeName = storeJs.getString("name");
            int security_id = storeJs.getInteger("security_id");
            JSONObject employeeJs = superAdminDAO.getAdminByStore(security_id);
            if (employeeJs != null){
                JSONObject resJs = new JSONObject();
                resJs.put("admin_id", employeeJs.getInteger("employee_id"));
                resJs.put("name", employeeJs.getString("employee_name"));
                resJs.put("account", employeeJs.getString("employee_account"));
                resJs.put("password", employeeJs.getString("employee_password"));
                resJs.put("store", storeName);
                res.add(resJs);
            }
        }
        return res;
    }


    @Override
    @Transactional
    public JSONArray getAdminByName(String admin_name){
        admin_name = "%" + admin_name + "%";
        List<JSONObject> jsList = superAdminDAO.getAdminByName(admin_name);
        JSONArray res = new JSONArray();
        for (int i = 0; i < jsList.size(); i++) {
            JSONObject js = jsList.get(i);
            int security_id = js.getInteger("security_id");
            String store = superAdminDAO.getSecurity(security_id);
            JSONObject resJs = new JSONObject();
            resJs.put("admin_id", js.getInteger("employee_id"));
            resJs.put("name", js.getString("employee_name"));
            resJs.put("account", js.getString("employee_account"));
            resJs.put("password", js.getString("employee_password"));
            resJs.put("store", store);
            res.add(resJs);
        }
        return res;
    }

    @Override
    @Transactional
    public JSONArray getAllAdmin() {
        List<JSONObject> jsList = superAdminDAO.getAllAdmin();
        JSONArray res = new JSONArray();
        for (int i = 0; i < jsList.size(); i++) {
            JSONObject js = jsList.get(i);
            int security_id = js.getInteger("security_id");
            String store = superAdminDAO.getSecurity(security_id);
            JSONObject resJs = new JSONObject();
            resJs.put("admin_id", js.getInteger("employee_id"));
            resJs.put("name", js.getString("employee_name"));
            resJs.put("account", js.getString("employee_account"));
            resJs.put("password", js.getString("employee_password"));
            resJs.put("store", store);
            res.add(resJs);
        }
        return res;
    }

    @Override
    @Transactional
    public JSONObject getSuperAdminName() {
        String super_admin_name = superAdminDAO.getSuperAdminName();
        JSONObject res = new JSONObject();
        res.put("superadminName", super_admin_name);
        return res;
    }

    @Override
    public JSONObject getUserList()
    {
        JSONArray ans=new JSONArray();
        List<JSONObject> tmp=superAdminDAO.getUserList();
        for (int i = 0; i < tmp.size(); i++) {
            JSONObject js = tmp.get(i);
            JSONObject ansJs = new JSONObject();
            String value = js.getString("name");
            String address = js.getString("user_id");
            ansJs.put("value", value);
            ansJs.put("address", address);
            ans.add(ansJs);
        }
        JSONObject res = new JSONObject();
        res.put("username", ans);
        return res;
    }

    @Override
    public JSONObject getUserInfo(int usrId)
    {
        return superAdminDAO.getUserInfo(usrId);
    }
    @Override
    public String getAddressInfo(int aId){
        JSONObject js=superAdminDAO.getAddressInfo(aId);
        String s=js.getString("province");
        s=s.concat(js.getString("city"));
        s=s.concat(js.getString("street"));
        s=s.concat(js.getString("detail"));
        return s;
    }
    @Override
    public JSONObject getPhoneAndTime(int usrId){
        return superAdminDAO.getPhoneAndTime(usrId);
    }

    @Override
    @Transactional
    public int changeMaxAuditorNum(int max_num) {
        return superAdminDAO.changeMaxAuditorNum(max_num);
    }

    @Override
    @Transactional
    public int changeExpireDate(Date expire_date) {
        return superAdminDAO.changeExpireDate(expire_date);
    }

    @Override
    @Transactional
    public int changeMinScore(int min_score) {
        return superAdminDAO.changeMinScore(min_score);
    }
    @Override
    public JSONArray getUserInfoBySecurityId(int security_id){
        JSONArray tmp=new JSONArray();
        tmp.addAll(superAdminDAO.getUserInfoBySecurityId(security_id));
        JSONObject jstmp1;
        String addr;
        JSONArray ans=new JSONArray();
        JSONObject anstmp=new JSONObject();
        for(int i=0;i<tmp.size();i++){
            jstmp1=tmp.getJSONObject(i);
            //idtmp=jstmp1.getInteger("user_id");
            addr=getAddressInfo(jstmp1.getInteger("id_address_id"));
            anstmp.put("user_id",jstmp1.getInteger("user_id"));
            anstmp.put("name",jstmp1.getString("name"));
            anstmp.put("id_num",jstmp1.getString("id_number"));
            anstmp.put("contact_address",addr);
            anstmp.put("apply_time",jstmp1.getSqlDate("create_time"));
            anstmp.put("audio_time",jstmp1.getSqlDate("update_time"));
            ans.add(anstmp);
            anstmp=new JSONObject();
        }
        return ans;
    }
    @Override
    public JSONArray getUserInfoByUserName(String name){
        String name_like="%"+name+"%";
        JSONArray tmp=new JSONArray();
        tmp.addAll(superAdminDAO.getUserInfoByUserName(name_like));
        JSONObject jstmp1;
        String addr;
        JSONArray ans=new JSONArray();
        JSONObject anstmp=new JSONObject();
        for(int i=0;i<tmp.size();i++){
            jstmp1=tmp.getJSONObject(i);
            //idtmp=jstmp1.getInteger("user_id");
            addr=getAddressInfo(jstmp1.getInteger("id_address_id"));
            anstmp.put("user_id",jstmp1.getInteger("user_id"));
            anstmp.put("name",jstmp1.getString("name"));
            anstmp.put("id_num",jstmp1.getString("id_number"));
            anstmp.put("contact_address",addr);
            anstmp.put("apply_time",jstmp1.getSqlDate("create_time"));
            anstmp.put("audio_time",jstmp1.getSqlDate("update_time"));
            ans.add(anstmp);
            anstmp=new JSONObject();
        }
        return ans;
    }
}
