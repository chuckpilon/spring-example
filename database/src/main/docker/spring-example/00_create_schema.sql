CREATE TABLE public.items (
	id SERIAL PRIMARY KEY NOT NULL,
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
