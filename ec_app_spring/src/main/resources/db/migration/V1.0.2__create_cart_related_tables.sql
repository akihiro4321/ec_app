create table
  "cart_items" (
    "id" serial primary key,
    "quantity" integer not null,
    "user_id" integer not null,
    "product_id" integer not null,
    "created_at" timestamptz not null default current_timestamp,
    constraint "fk_users" foreign key ("user_id") references "users" ("user_id") on delete cascade,
    constraint "fk_products" foreign key ("product_id") references "products" ("product_id") on delete restrict
  );