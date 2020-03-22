package com.copy.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PageInfo {
    @ApiModelProperty(value = "总页数")
    private long total;
    @ApiModelProperty(value = "每页的大小")
    private long pageSize;
    @ApiModelProperty(value = "当前页码")
    private long pageNum;
}
