package com.example.managestudent.logging;

import com.example.managestudent.logging.TraceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITraceLogRepository  extends JpaRepository<TraceLog, Integer> {
}
