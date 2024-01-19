package com.lygin.beekeepersshopapi.services;

import com.lygin.beekeepersshopapi.entity.Coupon;

import java.util.List;

public interface CouponService {
    Coupon create(Coupon coupon);
    List<Coupon> getAll();
}
