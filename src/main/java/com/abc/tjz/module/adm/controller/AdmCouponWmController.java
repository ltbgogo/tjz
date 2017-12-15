package com.abc.tjz.module.adm.controller;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponWm;
import com.abc.tjz.entity.CouponWm;
import com.abc.tjz.module.adm.dto.CouponWmCondiDto;
import com.abc.tjz.module.adm.service.AdmCouponWmService;
import com.abc.tjz.module.adm.dto.CouponWmCondiDto;
import com.abc.tjz.util.dto.ResultDto;
import com.abc.tjz.util.misc.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/adm/couponwm")
public class AdmCouponWmController extends AbstractController {

    @Autowired
    private AdmCouponWmService cxService;

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/showEdit")
    public ModelAndView showEdit() {
        return new ModelAndView(this.getViewRoot() + "/edit");
    }

    @GetMapping("/getCoupons")
    public ResultDto<Page<CouponWm>> getCoupons(CouponWmCondiDto condi,
                                                @PageableDefault(size = 50, sort = "updateDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResultDto.succeed("", rf.getCouponWmRepo().findAll(condi.toSpecification(), pageable));
    }

    @GetMapping("/getCoupon")
    public ResultDto<CouponWm> getCoupon(String id) {
        return ResultDto.succeed("", rf.getCouponWmRepo().findOne(id));
    }

    @PostMapping("/edit")
    public ResultDto<Void> edit(CouponWm form) {
        if (StringUtils.isBlank(form.getId())) {
            return this.cxService.add(form);
        } else {
            return this.cxService.update(form);
        }
    }

    @PostMapping("/deleteCoupons")
    public ResultDto<Void> deleteCoupons(@RequestParam List<String> ids) {
        rf.getCouponWmRepo().updateByIds(ids, "isDeleted", true);
        return ResultDto.SUCCESS;
    }
}
