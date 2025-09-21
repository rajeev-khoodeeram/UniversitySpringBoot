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
  `facultyId` BIGINT NOT NULL,
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
