INSERT INTO tbl_user (name, created_at)  VALUES('user', now());

INSERT INTO tbl_wallet(user_id, balance, created_at) values
(SELECT ID from tbl_user where name='user', 50000, now());