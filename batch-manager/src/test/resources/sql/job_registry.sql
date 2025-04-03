DROP TABLE IF EXISTS public.job_registry;

CREATE TABLE public.job_registry (
	job_name varchar(200) NOT NULL,
	CONSTRAINT job_registry_pkey PRIMARY KEY (job_name)
);

INSERT INTO public.job_registry (job_name) VALUES ('job'), ('another-job');