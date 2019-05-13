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
 *Date:2019-03-05 15:34
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "sdd_startup_event")
public class StartupEvent
{
    @Id
    private Long id;
    private String event;

    public StartupEvent()
    {
    }

    public StartupEvent(Long id, String event)
    {
        this.id = id;
        this.event = event;
    }
}
