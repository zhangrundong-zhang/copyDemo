package com.copy.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "基础响应信息", value = "ResponseBase")
public class ResponseBase {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码，0成功，非0失败")
    private Integer code;
    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String msg;
}
