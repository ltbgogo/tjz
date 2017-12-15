package com.abc.tjz.module.adm.service;

import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.util.dto.ResultDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.abc.tjz.repository.RepoFactory.rf;

@Service
public class AdmCouponTbService {

    public ResultDto<Void> add(CouponTb form) {
        CouponTb coupon = new CouponTb();
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponTbRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }

    public ResultDto<Void> update(CouponTb form) {
        CouponTb coupon = rf.getCouponTbRepo().findOne(form.getId());
        form.setIsDeleted(form.getIsDeleted() == null ? false : form.getIsDeleted());
        BeanUtils.copyProperties(form, coupon, "id", "createDateTime", "updateDateTime");
        rf.getCouponTbRepo().saveAndFlush(coupon);
        return ResultDto.SUCCESS;
    }
}
