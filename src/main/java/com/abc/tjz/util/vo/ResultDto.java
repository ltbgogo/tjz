package com.abc.tjz.util.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(staticName = "of")
public class ResultDto<T> {

    public static ResultDto<Void> SUCCESS = succeed(null);
    public static ResultDto<Void> FAILURE = fail(null);

    public static <T> ResultDto<T> succeed(String msg) {
        return of(msg, null);
    }

    public static <T> ResultDto<T> succeed(String msg, T data) {
        return of("success", StringUtils.defaultIfBlank(msg, "操作成功！"), data);
    }

    public static <T> ResultDto<T> fail(String msg) {
        return fail(msg, null);
    }

    public static <T> ResultDto<T> fail(String msg, T data) {
        return of("failure", StringUtils.defaultIfBlank(msg, "操作失败！"), data);
    }

    public static <T> ResultDto<T> of(String code) {
        return of(code, null);
    }

    public static <T> ResultDto<T> of(String code, String msg) {
        return of(code, msg, null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.is("success");
    }

    @JsonIgnore
    public boolean is(String code) {
        return StringUtils.equals(code, this.getCode());
    }

    private String code;
    private String msg;
    private T data;
}
