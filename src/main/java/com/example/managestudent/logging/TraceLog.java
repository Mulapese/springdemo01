package com.example.managestudent.logging;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@Table(name = "trace_log")
@NoArgsConstructor
public class TraceLog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int traceId;

    private String method;

    private String uri;

    private int responseStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timestamp;

    public TraceLog(String method, String uri, int responseStatus, Calendar timestamp) {
        this.method = method;
        this.uri = uri;
        this.responseStatus = responseStatus;
        this.timestamp = timestamp;
    }
}
