package com.atguigu.lease.web.admin.vo.fee;

import com.atguigu.lease.model.entity.FeeValue;
import lombok.Data;

@Data
public class FeeValueQueryParam extends FeeValue {
    private long apartmentId;
}
