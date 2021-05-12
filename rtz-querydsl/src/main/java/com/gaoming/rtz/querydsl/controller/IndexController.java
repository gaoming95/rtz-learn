package com.gaoming.rtz.querydsl.controller;

import com.gaoming.rtz.querydsl.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @file: IndexController
 * @author: gaoming
 * @date: 2021/05/12
 * @version: 1.0
 * @description:
 **/
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return indexService.query();
    }
}
