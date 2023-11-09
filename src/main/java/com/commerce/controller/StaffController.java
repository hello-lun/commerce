package com.commerce.controller;

import com.commerce.entity.Goods;
import com.commerce.entity.R;
import com.commerce.mapper.StaffMapper;
import com.commerce.service.impl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.commerce.entity.Staff;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    StaffServiceImpl staffService;

    @PostMapping("/delete")
    public R deleteStaff(@RequestBody Staff staff) {
        staffService.removeById(staff);
        return R.ok();
    }

    @GetMapping("/get")
    public R getStaff() {
        List<Staff> staffList = staffService.list();
        return R.ok(staffList);
    }

    @PostMapping("/add")
    public R addStaff(@RequestBody Staff staff) {
        staffService.save(staff);
        return R.ok();
    }

    @PostMapping("/edit")
    public R editStaff(@RequestBody Staff staff) {
        staffMapper.updateById(staff);
        return R.ok();
    }

}
