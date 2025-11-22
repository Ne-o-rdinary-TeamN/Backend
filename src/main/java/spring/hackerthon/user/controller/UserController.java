package spring.hackerthon.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.user.dto.*;
import spring.hackerthon.user.service.UserService;

@Tag(name="인증/인가 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<UserSignUpRes> signup(@RequestBody UserSignUpReq userSignUpReq) {
        return ApiResponse.onSuccess(userService.signup(userSignUpReq));
    }

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ApiResponse<UserLoginRes> login(@RequestBody UserLoginReq userLoginReq) {
        return ApiResponse.onSuccess(userService.login(userLoginReq));
    }

    @Operation(summary = "내 정보 조회", description = "내 정보 조회")
    @GetMapping("/check")
    public ApiResponse<UserCheckRes> check(@AuthenticationPrincipal JwtPrincipal user) {
        if(user == null) {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

        return ApiResponse.onSuccess(userService.check(user));
    }
}
