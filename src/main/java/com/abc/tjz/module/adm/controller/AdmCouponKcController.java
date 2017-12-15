package com.abc.tjz.module.adm.controller;

import com.abc.tjz.entity.CouponKc;
import com.abc.tjz.module.adm.dto.CouponKcCondiDto;
import com.abc.tjz.module.adm.service.AdmCouponKcService;
import com.abc.tjz.util.dto.ResultDto;
import com.abc.tjz.util.misc.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/adm/couponkc")
public class AdmCouponKcController extends AbstractController {

    @Autowired
    private AdmCouponKcService cxService;

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/showEdit")
    public ModelAndView showEdit() {
        return new ModelAndView(this.getViewRoot() + "/edit");
    }

    @GetMapping("/getCoupons")
    public ResultDto<Page<CouponKc>> getCoupons(CouponKcCondiDto condi,
                                                @PageableDefault(size = 50, sort = "updateDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResultDto.succeed("", rf.getCouponKcRepo().findAll(condi.toSpecification(), pageable));
    }

    @GetMapping("/getCoupon")
    public ResultDto<CouponKc> getCoupon(String id) {
        return ResultDto.succeed("", rf.getCouponKcRepo().findOne(id));
    }

    @PostMapping("/edit")
    public ResultDto<Void> edit(CouponKc form) {
        if (StringUtils.isBlank(form.getId())) {
            return this.cxService.add(form);
        } else {
            return this.cxService.update(form);
        }
    }

    @PostMapping("/deleteCoupons")
    public ResultDto<Void> deleteCoupons(@RequestParam List<String> ids) {
        rf.getCouponKcRepo().updateByIds(ids, "isDeleted", true);
        return ResultDto.SUCCESS;
    }
}
