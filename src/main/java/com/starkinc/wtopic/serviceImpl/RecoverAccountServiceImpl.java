package com.starkinc.wtopic.serviceImpl;

import static org.springframework.http.HttpStatus.FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import com.starkinc.wtopic.dto.ResetPasswordDTO;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.restClient.RecoverAccountClient;
import com.starkinc.wtopic.service.RecoverAccountService;

/**
 * @author RajaSushanth
 *
 */
@Service
public class RecoverAccountServiceImpl implements RecoverAccountService {
	
	private RecoverAccountClient recoverAccountClient;
	private TextEncryptor textEncryptor;

	@Override
	public String getSecurityQuestion(String username) {
		String question = null;
		ResponseEntity<TopicUser> entity = recoverAccountClient.getSecurityQuestion(username);
		if(entity.getStatusCode() == FOUND){
			TopicUser topicUser = entity.getBody();
			question = topicUser.getQuestion();
		}
		return question;
	}
	
	@Override
	public boolean recoverAccount(String username, ResetPasswordDTO resetPasswordDTO) {
		resetPasswordDTO.setPassword(textEncryptor.encrypt(resetPasswordDTO.getPassword()));
		ResponseEntity<Boolean> entity = recoverAccountClient.recoverAccount(username, resetPasswordDTO);
		return entity.getBody() == null ? false : entity.getBody();
	}

	@Autowired
	public void setRecoverAccountClient(RecoverAccountClient recoverAccountClient) {
		this.recoverAccountClient = recoverAccountClient;
	}

	@Autowired
	public void setTextEncryptor(TextEncryptor textEncryptor) {
		this.textEncryptor = textEncryptor;
	}
	
	


}
