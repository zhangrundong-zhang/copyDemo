package com.copy.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用dataDto
 *
 * @param <T> 数据类型
 * @author zhongyong
 * @date 20200113
 * @since v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class DataObj<T> {
    @ApiModelProperty(value = "对象信息")
    private T obj;
}
