package com.gaoming.rtz.querydsl.service;

import com.gaoming.rtz.querydsl.dao.entity.SysRoleDo;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.gaoming.rtz.querydsl.dao.query.QSysRole.sysRole;

/**
 * @file: IndexService
 * @author: gaoming
 * @date: 2021/05/12
 * @version: 1.0
 * @description:
 **/
@Service
public class IndexService {

    @Resource(name = "sqlQueryFactory")
    private SQLQueryFactory sqlQueryFactory;

    @Transactional
    public String query() {
        List<SysRoleDo> fetch = sqlQueryFactory.selectFrom(sysRole).fetch();
        List<Integer> fetch1 = sqlQueryFactory.selectOne().from(sysRole).fetch();
        System.out.println(fetch1.toString());
        return fetch.stream().map(SysRoleDo::getRoleName).collect(Collectors.joining(","));
    }
}
