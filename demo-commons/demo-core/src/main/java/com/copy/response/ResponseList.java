package com.copy.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 列表响应类
 *
 * @param <T> 列表类型
 * @author zhongyong
 * @date 20200114
 * @since v1.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "列表响应信息", value = "ResponseList")
public class ResponseList<T> extends ResponseBase {
    @ApiModelProperty(value = "响应数据")
    private DataList<T> data;

    ResponseList(Integer code, String msg) {
        super(code, msg);
    }
}
