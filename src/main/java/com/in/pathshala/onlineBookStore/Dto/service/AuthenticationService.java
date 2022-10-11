package com.in.pathshala.onlineBookStore.Dto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineBookStore.Dto.model.AuthenticationToken;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.TokenRepository;
import com.in.pathshala.onlineBookStore.Dto.utils.Helper;
import com.in.pathshala.onlineBookStore.config.MessageStrings;


@Service
public class AuthenticationService {

	@Autowired
	TokenRepository tokenRepository;
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}
	public AuthenticationToken getToken(Seller seller) {
		// TODO Auto-generated method stub
		return tokenRepository.findBySeller(seller);
	}
	public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getSeller(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }
	private Object  getSeller(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getSeller())) {
                return authenticationToken.getSeller();
            }
        }
        return null;
    }
}
