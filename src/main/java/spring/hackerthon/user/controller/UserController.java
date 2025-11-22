package spring.hackerthon.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.hackerthon.user.dto.UserLoginReq;
import spring.hackerthon.user.dto.UserLoginRes;
import spring.hackerthon.user.dto.UserSignUpReq;
import spring.hackerthon.user.dto.UserSignUpRes;
import spring.hackerthon.user.service.UserService;

@Tag(name="인증/인가 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/signup")
    public UserSignUpRes signup(@RequestBody UserSignUpReq userSignUpReq) {
        return userService.signup(userSignUpReq);
    }

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public UserLoginRes login(@RequestBody UserLoginReq userLoginReq) {
        return userService.login(userLoginReq);
    }
}
