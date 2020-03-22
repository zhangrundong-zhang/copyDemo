package com.copy.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.copy.enums.MessageCode;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * 返回消息构造器
 *
 * @author zhongyong
 * @date 20200114
 * @since v1.0
 */
public class ResponseBuilder {

    public static ResponseBase buildErrorMsg(String msg) {
        return new ResponseBase(MessageCode.ERROR.getCode(), msg);
    }

    public static ResponseBase buildErrorMsg(MessageCode msg) {
        return new ResponseBase(msg.getCode(), msg.getMsg());
    }

    public static ResponseBase buildSuccessMsg() {
        return new ResponseBase(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMsg());
    }

    public static ResponseBase buildSuccessMsg(String msg) {
        return new ResponseBase(MessageCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseObj<T> buildObj(T obj) {
        ResponseObj<T> response = new ResponseObj<>(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMsg());
        DataObj<T> data = new DataObj<>(obj);
        response.setData(data);
        return response;
    }

    public static <T> ResponseList<T> buildList(Collection<T> list) {
        ResponseList<T> response = new ResponseList<>(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMsg());
        DataList<T> data = new DataList<>(list);
        response.setData(data);
        return response;
    }

    public static <T> ResponsePage<T> buildPage(Page<T> page) {
        ResponsePage<T> response = new ResponsePage<>(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMsg());
        DataPage<T> data = new DataPage<>();

        // 分页信息
        PageInfo pageInfo = new PageInfo(page.getTotal(), page.getSize(), page.getCurrent());
        data.setPage(pageInfo);

        // 分页数据
        if (CollectionUtils.isEmpty(page.getRecords())) {
            data.setList(Collections.emptyList());
        } else {
            data.setList(page.getRecords());
        }
        response.setData(data);
        return response;
    }
}
