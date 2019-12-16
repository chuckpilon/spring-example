INSERT INTO public.users (username, password, enabled) VALUES ('cpilon', '{bcrypt}$2a$10$TTsbo77ByAr7gBB.hGOyUexPg3uhNYwi32oKclj18czkFnHzDKXxW', true); -- password
INSERT INTO public.users (username, password, enabled) VALUES ('admin', '{bcrypt}$2a$10$zF6yiUfmdqM8JQQQZkr6f.qvUeZHGdvOghPHszW.tzPOXKy8RyWQi', true); -- admin

INSERT INTO public.authorities (username, authority) VALUES ('cpilon', 'ROLE_USER');
INSERT INTO public.authorities (username, authority) VALUES ('admin', 'ROLE_USER');

INSERT INTO public.items (created, created_by, description) VALUES (NOW(), 'cpilon', 'Amazon Echo (3rd Gen) - Twilight Blue');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/61gTLgYwFCL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/610ogi5eeoL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/61tKJi6agRL._AC_SL1000_.jpg');

INSERT INTO public.items (created, created_by, description) VALUES (NOW(), 'cpilon', 'Introducing Echo Studio - High-fidelity smart speaker with 3D audio and Alexa');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/61FPZMMCqzL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/613HESgMbAL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/61gd6YvzWyL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/717E43kaTGL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/71aUSuUk4hL._AC_SL1000_.jpg');
INSERT INTO public.item_images (item_id, url) VALUES (currval(pg_get_serial_sequence('public.items', 'id')), 'https://images-na.ssl-images-amazon.com/images/I/71DV6AsWu0L._AC_SL1000_.jpg');
