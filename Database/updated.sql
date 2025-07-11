USE course_system;

--  Step 1: Drop existing foreign keys
ALTER TABLE registrations DROP FOREIGN KEY registrations_ibfk_1;
ALTER TABLE registrations DROP FOREIGN KEY registrations_ibfk_2;

--  Step 2: Re-add foreign keys with ON DELETE CASCADE
ALTER TABLE registrations
ADD CONSTRAINT registrations_ibfk_1
FOREIGN KEY (student_id)
REFERENCES students(student_id)
ON DELETE CASCADE;

ALTER TABLE registrations
ADD CONSTRAINT registrations_ibfk_2
FOREIGN KEY (course_id)
REFERENCES courses(course_id)
ON DELETE CASCADE;
