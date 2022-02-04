package com.flightapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.admin.model.Schedules;

public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {

}
