package com.abc.tjz.module.adm.service;

import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.util.dto.ResultDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.abc.tjz.repository.RepoFactory.rf;

@Service
public class AdmCouponCxService {

    public ResultDto<Void> add(CouponCx form) {
        CouponCx coupon = new CouponCx();
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponCxRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }

    public ResultDto<Void> update(CouponCx form) {
        CouponCx coupon = rf.getCouponCxRepo().findOne(form.getId());
        form.setIsDeleted(form.getIsDeleted() == null ? false : form.getIsDeleted());
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponCxRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }
}
