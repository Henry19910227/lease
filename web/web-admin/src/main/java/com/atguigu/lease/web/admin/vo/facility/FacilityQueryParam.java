package com.atguigu.lease.web.admin.vo.facility;

import com.atguigu.lease.model.entity.FacilityInfo;
import lombok.Data;


@Data
public class FacilityQueryParam extends FacilityInfo {
    private long apartmentId;
}
