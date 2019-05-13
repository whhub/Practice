package com.zhhe.lianying_project.bean.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 16:00
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "sdd_shutdown_event")
public class ShutdownEvent
{
    @Id
    private Long id;
    private String event;

    public ShutdownEvent()
    {
    }

    public ShutdownEvent(Long id, String event)
    {
        this.id = id;
        this.event = event;
    }
}
