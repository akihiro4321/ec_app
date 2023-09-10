create table
  "categories" (
    "category_id" serial primary key,
    "category_name" varchar(255) default null,
    "created_at" timestamptz not null default current_timestamp
  );

create table
  "products" (
    "product_id" serial primary key,
    "description" varchar(2000) default null,
    "image_url" varchar(500) default null,
    "product_name" varchar(500) default null,
    "price" decimal not null,
    "disabled" integer not null default 0,
    "created_at" timestamptz not null default current_timestamp,
    "category_id" integer not null,
    constraint "fk_categories" foreign key ("category_id") references "categories" ("category_id") on delete restrict
  );