CREATE TABLE USER_REQUEST
(
    ID               BIGINT       PRIMARY KEY  NOT NULL,
    LOGIN            VARCHAR(255) NOT NULL,
    REQUEST_COUNT    INTEGER      NOT NULL,
);

CREATE SEQUENCE SEQ_USER;