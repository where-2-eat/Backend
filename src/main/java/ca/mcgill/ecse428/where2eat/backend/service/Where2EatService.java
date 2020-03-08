package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
import ca.mcgill.ecse428.where2eat.backend.exception.CustomException;
import ca.mcgill.ecse428.where2eat.backend.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class Where2EatService {
    @Autowired
    private LoginRepository loginRepository;

//  @Autowired
//  private UserGroupRepository userGroupRepository;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserSystemRepository userSystemRepository;

    public List<String> blackList = new ArrayList<>();

    // ==========================================================================================
    // LOGIN operations
    // ==========================================================================================


    public List<String> getBlackList() {
        return blackList;
    }

    @Transactional
    public boolean logout(String token) throws Exception {
        blackList.add(token);
        return true;
    }


    /**
     * login method with authentication
     *
     * @param username
     * @param password
     * @return JSON Web token for the login session
     */
    @Transactional
    public String login(String username, String password) {
        String error = "";
        if (username == null || username.trim().length() == 0) {
            error += "Login userName cannot be empty!";
        }
        if (password == null || password.trim().length() == 0) {
            error += "Login password cannot be empty!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        try {
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            //return jwtTokenProvider.createToken(username);		// token is being made using login
            Login foundLogin = loginRepository.findUserLoginByuserName(username);
            if (foundLogin != null && foundLogin.getPassword().equals(password))
                return "asfdk34vl320s.pqweok32fk13d.dpektgfdvko123";
            else throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}