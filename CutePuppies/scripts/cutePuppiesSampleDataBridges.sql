USE CutePuppies;

SELECT * FROM Post;
SELECT * FROM Content;

-- add tag 1 and 2 to content 1
INSERT INTO content_tag (ContentId, TagId) VALUES (1, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (1, 2);

-- add tag 2 to content 2
INSERT INTO content_tag (ContentId, TagId) VALUES (2, 2);

-- add tag 3 to content 6
INSERT INTO content_tag (ContentId, TagId) VALUES (6, 3);

-- add category 1 to content 1
INSERT INTO content_category (ContentId, CategoryId) VALUES (1, 1);

-- add category 2 to content 2
INSERT INTO content_category (ContentId, CategoryId) VALUES (2, 2);

-- add category 3 to content 6
INSERT INTO content_category (ContentId, CategoryId) VALUES (6, 3);