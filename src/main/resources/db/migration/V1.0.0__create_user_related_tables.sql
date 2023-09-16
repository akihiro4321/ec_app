create table
  "users" (
    "user_id" serial,
    "email" varchar(255) not null,
    "first_name" varchar(255) not null,
    "last_name" varchar(255) not null,
    "password" varchar(255) not null,
    "disabled" integer not null default 0,
    "created_at" timestamptz not null default current_timestamp,
    "updated_at" timestamptz not null default current_timestamp,
    primary key ("user_id")
  );

create table
  "roles" (
    "role_id" integer primary key,
    "role_name" varchar(255) not null
  );

create table
  "user_role" (
    "user_id" integer,
    "role_id" integer,
    constraint "pk_user_role" primary key ("user_id", "role_id"),
    constraint "fk_user_role_user_id" foreign key ("user_id") references users ("user_id"),
    constraint "fk_user_role_role_id" foreign key ("role_id") references roles ("role_id")
  )