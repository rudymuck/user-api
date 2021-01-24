package com.example.userapi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "USER_REQUEST")
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private static final String SEQUENCE_NAME = "SEQ_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "id")
    protected long id;

    @Column(name = "login")
    private String login;

    @Column(name = "request_count")
    private Integer requestCount;

    public void increaseRequestCount() {
        this.requestCount++;
    }
}
