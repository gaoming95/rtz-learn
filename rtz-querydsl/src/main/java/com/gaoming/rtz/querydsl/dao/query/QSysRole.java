package com.gaoming.rtz.querydsl.dao.query;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.gaoming.rtz.querydsl.dao.entity.SysRoleDo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSysRole is a Querydsl query type for SysRoleDo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSysRole extends com.querydsl.sql.RelationalPathBase<SysRoleDo> {

    private static final long serialVersionUID = -775500239;

    public static final QSysRole sysRole = new QSysRole("sys_role");

    public final NumberPath<Integer> eee = createNumber("eee", Integer.class);

    public final NumberPath<Integer> etxt = createNumber("etxt", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public final StringPath roleName = createString("roleName");

    public final com.querydsl.sql.PrimaryKey<SysRoleDo> primary = createPrimaryKey(roleId);

    public QSysRole(String variable) {
        super(SysRoleDo.class, forVariable(variable), "null", "sys_role");
        addMetadata();
    }

    public QSysRole(String variable, String schema, String table) {
        super(SysRoleDo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSysRole(String variable, String schema) {
        super(SysRoleDo.class, forVariable(variable), schema, "sys_role");
        addMetadata();
    }

    public QSysRole(Path<? extends SysRoleDo> path) {
        super(path.getType(), path.getMetadata(), "null", "sys_role");
        addMetadata();
    }

    public QSysRole(PathMetadata metadata) {
        super(SysRoleDo.class, metadata, "null", "sys_role");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(eee, ColumnMetadata.named("eee").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(etxt, ColumnMetadata.named("etxt").withIndex(3).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(roleId, ColumnMetadata.named("role_id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(roleName, ColumnMetadata.named("role_name").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
    }

}

