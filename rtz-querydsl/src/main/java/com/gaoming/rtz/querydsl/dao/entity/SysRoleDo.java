package com.gaoming.rtz.querydsl.dao.entity;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * SysRoleDo is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class SysRoleDo implements Serializable {

    private Integer eee;

    private Integer etxt;

    private Integer roleId;

    private String roleName;

    public Integer getEee() {
        return eee;
    }

    public void setEee(Integer eee) {
        this.eee = eee;
    }

    public Integer getEtxt() {
        return etxt;
    }

    public void setEtxt(Integer etxt) {
        this.etxt = etxt;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
         return "eee = " + eee + ", etxt = " + etxt + ", roleId = " + roleId + ", roleName = " + roleName;
    }

}

