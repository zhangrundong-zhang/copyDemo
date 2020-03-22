package com.copy.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分页响应类
 *
 * @param <T> 分页响应类型
 * @author zhongyong
 * @date 20200114
 * @since v1.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "分页响应信息", value = "ResponsePage")
public class ResponsePage<T> extends ResponseBase {
    @ApiModelProperty(value = "响应数据")
    private DataPage<T> data;

    ResponsePage(Integer code, String msg) {
        super(code, msg);
    }
}
