package com.mj.scada.bean.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-13 10:51
 */
@Entity
@Data
public class PermissionDo implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键.
    private String name;//名称.
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private Integer parentId; //父编号
    private Integer available; //是否可用
}
