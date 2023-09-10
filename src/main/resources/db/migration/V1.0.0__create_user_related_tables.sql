create table
  "users" (
    "user_id" serial,
    "email" varchar(255) not null,
    "first_name" varchar(255) not null,
    "last_name" varchar(255) not null,
    "password" varchar(255) not null,
    "role_id" integer not null,
    "disabled" integer not null default 0,
    "created_at" timestamptz not null default current_timestamp,
    "updated_at" timestamptz not null default current_timestamp,
    primary key ("user_id")
  );

create table
  "roles" (
    "role_id" serial primary key,
    "role_name" varchar(255) not null,
    "active" integer not null default 0
  );