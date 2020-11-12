package com.example.managestudent.repository;

import com.example.managestudent.logging.TraceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITraceLogRepository  extends JpaRepository<TraceLog, Integer> {
//    List<TraceLog> findAll();
}
