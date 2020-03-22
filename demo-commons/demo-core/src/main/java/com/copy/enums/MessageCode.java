package com.copy.enums;


/**
 * 返回信息枚举类
 *
 * @author 张润东
 * @date 20200322
 * @since v1.0
 */
public enum MessageCode {
    /**
     * 统一响应编码，这些不可以修改
     */
    SUCCESS(1, "成功"),
    /**
     * ERROR 描述此枚举常量
     */
    ERROR(0, "失败"),
    /**
     * ERR101 描述此枚举常量
     */
    ERR101(101, "未登录或登录超时"),
    /**
     * ERR102 描述此枚举常量
     */
    ERR102(102, "无权限访问该接口"),
    /**
     * ERR103 描述此枚举常量
     */
    ERR103(103, "预留错误码"),
    /**
     * ERR104 描述此枚举常量
     */
    ERR104(104, "预留错误码"),
    /**
     * ERR105 描述此枚举常量
     */
    ERR105(105, "系统出错"),


    /**
     * 本系统自定义响应错误码
     */
    ERR201(201, "文件大小超出限制"),
    /**
     * ERR202 描述此枚举常量
     */
    ERR202(202, "参数不正确");


    /**
     * code 描述此字段
     */
    private Integer code;
    /**
     * msg 描述此字段
     */
    private String msg;

    /**
     * 描述该构造方法实现的功能
     *
     * @param code 描述此参数
     * @param msg  描述此参数
     * @author 张润东
     * @date 20200322
     * @since v1.0
     */
    MessageCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取 code.
     *
     * @return 返回 code
     * @author 张润东
     * @date 20200322
     * @since v1.0
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置 code.
     *
     * @param code 描述此参数
     * @author 张润东
     * @date 20200322
     * @since v1.0
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取 msg.
     *
     * @return 返回 msg
     * @author 张润东
     * @date 20200322
     * @since v1.0
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置 msg.
     *
     * @param msg 描述此参数
     * @author 张润东
     * @date 20200322
     * @since v1.0
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
