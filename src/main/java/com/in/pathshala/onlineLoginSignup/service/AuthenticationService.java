package com.in.pathshala.onlineLoginSignup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineLoginSignup.config.MessageStrings;
import com.in.pathshala.onlineLoginSignup.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineLoginSignup.model.AuthenticationToken;
import com.in.pathshala.onlineLoginSignup.model.User;
import com.in.pathshala.onlineLoginSignup.repository.TokenRepository;
import com.in.pathshala.onlineLoginSignup.utils.Helper;

@Service
public class AuthenticationService {
	

	@Autowired
	TokenRepository tokenRepository;
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}
	public AuthenticationToken getToken(User user) {
		// TODO Auto-generated method stub
		return tokenRepository.findByUser(user);
	}
	 public void authenticate(String token) throws AuthenticationFailException {
	        if (!Helper.notNull(token)) {
	            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
	        }
	        if (!Helper.notNull(getUser(token))) {
	            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
	        }
	    }
//	private Object getUser(String token) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	private Object  getUser(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

}
