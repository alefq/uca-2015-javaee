-- Delete previous data
DELETE FROM public.users;


-- Restart sequences
ALTER SEQUENCE public.users_id_seq RESTART WITH 1;

-- Inserts
INSERT INTO public.users (id, mail, name, lastname, password, state, role)
  VALUES (1, 'admin@sodep.com.py', 'admin', 'admin', '123456', 'ACTIVE', 'ROOT');
  

