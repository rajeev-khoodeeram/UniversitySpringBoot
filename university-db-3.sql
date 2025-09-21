SET FOREIGN_KEY_CHECKS = 0;

-- 1️⃣ Faculty
INSERT INTO `faculty` (`facultyId`, `facultyname`, `facultycode`) VALUES
(2,'Business and Management','BM'),
(4,'Information and Communication Technology','ICT'),
(5,'Engineering','Engg');

-- 2️⃣ Department
INSERT INTO `department` (`departmentId`, `departmentName`, `departmentCode`, `facultyId`, `lecturerId`) VALUES
(4,'Applied Computer Science &Engg','ACS',4,2),
(5,'Mechanical Engineering','MECA',5,3),
(10,'Civil Engineering','Civic',5,5),
(12,'EE Engineering','EEE',5,6);

-- 3️⃣ Lecturer
INSERT INTO `lecturer` (`lecturerId`,`lecturerFirstName`,`lecturerLastName`,`lecturerEmail`,`departmentId`,`lecturerHireDate`,`lecturerTitle`) VALUES
(2,'Rajeevah','Khoodeeramah','rajkhoooo@gmail.com',5,'2025-04-12 00:00:00','AP'),
(3,'sdasd','dsadsa','dfdf@yahoo.com',4,'2020-05-06 00:00:00','SL'),
(5,'dfdf','dfsdfdsf','dfdfdfd@yahoo.com',4,'2024-10-10 00:00:00','SL'),
(6,'Paul ','Thomas','pault@outlook.com',4,'2020-10-10 00:00:00','L'),
(7,'John','Travolta','johnt@gmail.com',4,'2019-08-12 00:00:00','AP'),
(8,'Paula','Smith','psmith@gmail.com',5,'2018-03-12 00:00:00','Prof');

-- 4️⃣ Student
INSERT INTO `student` (`student_id`,`student_firstname`,`student_lastname`,`student_dob`,`student_gender`,`student_phone`,`student_address`,`student_status`,`student_email`,`enrolment_date`) VALUES
(8,'Rajeev','Khoodeeram tomTom','2005-10-10','male','905-518-7851','hAMILTON,ON, Canada','Pending','rajkhoo77@yahoo.com','2025-10-10'),
(50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO `courseenrolment` VALUES (1,8,2,'2025-10-10 00:00:00.000000','Pending','Null');


INSERT INTO `moduleenrolment` VALUES (3,8,3,'2025-09-09 00:00:00','0','Accepted'),(4,8,4,'2025-10-10 00:00:00','0','Pending');

INSERT INTO `coordinator` VALUES (2,'rajkhoo77@gmail.com','Rajeev Khoodeeram',2,4);

INSERT INTO `course` VALUES (2,'Applied Computer science',3,'BSC',4,'ACS',0);

INSERT INTO `module` VALUES (3,'Database design','DD',2,2,3,2),(4,'Introduction to Programming','InPr',3,2,3,1);

SET FOREIGN_KEY_CHECKS = 1;
