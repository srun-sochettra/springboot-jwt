
CREATE TABLE users(
                      id serial PRIMARY KEY NOT NULL ,
                      full_name VARCHAR(50) NOT NULL ,
                      email VARCHAR(50) NOT NULL ,
                      password VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
                      id serial PRIMARY KEY NOT NULL,
                      role_name VARCHAR(50) NOT NULL
);

CREATE TABLE user_role(
                          user_id INT NOT NULL REFERENCES users (id) ON DELETE CASCADE ,
                          role_id INT NOT NULL REFERENCES roles (id) ON DELETE CASCADE ,
                          PRIMARY KEY (user_id, role_id)
);

insert into roles (role_name) values ('ROLE_USER');
insert into roles (role_name) values ('ROLE_ADMIN');

insert into users (full_name, email, password) values ('Srun Sochettra', 'srunsochettra@gmail.com', '1234')

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);