package com.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog.entity.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer>{

}
