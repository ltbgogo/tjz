package com.abc.tjz.module.adm.controller;

import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.module.adm.dto.CouponTbCondiDto;
import com.abc.tjz.module.adm.service.AdmCouponTbService;
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
@RequestMapping("/action/adm/coupontb")
public class AdmCouponTbController extends AbstractController {

    @Autowired
    private AdmCouponTbService cxService;

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/showEdit")
    public ModelAndView showEdit() {
        return new ModelAndView(this.getViewRoot() + "/edit");
    }

    @GetMapping("/getCoupons")
    public ResultDto<Page<CouponTb>> getCoupons(CouponTbCondiDto condi,
                                                @PageableDefault(size = 50, sort = "updateDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResultDto.succeed("", rf.getCouponTbRepo().findAll(condi.toSpecification(), pageable));
    }

    @GetMapping("/getCoupon")
    public ResultDto<CouponTb> getCoupon(String id) {
        return ResultDto.succeed("", rf.getCouponTbRepo().findOne(id));
    }

    @PostMapping("/edit")
    public ResultDto<Void> edit(CouponTb form) {
        if (StringUtils.isBlank(form.getId())) {
            return this.cxService.add(form);
        } else {
            return this.cxService.update(form);
        }
    }

    @PostMapping("/deleteCoupons")
    public ResultDto<Void> deleteCoupons(@RequestParam List<String> ids) {
        rf.getCouponTbRepo().updateByIds(ids, "isDeleted", true);
        return ResultDto.SUCCESS;
    }
}
