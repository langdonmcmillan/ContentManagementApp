USE CutePuppiesTest;

-- 
-- CREATE TABLE User (
--     UserId INT NOT NULL AUTO_INCREMENT,
--     RoleCode VARCHAR(20) NOT NULL,
--     CreatedDate DATE NOT NULL,
--     UpdatedDate DATE,
--     DeletedDate DATE,
--     UserName VARCHAR(100) NOT NULL,
--     UserPassword VARCHAR(100) NOT NULL,
--     UserEmail VARCHAR(100) NOT NULL,
--     PRIMARY KEY (UserId),
--     KEY (RoleCode)
-- );

INSERT INTO User (RoleCode, CreatedDate, UpdatedDate, DeletedDate, UserName, UserPassword, UserEmail)
VALUES ('ADMIN', '1900-01-01', null, null, 'sadukie', 'password1234', 'sadukie@sadukie.com');

INSERT INTO User (RoleCode, CreatedDate, UpdatedDate, DeletedDate, UserName, UserPassword, UserEmail)
VALUES ('AUTHOR', '1900-01-01', null, null, 'marke_ting', 'password1234', 'marke@sadukie.com');

-- update existing Post and Content table
UPDATE Post p
    SET p.CreatedByUserId = CASE p.CreatedByUserId
        WHEN 0 THEN 1 
        WHEN 1 THEN 2
    END
WHERE p.CreatedByUserId IN (0,1);

UPDATE Content c
    SET c.CreatedByUserId = CASE c.CreatedByUserId
        WHEN 0 THEN 1 
        WHEN 1 THEN 2
    END
WHERE c.CreatedByUserId IN (0,1);