create table
  "tokens" (
    "id" serial primary key,
    "expired" bool default false,
    "revoked" bool default false,
    "token" varchar(255) null,
    "token_type" varchar(255) null,
    "user_id" integer not null,
    constraint "uk_tokens" unique ("token"),
    constraint "fk_users" foreign key ("user_id") references "users" on delete cascade
  );