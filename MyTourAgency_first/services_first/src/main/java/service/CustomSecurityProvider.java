package service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface CustomSecurityProvider {Authentication authenticate(String userName) throws AuthenticationException;

}
