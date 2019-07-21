package com.kavi.user.dao;

import java.util.List;
import java.util.Map;

public interface UserService {

	List<Map<String, Object>> findByUserName(String userName);

}
