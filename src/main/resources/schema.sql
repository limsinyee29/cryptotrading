CREATE TABLE public.tbl_user (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(50) NOT NULL,
	created_at timestamp with time zone NOT NULL,
	updated_at timestamp with time zone,
	CONSTRAINT tbl_user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.tbl_wallet (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	user_id bigint NOT NULL,
	balance double precision NOT NULL,
	created_at timestamp with time zone NOT NULL,
	updated_at timestamp with time zone,
	CONSTRAINT tbl_wallet_pkey PRIMARY KEY (id),
	CONSTRAINT tbl_wallet_user_fkey FOREIGN KEY (user_id) REFERENCES tbl_user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE public.tbl_wallet_crypto (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	user_id bigint NOT NULL,
	type varchar(20) NOT NULL,
	quantity_balance double precision NOT NULL,
	created_at timestamp with time zone NOT NULL,
	updated_at timestamp with time zone,
	CONSTRAINT tbl_wallet_crypto_pkey PRIMARY KEY (id),
	CONSTRAINT tbl_wallet_crypto_user_fkey FOREIGN KEY (user_id) REFERENCES tbl_user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE public.tbl_transaction (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	user_id bigint NOT NULL,
	wallet_id bigint,
	wallet_new_balance double precision,
	wallet_crypto_id bigint,
	wallet_crypto_new_quantity_balance double precision, 
	order_type varchar(20) NOT NULL,
	quantity double precision NOT NULL,
	pricing double precision NOT NULL,
	total_amount double precision NOT NULL,
	created_at timestamp NOT NULL,
	CONSTRAINT tbl_transaction_pkey PRIMARY KEY (id),
	CONSTRAINT tbl_transaction_wallet_crypto_fkey FOREIGN KEY (wallet_crypto_id) REFERENCES tbl_wallet_crypto (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT tbl_transaction_wallet_fkey FOREIGN KEY (wallet_id) REFERENCES tbl_wallet (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT tbl_transaction_user_fkey FOREIGN KEY (user_id) REFERENCES tbl_user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE public.tbl_crypto_pricing (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	crypto_type varchar(20) NOT NULL,
	order_type varchar(20) NOT NULL,
	price double precision NOT NULL,
	created_at timestamp with time zone NOT NULL,
	updated_at timestamp with time zone,
	CONSTRAINT tbl_crypto_pricing_pkey PRIMARY KEY (id)
);
