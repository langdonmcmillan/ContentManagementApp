SET SQL_SAFE_UPDATES = 0;
DROP DATABASE IF EXISTS CutePuppiesTest;
CREATE DATABASE CutePuppiesTest;
USE CutePuppiesTest;

CREATE TABLE Content (
    ContentId INT NOT NULL AUTO_INCREMENT,
    PostId INT,
    Title VARCHAR(100),
    ContentImgLink VARCHAR(500),
	ContentImgAltTxt varchar(255),
    Body TEXT,
    Snippet TEXT,
    ContentStatusCode VARCHAR(20) NOT NULL,
    UrlPattern VARCHAR(100) NOT NULL,
    ContentTypeCode VARCHAR(20) NOT NULL,
    CreatedByUserId INT NOT NULL,
    CreatedOnDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedByUserId INT,
    UpdatedOnDate DATETIME ON UPDATE CURRENT_TIMESTAMP,
    ArchivedByUserId INT,
    ArchivedOnDate DATETIME,
    PRIMARY KEY (ContentId),
    KEY (PostId),
    KEY (ContentStatusCode)
);

CREATE TABLE Post (
    PostId INT NOT NULL AUTO_INCREMENT,
    CreatedByUserId INT NOT NULL,
    CreatedOnDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedByUserId INT,
    UpdatedOnDate DATETIME ON UPDATE CURRENT_TIMESTAMP,
    ArchivedByUserId INT,
    ArchivedOnDate DATETIME,
    PRIMARY KEY (PostId)
);

CREATE TABLE User (
    UserId INT NOT NULL AUTO_INCREMENT,
    RoleCode VARCHAR(20) NOT NULL,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedDate DATETIME ON UPDATE CURRENT_TIMESTAMP,
    DeletedDate DATETIME,
    UserName VARCHAR(100) NOT NULL UNIQUE,
    UserPassword VARCHAR(100) NOT NULL,
    UserEmail VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (UserId),
    KEY (RoleCode)
);

CREATE TABLE ContentStatus (
    ContentStatusCode VARCHAR(20) NOT NULL,
    ContentStatusDescription VARCHAR(100),
    PRIMARY KEY (ContentStatusCode)
);
INSERT INTO ContentStatus (ContentStatusCode, ContentStatusDescription) VALUES ('PUBLISHED', 'Published');
INSERT INTO ContentStatus (ContentStatusCode, ContentStatusDescription) VALUES ('DRAFT', 'Draft');
INSERT INTO ContentStatus (ContentStatusCode, ContentStatusDescription) VALUES ('AWAITING', 'Awaiting approval');
INSERT INTO ContentStatus (ContentStatusCode, ContentStatusDescription) VALUES ('ARCHIVED', 'Archived');

CREATE TABLE Role (
    RoleCode VARCHAR(20) NOT NULL,
    RoleDescription VARCHAR(100),
    PRIMARY KEY (RoleCode)
);
insert into CutePuppies.Role values 
('ROLE_ADMIN','Administrator'),	
    ('ROLE_AUTHOR','Author'),
    ('ROLE_GUEST','Guest'),
    ('ROLE_MEMBER','Member');

CREATE TABLE ContentType (
    ContentTypeCode VARCHAR(20) NOT NULL,
    ContentTypeDescription VARCHAR(100),
    PRIMARY KEY (ContentTypeCode)
);
INSERT INTO ContentType (ContentTypeCode, ContentTypeDescription) VALUES ('POST', 'Post');
INSERT INTO ContentType (ContentTypeCode, ContentTypeDescription) VALUES ('STATIC PAGE', 'Static page');
INSERT INTO ContentType (ContentTypeCode, ContentTypeDescription) VALUES ('COMMENT', 'Comment');

CREATE TABLE Permission (
    PermissionCode VARCHAR(100) NOT NULL,
    PermissionDescription VARCHAR(100),
    PRIMARY KEY (PermissionCode)
);
INSERT INTO Permission (PermissionCode, PermissionDescription) VALUES ('CAN_CREATE', 'Can Create New Draft');
INSERT INTO Permission (PermissionCode, PermissionDescription) VALUES ('CAN_EDIT', 'Can Edit Published And Draft Post');
INSERT INTO Permission (PermissionCode, PermissionDescription) VALUES ('CAN_PUBLISH', 'Can Publish Post');
INSERT INTO Permission (PermissionCode, PermissionDescription) VALUES ('CAN_DELETE', 'Can Delete Post');
insert into Permission values
	('CAN_ADD_USER','Can add users with any role'),
	('CAN_EDIT_TAG','Can edit/delete tags'),
	('CAN_EDIT_CATEGORY','Can edit/delete categories');

CREATE TABLE Tag (
    TagId INT NOT NULL AUTO_INCREMENT,
    TagDescription VARCHAR(500) UNIQUE,
    PRIMARY KEY (TagId)
);

CREATE TABLE Category (
    CategoryId INT NOT NULL AUTO_INCREMENT,
    CategoryDescription VARCHAR(500) UNIQUE,
    PRIMARY KEY (CategoryId)
);

-- establishing foreign keys for content table
ALTER TABLE Content
ADD CONSTRAINT Content_fk_Post FOREIGN KEY (PostId) REFERENCES Post (PostId) ON DELETE NO ACTION,
ADD CONSTRAINT Content_fk_ContentStatus FOREIGN KEY (ContentStatusCode) REFERENCES ContentStatus (ContentStatusCode) ON DELETE NO ACTION;

-- establishing foreign key for user table
ALTER TABLE User
ADD CONSTRAINT User_fk_Role FOREIGN KEY (RoleCode) REFERENCES Role (RoleCode) ON DELETE NO ACTION;

-- establishing bridge table betwen role and permission
CREATE TABLE role_permission (
    RoleCode VARCHAR(20) NOT NULL,
    PermissionCode VARCHAR(100) NOT NULL,
    KEY RoleCode (RoleCode),
    KEY PermissionCode (PermissionCode)
);
ALTER TABLE role_permission
ADD CONSTRAINT role_permission_fk_role FOREIGN KEY (RoleCode) REFERENCES Role (RoleCode) ON DELETE NO ACTION,
ADD CONSTRAINT role_permission_fk_permission FOREIGN KEY (PermissionCode) REFERENCES Permission (PermissionCode) ON DELETE NO ACTION;

insert into role_permission values
	('ROLE_ADMIN','CAN_ADD_USER'),
	('ROLE_ADMIN','CAN_EDIT_TAG'),
	('ROLE_ADMIN','CAN_EDIT_CATEGORY'),
	('ROLE_ADMIN','CAN_CREATE'),
	('ROLE_ADMIN','CAN_EDIT'),
	('ROLE_ADMIN','CAN_DELETE'),
	('ROLE_ADMIN','CAN_PUBLISH'),
	('ROLE_AUTHOR','CAN_CREATE'),
	('ROLE_AUTHOR','CAN_EDIT'),
	('ROLE_AUTHOR','CAN_DELETE');

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

insert into User (RoleCode, UserName, UserPassword, UserEmail) values ('ROLE_ADMIN', 'sadukie', '$2a$10$ffs7y5b7OwYsccPXjUimy.d.m3eACk0Mhb6R4TqpxAnuGE80SnbVu', 'sarah@cutepuppies.com'), ('ROLE_AUTHOR', 'marky_ting', '$2a$10$ffs7y5b7OwYsccPXjUimy.d.m3eACk0Mhb6R4TqpxAnuGE80SnbVu', 'marky_ting@cutepuppies.com');

create or replace view nonArchived 
as select c.PostId, count(c.ContentStatusCode) 
from Content c
join Post p on p.PostId = c.PostId
where c.ContentStatusCode != 'ARCHIVED'
group by p.PostId;
