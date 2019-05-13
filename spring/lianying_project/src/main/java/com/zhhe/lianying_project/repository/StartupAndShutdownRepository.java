package com.zhhe.lianying_project.repository;

import com.zhhe.lianying_project.bean.entity.StartupAndShutdown;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-12 14:19
 */
public interface StartupAndShutdownRepository extends JpaRepository<StartupAndShutdown,Integer>
{
}
