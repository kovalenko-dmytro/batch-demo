INSERT INTO public.batch_job_instance
(job_instance_id, "version", job_name, job_key)
VALUES(3, 0, 'import-csv-to-db', '5a4f285353dbf3755613fa3d3580f38b');

INSERT INTO public.batch_job_execution
(job_execution_id, "version", job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated)
VALUES(3, 2, 3, '2025-03-13 14:43:33.592', '2025-03-13 14:43:33.618', '2025-03-13 14:54:08.700', 'COMPLETED', 'COMPLETED', '', '2025-03-13 14:54:08.705');

INSERT INTO public.batch_job_execution_params
(job_execution_id, parameter_name, parameter_type, parameter_value, identifying)
VALUES(3, 'file-storage-resource', 'java.lang.String', 'example.csv', 'Y');
INSERT INTO public.batch_job_execution_params
(job_execution_id, parameter_name, parameter_type, parameter_value, identifying)
VALUES(3, 'job-exec-mark', 'java.lang.String', 'f8ec8765-308a-4c26-9543-a84a369ba81f', 'Y');
INSERT INTO public.batch_job_execution_params
(job_execution_id, parameter_name, parameter_type, parameter_value, identifying)
VALUES(3, 'job-name', 'java.lang.String', 'import-csv-to-db', 'Y');

INSERT INTO public.batch_step_execution
(step_execution_id, "version", step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated)
VALUES(13, 4058, 'file-to-work-step', 3, '2025-03-13 14:43:46.679', '2025-03-13 14:43:46.687', '2025-03-13 14:46:36.535', 'COMPLETED', 4056, 4055984, 0, 4055984, 0, 0, 0, 0, 'COMPLETED', '', '2025-03-13 14:46:36.548');
INSERT INTO public.batch_step_execution
(step_execution_id, "version", step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated)
VALUES(11, 3, 'truncate-work-step', 3, '2025-03-13 14:43:33.637', '2025-03-13 14:43:33.644', '2025-03-13 14:43:33.780', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2025-03-13 14:43:33.786');
INSERT INTO public.batch_step_execution
(step_execution_id, "version", step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated)
VALUES(12, 3, 'upload-file-step', 3, '2025-03-13 14:43:33.800', '2025-03-13 14:43:33.807', '2025-03-13 14:43:35.534', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2025-03-13 14:43:35.538');
INSERT INTO public.batch_step_execution
(step_execution_id, "version", step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated)
VALUES(14, 4058, 'work-to-master-step', 3, '2025-03-13 14:46:47.205', '2025-03-13 14:46:47.214', '2025-03-13 14:50:02.955', 'COMPLETED', 4056, 4055984, 0, 4055984, 0, 0, 0, 0, 'COMPLETED', '', '2025-03-13 14:50:02.959');
INSERT INTO public.batch_step_execution
(step_execution_id, "version", step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated)
VALUES(15, 3, 'truncate-work-step', 3, '2025-03-13 14:50:47.218', '2025-03-13 14:50:47.225', '2025-03-13 14:53:42.285', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2025-03-13 14:53:42.291');

