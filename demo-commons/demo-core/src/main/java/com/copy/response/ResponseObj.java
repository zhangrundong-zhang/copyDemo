package com.copy.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 对象响应类
 *
 * @param <T> 对象响应类型
 * @author zhongyong
 * @date 20200114
 * @since v1.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "对象响应信息", value = "ResponseObj")
public class ResponseObj<T> extends ResponseBase {
    @ApiModelProperty(value = "响应数据")
    private DataObj<T> data;

    ResponseObj(Integer code, String msg) {
        super(code, msg);
    }
}
