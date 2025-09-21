SET FOREIGN_KEY_CHECKS = 0;

-- ===========================================
-- 1️⃣ Drop all tables if they exist
-- ===========================================

DROP TABLE IF EXISTS `moduleenrolment`;
DROP TABLE IF EXISTS `module`;
DROP TABLE IF EXISTS `courseenrolment`;
DROP TABLE IF EXISTS `coordinator`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `lecturer`;
DROP TABLE IF EXISTS `faculty`;
DROP TABLE IF EXISTS `student`;

-- ===========================================
-- 2️⃣ Create tables with PKs and NULLABLE FKs
-- ===========================================

CREATE TABLE `faculty` (
  `facultyId` BIGINT NOT NULL AUTO_INCREMENT,
  `facultyname` VARCHAR(255) NOT NULL,
  `facultycode` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`facultyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `department` (
  `departmentId` BIGINT NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(255) NOT NULL,
  `departmentCode` VARCHAR(255) NOT NULL,
  `facultyId` BIGINT DEFAULT NULL,
  `lecturerId` BIGINT DEFAULT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `lecturer` (
  `lecturerId` BIGINT NOT NULL AUTO_INCREMENT,
  `lecturerFirstName` VARCHAR(255) NOT NULL,
  `lecturerLastName` VARCHAR(255) NOT NULL,
  `lecturerEmail` VARCHAR(255) NOT NULL,
  `departmentId` BIGINT DEFAULT NULL,
  `lecturerHireDate` DATETIME DEFAULT NULL,
  `lecturerTitle` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`lecturerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `student` (
  `student_id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_firstname` VARCHAR(255) DEFAULT NULL,
  `student_lastname` VARCHAR(255) DEFAULT NULL,
  `student_dob` VARCHAR(255) DEFAULT NULL,
  `student_gender` VARCHAR(255) DEFAULT NULL,
  `student_phone` VARCHAR(255) DEFAULT NULL,
  `student_address` VARCHAR(255) DEFAULT NULL,
  `student_status` VARCHAR(255) DEFAULT NULL,
  `student_email` VARCHAR(255) DEFAULT NULL,
  `enrolment_date` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `course` (
  `courseId` BIGINT NOT NULL AUTO_INCREMENT,
  `courseName` VARCHAR(255) NOT NULL,
  `courseDuration` INT NOT NULL,
  `courseLevel` VARCHAR(255) NOT NULL,
  `departmentId` BIGINT DEFAULT NULL,
  `courseAbbrev` VARCHAR(255) NOT NULL,
  `coordinatorId` BIGINT DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `coordinator` (
  `coordinatorId` BIGINT NOT NULL AUTO_INCREMENT,
  `coordinatorEmail` VARCHAR(255) NOT NULL,
  `coordinatorName` VARCHAR(255) NOT NULL,
  `courseId` BIGINT DEFAULT NULL,
  `departmentId` BIGINT DEFAULT NULL,
  PRIMARY KEY (`coordinatorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `courseenrolment` (
  `courseEnrolId` BIGINT NOT NULL AUTO_INCREMENT,
  `studentId` BIGINT DEFAULT NULL,
  `courseId` BIGINT DEFAULT NULL,
  `courseEnrolDate` DATETIME NOT NULL,
  `courseEnrolStatus` VARCHAR(255) NOT NULL,
  `courseEnrolGraduation` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`courseEnrolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `module` (
  `moduleId` BIGINT NOT NULL AUTO_INCREMENT,
  `moduleName` VARCHAR(255) NOT NULL,
  `moduleCode` VARCHAR(255) NOT NULL,
  `moduleCredits` INT NOT NULL,
  `courseId` BIGINT DEFAULT NULL,
  `lecturerId` BIGINT DEFAULT NULL,
  `moduleSemester` INT DEFAULT NULL,
  PRIMARY KEY (`moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `moduleenrolment` (
  `enrolmentId` BIGINT NOT NULL AUTO_INCREMENT,
  `studentId` BIGINT DEFAULT NULL,
  `moduleId` BIGINT DEFAULT NULL,
  `enrolmentDate` DATETIME NOT NULL,
  `grade` VARCHAR(255) NOT NULL,
  `status` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`enrolmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===========================================
-- 3️⃣ Add foreign keys AFTER inserts
-- ===========================================

ALTER TABLE `department`
  ADD CONSTRAINT `department_faculty_FK` FOREIGN KEY (`facultyId`) REFERENCES `faculty`(`facultyId`),
  ADD CONSTRAINT `department_lecturer_FK` FOREIGN KEY (`lecturerId`) REFERENCES `lecturer`(`lecturerId`);

ALTER TABLE `lecturer`
  ADD CONSTRAINT `lecturer_department_FK` FOREIGN KEY (`departmentId`) REFERENCES `department`(`departmentId`);

ALTER TABLE `course`
  ADD CONSTRAINT `course_department_FK` FOREIGN KEY (`departmentId`) REFERENCES `department`(`departmentId`),
  ADD CONSTRAINT `course_coordinator_FK` FOREIGN KEY (`coordinatorId`) REFERENCES `coordinator`(`coordinatorId`);

ALTER TABLE `coordinator`
  ADD CONSTRAINT `coordinator_course_FK` FOREIGN KEY (`courseId`) REFERENCES `course`(`courseId`),
  ADD CONSTRAINT `coordinator_department_FK` FOREIGN KEY (`departmentId`) REFERENCES `department`(`departmentId`);

ALTER TABLE `courseenrolment`
  ADD CONSTRAINT `courseenrolment_student_FK` FOREIGN KEY (`studentId`) REFERENCES `student`(`student_id`),
  ADD CONSTRAINT `courseenrolment_course_FK` FOREIGN KEY (`courseId`) REFERENCES `course`(`courseId`);

ALTER TABLE `module`
  ADD CONSTRAINT `module_course_FK` FOREIGN KEY (`courseId`) REFERENCES `course`(`courseId`),
  ADD CONSTRAINT `module_lecturer_FK` FOREIGN KEY (`lecturerId`) REFERENCES `lecturer`(`lecturerId`);

ALTER TABLE `moduleenrolment`
  ADD CONSTRAINT `enrolment_module_FK` FOREIGN KEY (`moduleId`) REFERENCES `module`(`moduleId`) ON DELETE CASCADE,
  ADD CONSTRAINT `enrolment_student_FK` FOREIGN KEY (`studentId`) REFERENCES `student`(`student_id`) ON DELETE CASCADE ON UPDATE RESTRICT;



SET FOREIGN_KEY_CHECKS = 1;





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
