package com.svs.support.time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public final class ServerTime {
    // 스테이틱으로 관리할(= 객체 안 만들고 클래스 이름에서 바로 접근할) 메서드라서 이렇게 했어요.
    // 어차피 객체 만들어서 싱글톤으로 쓰나 이렇게 쓰나 얘넨 별 차이가 없어서요 넵!
    public static OffsetDateTime now() {
        return OffsetDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public static LocalDateTime nowLocalDateTime() {
        return now().toLocalDateTime();
    }

    // 얘는 어차피 객체 안 만들 거니까 이렇게 한 거고
    private ServerTime() {} // No Constructor Allowed
}
