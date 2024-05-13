CREATE TABLE IF NOT EXISTS competences (
  id smallint NOT NULL AUTO_INCREMENT,
  competence varchar(255) NOT NULL,
  tenant_id smallint NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS users (
                                     id binary(16) NOT NULL,
                                     dni varchar(255) DEFAULT NULL,
                                     email varchar(255) NOT NULL,
                                     is_active bit(1) NOT NULL,
                                     lastname varchar(255) DEFAULT NULL,
                                     name varchar(255) NOT NULL,
                                     password varchar(255) NOT NULL,
                                     phone varchar(255) DEFAULT NULL,
                                     professional_card_number varchar(255) DEFAULT NULL,
                                     regional_id smallint DEFAULT NULL,
                                     sign tinyblob,
                                     tenant_id varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (id),
                                     UNIQUE KEY UK_6dotkott2kjsp8vw4d0m25fb7 (email),
                                     UNIQUE KEY UK_6aphui3g30h49muho4c91n0yl (dni),
                                     UNIQUE KEY UK_11a4wnecag3err6aft46k1okw (professional_card_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS roles (
                                       id bigint NOT NULL AUTO_INCREMENT,
                                       rol enum('ADMIN','USERS_ADMIN','CUSTOMER','CONSTRUCTOR','COMMERCIAL_ADVISOR','REGIONAL_DIRECTOR','TECHNICAL_DIRECTOR','INSPECTOR','SCHEDULE_PROGRAMMER') NOT NULL,
                                       tenant_id smallint NOT NULL,
                                       user_id binary(16) DEFAULT NULL,
                                       PRIMARY KEY (id),
                                       KEY FK97mxvrajhkq19dmvboprimeg1 (user_id),
                                       CONSTRAINT FK97mxvrajhkq19dmvboprimeg1 FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS user_competences (
                                                  user_id binary(16) NOT NULL,
                                                  competence_id smallint NOT NULL,
                                                  PRIMARY KEY (user_id,competence_id),
                                                  KEY FKkuath65neook2ngxwp8uytltg (competence_id),
                                                  CONSTRAINT FK567o2q7end1ju63k6wjp7rfvn FOREIGN KEY (user_id) REFERENCES users (id),
                                                  CONSTRAINT FKkuath65neook2ngxwp8uytltg FOREIGN KEY (competence_id) REFERENCES competences (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

