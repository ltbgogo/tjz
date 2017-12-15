package com.abc.tjz.module.adm.service;

import com.abc.tjz.entity.CouponWm;
import com.abc.tjz.util.dto.ResultDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.abc.tjz.repository.RepoFactory.rf;

@Service
public class AdmCouponWmService {

    public ResultDto<Void> add(CouponWm form) {
        CouponWm coupon = new CouponWm();
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponWmRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }

    public ResultDto<Void> update(CouponWm form) {
        CouponWm coupon = rf.getCouponWmRepo().findOne(form.getId());
        form.setIsDeleted(form.getIsDeleted() == null ? false : form.getIsDeleted());
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponWmRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }
}
