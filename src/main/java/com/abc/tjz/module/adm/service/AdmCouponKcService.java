package com.abc.tjz.module.adm.service;

import com.abc.tjz.entity.CouponKc;
import com.abc.tjz.util.dto.ResultDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.abc.tjz.repository.RepoFactory.rf;

@Service
public class AdmCouponKcService {

    public ResultDto<Void> add(CouponKc form) {
        CouponKc coupon = new CouponKc();
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponKcRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }

    public ResultDto<Void> update(CouponKc form) {
        CouponKc coupon = rf.getCouponKcRepo().findOne(form.getId());
        form.setIsDeleted(form.getIsDeleted() == null ? false : form.getIsDeleted());
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponKcRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }
}
