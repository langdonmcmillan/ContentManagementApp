USE CutePuppies;

alter table content add ContentImgAltTxt varchar(255) after ContentImgLink;
UPDATE Content 
SET 
    ContentImgAltTxt = 'sample alt text';
    
UPDATE Content 
SET 
    ContentImgLink = 'http://placehold.it/900x300';

