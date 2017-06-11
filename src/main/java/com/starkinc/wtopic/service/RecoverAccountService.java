package com.starkinc.wtopic.service;

import com.starkinc.wtopic.dto.ResetPasswordDTO;

public interface RecoverAccountService {
	
	public String getSecurityQuestion(String username);
	public boolean recoverAccount(String username, ResetPasswordDTO resetPasswordDTO);

}
