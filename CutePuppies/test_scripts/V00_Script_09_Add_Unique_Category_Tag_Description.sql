use CutePuppiesTest;

ALTER TABLE Category
ADD UNIQUE (CategoryDescription);

ALTER TABLE Tag
ADD UNIQUE (TagDescription);
