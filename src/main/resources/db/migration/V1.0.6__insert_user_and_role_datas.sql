insert into
  roles (role_id, role_name)
values
  (1, 'ROLE_GENERAL'),
  (2, 'ROLE_ADMIN');

insert into
  users (email, first_name, last_name, password)
values
  (
    'general@example.com',
    'yamada',
    'taro',
    'pas$2a$10$RsccuSsLrZcgXBDexnzSK.nLdhadsBo1/HXW/9Waas8YddeOCMiaCsword'
  );

insert into
  users (email, first_name, last_name, password)
values
  (
    'admin@example.com',
    'yamada',
    'taro',
    'pas$2a$10$RsccuSsLrZcgXBDexnzSK.nLdhadsBo1/HXW/9Waas8YddeOCMiaCsword'
  );

insert into
  user_role (user_id, role_id)
values
  (1, 1),
  (2, 1),
  (2, 2);