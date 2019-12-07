package com.gopolangmathole.employeedirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopolangmathole.employeedirectory.entity.HttpServletRequestsLogging;

public interface EmployeeLoggingRepository extends JpaRepository<HttpServletRequestsLogging, Integer> {

}
