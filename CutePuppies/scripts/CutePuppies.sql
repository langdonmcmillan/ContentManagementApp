-- Based on Joes2Pros
SET SQL_SAFE_UPDATES = 0;
DROP DATABASE IF EXISTS CutePuppies;
CREATE DATABASE CutePuppies;
USE CutePuppies;

CREATE TABLE Content (
ContentId INT NOT NULL AUTO_INCREMENT,
PostId INT NOT NULL,
Title VARCHAR(100),
ContentImgLink VARCHAR(500),
Body BLOB,
Snippet BLOB,
StatusId INT NOT NULL,
UrlPattern VARCHAR(1000) NOT NULL,
ContentTypeId INT NOT NULL,
CreatedByUserId INT NOT NULL,
CreatedOnDate DATE NOT NULL,
UpdatedByUserId INT NOT NULL,
UpdatedOnDate DATE NOT NULL,
ArchivedByUserId INT NOT NULL,
ArchivedOnDate DATE NOT NULL,
PRIMARY KEY (ContentId),
KEY (PostId),
KEY(StatusId)
);

CREATE TABLE Post (
PostId INT NOT NULL AUTO_INCREMENT,
CreatedByUserId INT NOT NULL,
CreatedOnDate DATE NOT NULL,
UpdatedByUserId INT NOT NULL,
UpdatedOnDate DATE NOT NULL,
ArchivedByUserId INT NOT NULL,
ArchivedOnDate DATE NOT NULL,
PRIMARY KEY (PostId)
);

CREATE TABLE User (
UserId INT NOT NULL AUTO_INCREMENT,
RoleID INT NOT NULL,
CreatedDate DATE NOT NULL,
UpdatedDate DATE,
DeletedDate DATE,
UserName VARCHAR(100),
UserPassword VARCHAR(100),
PRIMARY KEY (UserId),
KEY (RoleId)
);

CREATE TABLE `Status` (
StatusId INT NOT NULL AUTO_INCREMENT,
StatusDescription VARCHAR(100),
PRIMARY KEY (StatusId)
);
INSERT INTO `Status` (StatusDescription) VALUES ('Published');
INSERT INTO `Status` (StatusDescription) VALUES ('Draft');
INSERT INTO `Status` (StatusDescription) VALUES ('Awaiting approval');
INSERT INTO `Status` (StatusDescription) VALUES ('Archived');

CREATE TABLE Role (
RoleId INT NOT NULL AUTO_INCREMENT,
RoleDescription VARCHAR(100),
PRIMARY KEY (RoleId)
);
INSERT INTO Role (RoleDescription) VALUES ('Administrator');
INSERT INTO Role (RoleDescription) VALUES ('Author');
INSERT INTO Role (RoleDescription) VALUES ('Visitor');
INSERT INTO Role (RoleDescription) VALUES ('Member');

CREATE TABLE ContentType (
ContentTypeId INT NOT NULL AUTO_INCREMENT,
ContentTypeDescription VARCHAR(100),
PRIMARY KEY (ContentTypeId)
);
INSERT INTO ContentType (ContentTypeDescription) VALUES ('Post');
INSERT INTO ContentType (ContentTypeDescription) VALUES ('Static page');
INSERT INTO ContentType (ContentTypeDescription) VALUES ('Comment');

CREATE TABLE Permission (
PermissionId INT NOT NULL AUTO_INCREMENT,
PermissionDescription VARCHAR(100),
PRIMARY KEY (PermissionId)
);
INSERT INTO Permission (PermissionDescription) VALUES ('Can Create New Draft');
INSERT INTO Permission (PermissionDescription) VALUES ('Can Edit Published And Draft Post');
INSERT INTO Permission (PermissionDescription) VALUES ('Can Publish Post');
INSERT INTO Permission (PermissionDescription) VALUES ('Can Delete Post');
INSERT INTO Permission (PermissionDescription) VALUES ('Can Submit Draft To Publish');
INSERT INTO Permission (PermissionDescription) VALUES ('Can Submit Post To Delete');

CREATE TABLE Tag (
TagId INT NOT NULL AUTO_INCREMENT,
TagDescription VARCHAR(500),
PRIMARY KEY (TagId)
);
INSERT INTO Tag (TagDescription) VALUES ('Adoption');
INSERT INTO Tag (TagDescription) VALUES ('Training');
INSERT INTO Tag (TagDescription) VALUES ('Advice');

CREATE TABLE Category (
CategoryId INT NOT NULL AUTO_INCREMENT,
CategoryDescription VARCHAR(500),
PRIMARY KEY (CategoryId)
);
INSERT INTO Category (CategoryDescription) VALUES ('Ask An Expert');
INSERT INTO Category (CategoryDescription) VALUES ('Adoption Story');
INSERT INTO Category (CategoryDescription) VALUES ('Animal Rescue');

-- establishing foreign keys for content table
ALTER TABLE Content
ADD CONSTRAINT Content_fk_Post FOREIGN KEY (PostId) REFERENCES Post (PostId) ON DELETE NO ACTION,
ADD CONSTRAINT Content_fk_Status FOREIGN KEY (StatusId) REFERENCES `Status` (StatusId) ON DELETE NO ACTION;

-- establishing foreign key for user table
ALTER TABLE User
ADD CONSTRAINT User_fk_Role FOREIGN KEY (RoleId) REFERENCES Role (RoleId) ON DELETE NO ACTION;

-- establishing bridge table betwen role and permission
CREATE TABLE role_permission (
RoleId INT NOT NULL,
PermissionId INT NOT NULL,
KEY RoleId (RoleId),
KEY PermissionId (PermissionId)
);
ALTER TABLE role_permission
ADD CONSTRAINT role_permission_fk_role FOREIGN KEY (RoleId) REFERENCES Role (RoleId) ON DELETE NO ACTION,
ADD CONSTRAINT role_permission_fk_permission FOREIGN KEY (PermissionId) REFERENCES Permission (PermissionId) ON DELETE NO ACTION;

-- establishing bridge table between content and tag
CREATE TABLE content_tag (
ContentId INT NOT NULL,
TagId INT NOT NULL,
KEY ContentId (ContentId),
KEY TagId (TagId)
);
ALTER TABLE content_tag
ADD CONSTRAINT content_tag_fk_content FOREIGN KEY (ContentId) REFERENCES Content (ContentId) ON DELETE NO ACTION,
ADD CONSTRAINT content_tag_fk_tag FOREIGN KEY (TagId) REFERENCES Tag (TagId) ON DELETE NO ACTION;

-- establishing bridge table between content and category
CREATE TABLE content_category (
ContentId INT NOT NULL,
CategoryId INT NOT NULL,
KEY ContentId (ContentId),
KEY CategoryId (CategoryId)
);
ALTER TABLE content_category
ADD CONSTRAINT content_category_fk_content FOREIGN KEY (ContentId) REFERENCES Content (ContentId) ON DELETE NO ACTION,
ADD CONSTRAINT content_category_fk_category FOREIGN KEY (CategoryId) REFERENCES Category (CategoryId) ON DELETE NO ACTION;

