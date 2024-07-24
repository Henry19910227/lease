package com.atguigu.lease.web.admin.vo.label;

import com.atguigu.lease.model.entity.LabelInfo;
import lombok.Data;

@Data
public class LabelQueryParam extends LabelInfo {
    private long apartmentId;
}
