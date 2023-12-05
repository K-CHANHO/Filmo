package com.movie.test.complaint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "complaint")
public class ComplaintEntity {

    @Id
    private String complaintId;

    @Column
    private String complaintUser;

    @Column
    private String getComplaintReportId;

}
