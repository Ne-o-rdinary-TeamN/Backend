package spring.hackerthon.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.global.security.JwtTokenProvider;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.user.dto.UserLoginReq;
import spring.hackerthon.user.dto.UserLoginRes;
import spring.hackerthon.user.dto.UserSignUpReq;
import spring.hackerthon.user.dto.UserSignUpRes;
import spring.hackerthon.user.repository.UserRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     * @param request
     * @return
     */
    public UserSignUpRes signup(UserSignUpReq request) {
        //이미 존재하는 아이디 확인
        if(userRepository.existsByUserId(request.userId())) {
            throw new GeneralHandler(ErrorStatus.SAME_USERID);
        }

        User user = User.builder()
                .userId(request.userId())
                .name(request.name())
                .userPw(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
        return UserSignUpRes.builder().success(true).build();
    }

    /**
     * 로그인
     * @param request
     * @return
     */
    public UserLoginRes login(UserLoginReq request) {
        User user = userRepository.findUserByUserId(request.userId()).orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        //비밀번호 일치 여부 확인
        if(!passwordEncoder.matches(request.password(), user.getUserPw())) {
            throw new GeneralHandler(ErrorStatus.USER_NOT_FOUND);
        }

        //jwt 발급
        String roleName = "USER";
        List<String> roles = Collections.singletonList("ROLE_" + roleName);

        Map<String, Object> claims = new HashMap<>(6);
        claims.put("userPk", user.getUserPk());
        claims.put("userId", user.getUserId());
        claims.put("userName", user.getName());
        claims.put("roles", roles);

        String newAccess = jwtTokenProvider.createAccessToken(user.getUserId(), claims);

        return UserLoginRes
                .builder()
                .tokenType("Bearer")
                .accessToken(newAccess)
                .expiresIn(jwtTokenProvider.getAccessValidityMs())
                .userPk(user.getUserPk())
                .build();
    }
}
