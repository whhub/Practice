package com.mj.scada.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-19 11:42
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "maintain_event")
public class MaintainEvent
{
    @Id
    @GeneratedValue
    private Integer id;
    private String color;
    private String msg;
}
