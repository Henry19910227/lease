package com.atguigu.lease.web.admin.mapper;

import com.atguigu.lease.model.entity.FacilityInfo;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.web.admin.vo.facility.FacilityQueryParam;
import com.atguigu.lease.web.admin.vo.fee.FeeValueQueryParam;
import com.atguigu.lease.web.admin.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.FeeValue
*/
@Mapper
public interface FeeValueMapper extends BaseMapper<FeeValue> {
    List<FeeValueVo> feeValueVoList(FeeValueQueryParam param);
}




