create function refresh_updated_at() returns trigger as $$
begin
  if (tg_op = 'update') then
    new.updated_at := current_timestamp;
  end if;
  return new;
end;
$$ language 'plpgsql';

create trigger refresh_users_updated_at
  before update on "users" for each row
  execute procedure refresh_updated_at();