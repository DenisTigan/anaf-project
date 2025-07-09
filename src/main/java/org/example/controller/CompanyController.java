package org.example.controller;

import org.example.service.AnafApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private AnafApiService anafApiService;

    @GetMapping("/{cui}")
    public String getCompanyByCui(@PathVariable Long cui){
        return anafApiService.getCompanyInfo(cui);
    }
}
