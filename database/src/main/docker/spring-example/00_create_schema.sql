CREATE TABLE public.users (
	username varchar NOT NULL PRIMARY KEY,
	password varchar NOT NULL,
	enabled boolean NOT NULL
);

CREATE TABLE public.authorities (
	username varchar NOT NULL,
	authority varchar NOT NULL
);
ALTER TABLE public.authorities ADD CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE;
CREATE UNIQUE INDEX authorities_idx ON public.authorities (username, authority);

CREATE TABLE public.items (
	id SERIAL PRIMARY KEY NOT NULL,
	created_by varchar NOT NULL,
	created timestamp NOT NUll,
	description varchar NOT NULL
);
CREATE UNIQUE INDEX items_id_idx ON public.items (id);

CREATE TABLE public.item_images (
	id SERIAL PRIMARY KEY NOT NULL,
	item_id int4 NOT NULL,
	url varchar NOT NULL
);
CREATE UNIQUE INDEX item_images_id_idx ON public.item_images (id);
ALTER TABLE public.item_images ADD CONSTRAINT item_images_fk FOREIGN KEY (item_id) REFERENCES public.items(id);
