CREATE TABLE public.items (
	id SERIAL PRIMARY KEY NOT NULL,
	description varchar NULL
);
CREATE UNIQUE INDEX items_id_idx ON public.items (id);
