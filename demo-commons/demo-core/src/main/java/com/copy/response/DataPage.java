package com.copy.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

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
class DataPage<T> {
    @ApiModelProperty(value = "列表信息")
    private Collection<T> list;
    @ApiModelProperty(value = "分页信息")
    private PageInfo page;
}
