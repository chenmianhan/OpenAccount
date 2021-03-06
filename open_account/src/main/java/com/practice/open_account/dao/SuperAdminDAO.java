package com.practice.open_account.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.repository.query.Param;

public interface SuperAdminDAO {
    int getSecurityUnderAdmin(@Param("admin_id") int admin_id);
    // return security_id

    int getMaxId();
    // return max employee_id from employee

    void addAdminToEmployee(@Param("employee_id") int employee_id,
                            @Param("name") String name,
                            @Param("account") String account,
                            @Param("password") String password);

    void addAdminToAdminManager(@Param("employee_id") int employee_id,
                                @Param("store") int store);

    int modifyAdmin(@Param("employee_id") int employee_id,
                    @Param("name") String name,
                    @Param("password") String password,
                    @Param("account") String account);
    // return 1 as successful 0 otherwise

//    int addStore();
    // Aborting
    // return 1 as successful 0 otherwise

    int getUsersNumUnderStore(@Param("store") int store);
    // return number of users under store
    List<Integer> getAdminUnderStore(@Param("store") int store);
    List<Integer> getAuditorUnderStore(@Param("store") int store);

    int deleteStoreFromSecurity(@Param("store") int store);
    int deleteStoreFromAdminManager(@Param("store") int store);
    int deleteStoreFromAuditorManager(@Param("store") int store);
    int deleteEmployee(@Param("employee_id") int employee_id);
    // return 1 as successful 0 otherwise

    String getCustomerId(@Param("user_id") int user_id);

    int deleteUserInAccountInfo(@Param("user_id") int user_id);
    int deleteUserInCustomerAccount(@Param("user_id") int user_id);
    int deleteUserInFundAccount(@Param("customer_id") String customer_id);
    int deleteUserInTradeAccount(@Param("customer_id") String customer_id);
    int deleteUserInUser(@Param("user_id") int user_id);
    int deleteCurrencyUnderFund(@Param("fund_id") String fund_id);
    // return 1 as successful 0 otherwise

    int deleteAdminInAdminManager(@Param("admin_id") int admin_id);

    List<JSONObject> getStore(@Param("store") String store);
    List<JSONObject> getAllStore();

    JSONObject getAdminByStore(@Param("security_id") int security_id);
    List<JSONObject> getAdminByName(@Param("admin_name") String admin_name);
    List<JSONObject> getAllAdmin();
    List<JSONObject> getAllReviewers(@Param("security_id") int security_id);

    String getSecurity(@Param("security_id") int security_id);
    String getSuperAdminName();

    List<JSONObject> getUserList(int security_id);
    JSONObject getUserInfo(int usrId);
    JSONObject getAddressInfo(int aId);
    JSONObject getPhoneAndTime(int usrId);

    int changeMaxAuditorNum(@Param("max_num") int max_num);
    int changeExpireDate(@Param("expire_date") java.sql.Date expire_date);
    int changeMinScore(@Param("min_score") int min_score);

    List<JSONObject> getUserInfoBySecurityId(int seId);
    JSONObject getApplyAndReviewTime(int usrId);
    List<JSONObject> getUserInfoByUserName(String userName);

    JSONObject getAuditorNum();
    JSONObject getExpireDate();
    JSONObject getMinScore();

    int getAuthority(@Param("admin_id") int admin_id);
    JSONObject getReviewerInfo(@Param("reviewerId") int reviewerId);

    int getSecurityIdByAdminId(@Param("admin_id") int admin_id);
}
