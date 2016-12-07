USE CutePuppiesTest;
DROP PROCEDURE IF EXISTS `reset_CutePuppiesTest`;
DELIMITER $$
CREATE PROCEDURE `reset_CutePuppiesTest` ()
BEGIN

SET SQL_SAFE_UPDATES = 0;

DELETE FROM content_category;
DELETE FROM content_tag;
DELETE FROM Tag;
DELETE FROM Category;
DELETE FROM Content;
DELETE FROM Post;
DELETE FROM `User`;

insert into `User` values
	(1,'ROLE_ADMIN','2011-01-01 01:11:11',null,null,'admin','admin','admin@cutepuppies.com'),
	(2,'ROLE_AUTHOR','2012-02-02 02:22:22',null,null,'author1','author2','author1@cutepuppies.com'),
	(3,'ROLE_AUTHOR','2013-03-03 03:33:33',null,null,'author2','author2','author2@cutepuppies.com');
    
insert into Tag values
	(1, 'TestTag1'),
	(2, 'TestTag2'),
	(3, 'TestTag3'),
	(4, 'TestTag4'),
	(5, 'TestTag5'),
	(6, 'TestTag6'),
	(7, 'UnpublishedTag7');
    
insert into Category values
	(1, 'TestCategory1'),
	(2, 'TestCategory2'),
	(3, 'TestCategory3'),
	(4, 'TestCategory4'),
	(5, 'TestCategory5'),
	(6, 'TestCategory6');

insert into Post values 
	(1,1,'2011-01-01 01:11:11',null,null,null,null), -- Created once and saved as draft
	(2,1,'2011-01-02 01:11:11',1,'2011-01-02 02:11:11',null,null), -- Created and modified once, saved as draft
	(3,1,'2011-01-03 01:11:11',1,'2011-01-03 02:11:11',1,'2011-01-03 03:11:11'), -- Created, modified once, deleted
	(4,1,'2011-01-04 01:11:11',null,null,null,null), -- Created and published
	(5,1,'2011-01-05 01:11:11',1,'2011-01-05 03:11:11',null,null), -- Created, modified draft, published
	(6,1,'2011-01-06 01:11:11',2,null,null,null), -- Created, author 1 modified, awaiting approval
	(7,1,'2011-01-07 01:11:11',3,null,null,null), -- Created, author 2 modified, approved
	(8,1,'2011-01-08 01:11:11',1,null,1,null), -- Created, author 1 modified, rejected
	(9,1,'2011-01-09 01:11:11',null,null,null,null), -- Author 1 created, saved draft
	(10,1,'2011-01-10 01:11:11',null,null,null,null), -- Author 1 created, pending approval
	(11,1,'2011-01-11 01:11:11',null,null,null,null), -- Author 1 created, approved by admin
	(12,1,'2011-01-12 01:11:11',null,null,null,null), -- Author 1 created, saved draft, author 2 edited, saved draft
	(13,1,'2011-01-13 01:11:11',null,null,null,null), -- Author 1 created, saved draft, author 2 edited, approved by admin
	(14,2,'2011-01-14 01:11:11',null,null,null,null); -- Created and published, author 1 created new draft (published version stays)
-- 	(15,1,'2011-01-15 01:11:11',null,null,null,null), -- Created and published, author 1 created new draft awaiting approval (published version stays)
-- 	(16,1,'2011-01-16 01:11:11',null,null,null,null), -- Created and published, author 1 created new draft awaiting approval, new version published
-- 	(17,1,'2011-01-17 01:11:11',null,null,null,null),
-- 	(18,1,'2011-01-18 01:11:11',null,null,null,null),
-- 	(19,1,'2011-01-19 01:11:11',null,null,null,null),
-- 	(20,1,'2011-01-20 01:11:11',null,null,null,null);
    
insert into Content values
	(1,1,'Post 1, Content 1','http://placehold.it/900x300','Post 1, Content 1 Image','<p>Post 1, Content 1 test content body</p>',null,'DRAFT','1','POST',1,'2011-01-01 01:11:11',null,null,null,null),
	(2,2,'Post 2, Content 1','http://placehold.it/900x300','Post 2, Content 1 Image','<p>Post 2, Content 1 test content body</p>',null,'ARCHIVED','2','POST',1,'2011-01-02 01:11:11',null,null,1,'2011-01-02 02:11:11'),
	(3,2,'Post 2, Content 2','http://placehold.it/900x300','Post 2, Content 2 Image','<p>Post 2, Content 2 test content body</p>',null,'DRAFT','2','POST',1,'2011-01-02 02:11:11',null,null,null,null),
	(4,3,'Post 3, Content 1','http://placehold.it/900x300','Post 3, Content 1 Image','<p>Post 3, Content 1 test content body</p>',null,'ARCHIVED','3','POST',1,'2011-01-03 01:11:11',null,null,1,'2011-01-03 02:11:11'),
	(5,3,'Post 3, Content 2','http://placehold.it/900x300','Post 3, Content 2 Image','<p>Post 3, Content 2 test content body</p>',null,'ARCHIVED','3','POST',1,'2011-01-03 02:11:11',null,null,1,'2011-01-03 03:11:11'),
	(6,4,'Post 4, Content 1','http://placehold.it/900x300','Post 4, Content 1 Image','<p>Post 4, Content 1 test content body</p>',null,'PUBLISHED','4','POST',1,'2011-01-04 01:11:11',null,null,null,null),
	(7,5,'Post 5, Content 1','http://placehold.it/900x300','Post 5, Content 1 Image','<p>Post 5, Content 1 test content body</p>',null,'ARCHIVED','5','POST',1,'2011-01-05 01:11:11',null,null,1,'2011-01-05 02:11:11'),
	(8,5,'Post 5, Content 2','http://placehold.it/900x300','Post 5, Content 2 Image','<p>Post 5, Content 2 test content body</p>',null,'PUBLISHED','5','POST',1,'2011-01-05 02:11:11',null,null,1,'2011-01-05 03:11:11'),
	(9,6,'Post 6, Content 1','http://placehold.it/900x300','Post 6, Content 1 Image','<p>Post 6, Content 1 test content body</p>',null,'ARCHIVED','6','POST',1,'2011-01-06 01:11:11',null,null,2,'2011-01-06 02:11:11'),
	(10,6,'Post 6, Content 2','http://placehold.it/900x300','Post 6, Content 2 Image','<p>Post 6, Content 2 test content body</p>',null,'AWAITING','6','POST',2,'2011-01-06 02:11:11',null,null,null,null),
	(11,7,'Post 7, Content 1','http://placehold.it/900x300','Post 7, Content 1 Image','<p>Post 7, Content 1 test content body</p>',null,'ARCHIVED','7','POST',1,'2011-01-07 01:11:11',null,null,3,'2011-01-07 02:11:11'),
	(12,7,'Post 7, Content 2','http://placehold.it/900x300','Post 7, Content 2 Image','<p>Post 7, Content 2 test content body</p>',null,'PUBLISHED','7','POST',3,'2011-01-07 02:11:11',1,'2011-01-07 03:11:11',null,null),
	(13,8,'Post 8, Content 1','http://placehold.it/900x300','Post 8, Content 1 Image','<p>Post 8, Content 1 test content body</p>',null,'ARCHIVED','8','POST',1,'2011-01-08 01:11:11',null,null,2,'2011-01-08 02:11:11'), -- created by admin
	(14,8,'Post 8, Content 2','http://placehold.it/900x300','Post 8, Content 2 Image','<p>Post 8, Content 2 test content body</p>',null,'ARCHIVED','8','POST',2,'2011-01-08 02:11:11',null,null,1,'2011-01-08 03:11:11'), -- updated by author 1, rejected by admin
	(15,9,'Post 9, Content 1','http://placehold.it/900x300','Post 9, Content 1 Image','<p>Post 9, Content 1 test content body</p>',null,'DRAFT','9','POST',2,'2011-01-09 01:11:11',null,null,null,null),
	(16,10,'Post 10, Content 1','http://placehold.it/900x300','Post 10, Content 1 Image','<p>Post 10, Content 1 test content body</p>',null,'AWAITING','10','POST',2,'2011-01-10 01:11:11',null,null,null,null),
	(17,11,'Post 11, Content 1','http://placehold.it/900x300','Post 11, Content 1 Image','<p>Post 11, Content 1 test content body</p>',null,'PUBLISHED','11','POST',2,'2011-01-11 01:11:11',1,'2011-01-11 02:11:11',null,null),
	(18,12,'Post 12, Content 1','http://placehold.it/900x300','Post 12, Content 1 Image','<p>Post 12, Content 1 test content body</p>',null,'ARCHIVED','12','POST',2,'2011-01-12 01:11:11',null,null,3,'2011-01-12 02:11:11'),
	(19,12,'Post 12, Content 2','http://placehold.it/900x300','Post 12, Content 2 Image','<p>Post 12, Content 2 test content body</p>',null,'DRAFT','12','POST',3,'2011-01-12 02:11:11',null,null,null,null),
	(20,13,'Post 13, Content 1','http://placehold.it/900x300','Post 13, Content 1 Image','<p>Post 13, Content 1 test content body</p>',null,'ARCHIVED','13','POST',2,'2011-01-13 01:11:11',null,null,3,'2011-01-13 02:11:11'),
	(21,13,'Post 13, Content 2','http://placehold.it/900x300','Post 13, Content 2 Image','<p>Post 13, Content 2 test content body</p>',null,'PUBLISHED','13','POST',3,'2011-01-13 02:11:11',1,'2011-01-13 03:11:11',null,null),
	(22,14,'Post 14, Content 1','http://placehold.it/900x300','Post 14, Content 1 Image','<p>Post 14, Content 1 test content body</p>',null,'PUBLISHED','14','POST',1,'2011-01-14 01:11:11',null,null,null,null),
	(23,14,'Post 14, Content 2','http://placehold.it/900x300','Post 14, Content 2 Image','<p>Post 14, Content 2 test content body</p>',null,'DRAFT','14','POST',3,'2011-01-14 02:11:11',1,'2011-01-14 03:11:11',null,null);

-- static page insertions.
insert into Content (ContentId, Title, ContentImgLink, ContentImgAltTxt, Body, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) VALUES
(24,'StaticPage1Content24','http://placehold.it/900x300','StaticPage1Content24Img','<p>Content24 test content body</p>','PUBLISHED','statPgUrlPattern1','STATIC PAGE',1,'2011-01-14 02:11:11', null, null, null, null),
(25,'StaticPage2Content25','http://placehold.it/900x300','StaticPage2Content25Img','<p>Content25 test content body</p>','ARCHIVED','statPgUrlPattern2','STATIC PAGE',1,'2011-01-15 02:24:11', null, null, 1, '2014-01-17 02:24:11'),
(26,'StaticPage3Content26','http://placehold.it/900x300','StaticPage3Content26Img','<p>Content26 test content body</p>','DRAFT','statPgUrlPattern3','STATIC PAGE',1,'2011-01-16 02:41:11', null, null, null, null),
(27,'StaticPage4Content27','http://placehold.it/900x300','StaticPage4Content27Img','<p>Content27 test content body</p>','PUBLISHED','statPgUrlPattern4','STATIC PAGE',1,'2011-01-17 02:11:11', 1, '2013-01-14 02:11:11', null, null),
(28,'StaticPage5Content28','http://placehold.it/900x300','StaticPage5Content28Img','<p>Content28 test content body</p>','PUBLISHED','statPgUrlPattern5','STATIC PAGE',1,'2011-01-18 02:21:11', null, null, null, null),
(29,'StaticPage6Content29','http://placehold.it/900x300','StaticPage5Content29Img','<p>Content29 test content body</p>','ARCHIVED','statPgUrlPattern6','STATIC PAGE',1,'2011-01-19 02:21:11', null, null, 1, '2014-01-17 02:24:11');

-- post comments
insert into User (UserId, RoleCode, CreatedDate, UserName, UserPassword, UserEmail) 
values
(4,'ROLE_GUEST','2012-01-14 01:11:11','Post 14 Commenter 1','','111');
insert into Content (ContentId, PostId, Body, UrlPattern, ContentStatusCode, ContentTypeCode, CreatedByUserId, CreatedOnDate) 
values
(30,14,'First!', '','PUBLISHED','COMMENT',4,'2012-01-14 01:11:11');
insert into User (UserId, RoleCode, CreatedDate, UserName, UserPassword, UserEmail) 
values
(5,'ROLE_GUEST','2012-01-14 02:11:11','Post 14 Commenter 2','','222');
insert into Content (ContentId, PostId, Body, UrlPattern, ContentStatusCode, ContentTypeCode, CreatedByUserId, CreatedOnDate,ArchivedByUserId,ArchivedOnDate) 
values
(31,14,'Second!', '','ARCHIVED','COMMENT',5,'2012-01-14 02:11:11',1,'2012-01-14 03:11:11');
insert into User (UserId, RoleCode, CreatedDate, UserName, UserPassword, UserEmail) 
values
(6,'ROLE_GUEST','2012-01-14 04:11:11','Post 14 Commenter 3','','333');
insert into Content (ContentId, PostId, Body, UrlPattern, ContentStatusCode, ContentTypeCode, CreatedByUserId, CreatedOnDate,ArchivedByUserId,ArchivedOnDate) 
values
(32,14,'Third! (Second commenter was deleted)', '','PUBLISHED','COMMENT',6,'2012-01-14 04:11:11',null,null);

-- post 5 has no tags
insert into content_tag values
	(1,1),
	(1,2),
	(1,3),
	(2,4),
	(2,5),
	(2,6),
	(3,1),
	(3,2),
	(3,3),
	(3,4),
	(3,5),
	(3,6),
	(4,1),
	(6,3),
	(7,1),
	(7,4),
	(8,3),
	(8,6),
	(8,2),
	(8,5),
	(9,3),
	(9,2),
	(10,1),
	(10,3),
	(10,5),
	(11,1),
	(11,4),
	(12,6),
	(12,4),
	(12,2),
	(13,5),
	(14,2),
	(14,4),
	(15,6);
    
-- post 12 has no category
insert into content_category values
	(1,1),
	(2,3),
	(2,4),
	(3,2),
	(4,4),
	(4,3),
	(4,6),
	(5,1),
	(6,2),
	(7,5),
	(8,3),
	(8,6),
	(9,3),
	(10,1),
	(11,5),
	(13,2),
	(14,5),
	(14,1);

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
            			Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> </p>
            		<p>Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
            			Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> </p>
            		<p>Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
            			Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> </p>
            		<p>Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
            			Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> </p>
            		<p>Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
            			Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> 
                        Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> Test test test test <strong>test test test</strong> test test test test test <em>test test test.</em> </p>')
where ContentTypeCode = 'POST';
                        
END$$
DELIMITER ;