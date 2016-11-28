USE CutePuppiesTest;

-- post 1
INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-11-20', null, null, null, null);

-- post 2
INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-11-30', null, null, null, null);

-- post 3
INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (1, '2016-12-01', null, null, null, null);

-- post 4
INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-10', null, null, null, null);

-- 2 content for post 1. one archived, one published.
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(1, 'Hello World!', 'someImgLink', 'body for content 1 of post 1', 'snippet for content 1 of post 1', 'ARCHIVED', 'urlPatternContent1Post1', 'POST', 0, '2016-11-20', null, null, 0, '2016-11-24');
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(1, 'Hello World!', 'someImgLink', 'body for content 2 of post 1', 'snippet for content 2 of post 1', 'PUBLISHED', 'urlPatternContent2Post1', 'POST', 0, '2016-11-24', null, null, null, null);

-- 1 content for post 2. one draft, nothing published
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(2, 'Who are you?', 'someOtherImgLink', 'body for content 1 of post 2', 'snippet for content 1 of post 2', 'DRAFT', 'urlPatternContent1Post2', 'POST', 0, '2016-11-27', null, null, null, null);

-- 2 content for post 3. one archived, one awaiting, nothing published.
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(3, 'Post by marketing person', 'someImgLink', 'body for content 1 of post 3', 'snippet for content 1 of post 3', 'ARCHIVED', 'urlPatternContent1Post3', 'POST', 1, '2016-12-01', null, null, 1, '2016-12-05');
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(3, 'Post by marketing person', 'someImgLink', 'body for content 2 of post 3', 'snippet for content 2 of post 3', 'AWAITING', 'urlPatternContent2Post3', 'POST', 1, '2016-12-05', null, null, null, null);

-- 1 content for post 4. one published.
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(4, 'Post by admin', 'someImgLink', 'body for content 1 of post 4', 'snippet for content 1 of post 4', 'PUBLISHED', 'urlPatternContent1Post4', 'POST', 0, '2016-12-10', null, null, null, null);
