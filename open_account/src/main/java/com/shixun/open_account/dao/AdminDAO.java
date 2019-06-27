package com.shixun.open_account.dao;

import java.util.ArrayList;
import java.util.List;

public interface AdminDAO {
	List<Integer> getSecurityIdByAdminId(Integer admin_id);
}
