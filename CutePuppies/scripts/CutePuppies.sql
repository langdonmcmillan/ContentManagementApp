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

CREATE TABLE `Status` (
StatusId INT NOT NULL AUTO_INCREMENT,
StatusDescription VARCHAR(100),
PRIMARY KEY (StatusId)
);
