package spring.hackerthon.user.controller.docs;

import io.swagger.v3.oas.annotations.Operation;

import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.user.dto.UserLoginReq;
import spring.hackerthon.user.dto.UserLoginRes;

public interface UserLoginControllerDocs {

    @Operation(
            summary = "로그인 API",
            description = "사용자가 아이디와 비밀번호로 로그인합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "요청 값 오류"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "아이디 또는 비밀번호 불일치"
            )
    })
    ApiResponse<UserLoginRes> login(UserLoginReq request);
}
