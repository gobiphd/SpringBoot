package com.kavi.user.bo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.user.impl.UserServiceImpl;
import com.kavi.user.security.SaltUtils;
import com.kavi.user.vo.LoginVO;
import com.kavi.user.vo.UserVO;

@Component
public class UserBO {

	@Autowired
	private UserServiceImpl userServiceImpl;

	public UserVO validateUserCredential(LoginVO loginVO) {
		UserVO userVO = null;
		List<Map<String, Object>> listOfUsers = null;
		try {
			listOfUsers = userServiceImpl.findByUserName(loginVO.getUserName());

			if (listOfUsers != null && listOfUsers.size() > 0) {
				Map<String, Object> mapObject = listOfUsers.get(0);
				String encryptPwd = (String)mapObject.get("password");

				if(isUserValidated(loginVO.getUserName(), loginVO.getPwd(), encryptPwd)) {
					userVO = fillUserDetails(mapObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}

	private UserVO fillUserDetails(Map<String, Object> mapObject) {
		UserVO userVO = new UserVO();

		if (mapObject != null && mapObject.size() > 0) {
			userVO.setUserId(((BigDecimal)mapObject.get("userid")).longValue());
			userVO.setUserName((String)mapObject.get("username"));
			userVO.setFirstName((String)mapObject.get("firstname"));
			userVO.setLastName((String)mapObject.get("lastname"));
			userVO.setEmail((String)mapObject.get("email"));
		}
		return userVO;
	}

	private boolean isUserValidated(String userName, String pwd, String encryptPwd) {
		boolean isUserValidate = false;
		if (userName != null && !userName.isEmpty() && pwd != null && !pwd.isEmpty() && encryptPwd != null && !encryptPwd.isEmpty()) {
			String regularPwd = userName.toUpperCase() + pwd;
			isUserValidate = SaltUtils.authenticate(regularPwd, encryptPwd);
		}
		return isUserValidate;
	}

}
