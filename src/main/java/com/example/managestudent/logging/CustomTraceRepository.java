package com.example.managestudent.logging;

import com.example.managestudent.repository.IStudentCourseRepository;
import com.example.managestudent.repository.ITraceLogRepository;
import com.example.managestudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class CustomTraceRepository implements HttpTraceRepository {

    @Autowired
    ITraceLogRepository repository;

    AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

    @Override
    public List<HttpTrace> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    @Override
    public void add(HttpTrace trace) {
        String uri = trace.getRequest().getUri().toString();

        Instant timestamp = trace.getTimestamp();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        Calendar cal = GregorianCalendar.from(zdt);

        String method = trace.getRequest().getMethod();
        int status = trace.getResponse().getStatus();

        repository.save(new TraceLog(method, uri, status, cal));
    }
}