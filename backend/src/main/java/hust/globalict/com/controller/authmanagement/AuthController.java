package hust.globalict.com.controller.authmanagement;


import hust.globalict.com.repository.UserRepository;
import hust.globalict.com.utils.Constants;
import hust.globalict.com.utils.common.ResponseUtil;
import hust.globalict.com.utils.common.StringUtils;
import hust.globalict.com.vo.request.LoginRequest;
import hust.globalict.com.vo.response.CommonResponse;
import hust.globalict.com.vo.response.JwtResponseVO;
import jakarta.validation.Valid;
import hust.globalict.com.configuration.exception.InvalidInputRequestException;
import hust.globalict.com.configuration.security.UserDetailsImpl;
import hust.globalict.com.configuration.security.jwt.JwtUtils;
import hust.globalict.com.utils.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.API_VERSION + "/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        CommonResponse<JwtResponseVO> result = new CommonResponse<>();

        try {
            if (StringUtils.isEmpty(loginRequest.getUsername())) {
                result.setMessage(Translator.toLocale("msg_login_username_missing"));
                return ResponseUtil.setResponseData(result.getStatusCode(), result.getData(), result.getMessage());
            }
            if (StringUtils.isEmpty(loginRequest.getPassword())) {
                result.setMessage(Translator.toLocale("msg_login_pwd_missing"));
                return ResponseUtil.setResponseData(result.getStatusCode(), result.getData(), result.getMessage());
            }

            // Continue with authentication and generate JWT token
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            JwtResponseVO jwtResponseVO = new JwtResponseVO(jwt, userDetails.getId(), userDetails.getUsername(),
                    userDetails.getEmail(), roles);
            return ResponseUtil.setResponseData(HttpStatus.OK.value(), jwtResponseVO,
                    Translator.toLocale("msg_success"));
        } catch (Exception e) {
            throw new InvalidInputRequestException("Login ko họp lệ");
        }

    }

}
