package spring.hackerthon.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {
    SOCIAL("사회"),
    POLITICS("정치"),
    ECONOMY("경제"),
    LOVE("연애"),
    HOT("핫이슈");

    private final String displayName;

    public static Category toCategory(String displayName) {
        return Arrays.stream(values())
                .filter(c -> c.displayName.equals(displayName))
                .findAny()
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.CATEGORY_NOT_FOUND));
    }
}
