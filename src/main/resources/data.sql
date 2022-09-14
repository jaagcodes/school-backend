INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(1, "anar@email.com", "Ana", "andre@email.com", "123456789", "Andre", "Osa");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(2,"luisal@email.com", "Luisa", "juan@email.com", "223456789", "Juan", "Lurdes");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(3,"felipe@email.com", "Felipe", "andres@email.com", "323456789", "Andres", "Ozuna");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(4,"andy@email.com", "Andy", "valery@email.com", "423456789", "Valery", "Otto");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(5,"sofi@email.com", "Sofia", "cata@email.com", "523456789", "Cata", "Pinzon");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(6,"valentina@email.com", "Valentina", "valen@email.com", "623456789", "Yuval", "Harari");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(7,"harry@email.com", "Harry", "daniel@email.com", "723456789", "Daniel", "Potter");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(8,"ron@email.com", "Ron", "wesley@email.com", "823456789", "Tom", "Wesley");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(9,"peter@email.com", "Peter", "parker@email.com", "923456789", "Lee", "Parker");
INSERT INTO student(id_student, email_address, first_name, guardian_email, guardian_mobile, guardian_name, last_name) VALUES(10,"bruce@email.com", "Bruce", "wayne@email.com", "123456780", "Viejo", "Wayne");


INSERT INTO user(user_id, username, password, enabled) VALUES (1, 'josealejo0812', '$2a$12$Dkn8mUaNzDLfovs6.03/X.hQhbbtQhOHyPyrmmrTIdMV64hsp/ySK', 1);
INSERT INTO user(user_id, username, password, enabled) VALUES (2, 'ana123', '$2a$12$h5jMS7jfl5LS6bqz8zkgS.BflPvv64DuSC748bleclRweOmsq3ewu', 1);
INSERT INTO user(user_id, username, password, enabled) VALUES (3, 'luisa123', '$2a$12$h5jMS7jfl5LS6bqz8zkgS.BflPvv64DuSC748bleclRweOmsq3ewu', 1);

INSERT INTO role(role_id, name, description) VALUES(1, 'ADMIN', 'Admin');
INSERT INTO role(role_id, name, description) VALUES(2, 'TEACHER', 'Teacher');
INSERT INTO role(role_id, name, description) VALUES(3, 'STUDENT', 'Student');

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(3, 3);