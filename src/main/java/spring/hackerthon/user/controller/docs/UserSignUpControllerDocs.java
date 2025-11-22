package spring.hackerthon.user.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.user.dto.UserSignUpReq;
import spring.hackerthon.user.dto.UserSignUpRes;

public interface UserSignUpControllerDocs {

    @Operation(
            summary = "회원가입 API",
            description = "사용자가 회원가입을 진행합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "회원가입 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "이미 존재하는 회원"
            )
    })
    ApiResponse<UserSignUpRes> signup(UserSignUpReq request);
}
