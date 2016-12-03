insert into CutePuppies.Role values 
	('ROLE_ADMIN','Administrator'),	
    ('ROLE_AUTHOR','Author'),
    ('ROLE_GUEST','Guest'),
    ('ROLE_MEMBER','Member');
    
UPDATE User 
SET 
    RoleCode = 'ROLE_ADMIN'
WHERE
    RoleCode = 'ADMIN';
UPDATE User 
SET 
    RoleCode = 'ROLE_AUTHOR'
WHERE
    RoleCode = 'AUTHOR';
    
insert into Permission values
	('CAN_ADD_USER','Can add users with any role'),
	('CAN_EDIT_TAG','Can edit/delete tags'),
	('CAN_EDIT_CATEGORY','Can edit/delete categories');
    
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

UPDATE `CutePuppies`.`User` 
SET 
    `UserPassword` = '$2a$10$ffs7y5b7OwYsccPXjUimy.d.m3eACk0Mhb6R4TqpxAnuGE80SnbVu' -- password1234

