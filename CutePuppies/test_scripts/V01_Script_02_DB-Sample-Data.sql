use CutePuppiesTest;

INSERT INTO Category (CategoryDescription) VALUES ('Ask An Expert');
INSERT INTO Category (CategoryDescription) VALUES ('Adoption Story');
INSERT INTO Category (CategoryDescription) VALUES ('Animal Rescue');
INSERT INTO Tag (TagDescription) VALUES ('Adoption');
INSERT INTO Tag (TagDescription) VALUES ('Training');
INSERT INTO Tag (TagDescription) VALUES ('Advice');


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

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-11', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(5, 'Post by admin', 'someImgLink', 'body for content 1 of post 5', 'snippet for content 1 of post 5', 'PUBLISHED', 'urlPatternContent1Post5', 'POST', 0, '2016-12-11', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (7, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (7, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-12', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(6, 'Post by admin', 'someImgLink', 'body for content 1 of post 6', 'snippet for content 1 of post 6', 'PUBLISHED', 'urlPatternContent1Post6', 'POST', 0, '2016-12-12', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (8, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (8, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-13', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(7, 'Post by admin', 'someImgLink', 'body for content 1 of post7', 'snippet for content 1 of post 7', 'PUBLISHED', 'urlPatternContent1Post7', 'POST', 0, '2016-12-13', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (9, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (9, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-14', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(8, 'Post by admin', 'someImgLink', 'body for content 1 of post 8', 'snippet for content 1 of post 8', 'PUBLISHED', 'urlPatternContent1Post8', 'POST', 0, '2016-12-14', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (10, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (10, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-15', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(9, 'Post by admin', 'someImgLink', 'body for content 1 of post 9', 'snippet for content 1 of post 9', 'PUBLISHED', 'urlPatternContent1Post9', 'POST', 0, '2016-12-15', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (11, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (11, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-16', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(10, 'Post by admin', 'someImgLink', 'body for content 1 of post 10', 'snippet for content 1 of post 10', 'PUBLISHED', 'urlPatternContent1Post10', 'POST', 0, '2016-12-16', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (12, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (12, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-17', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(11, 'Post by admin', 'someImgLink', 'body for content 1 of post 11', 'snippet for content 1 of post 11', 'PUBLISHED', 'urlPatternContent1Post11', 'POST', 0, '2016-12-17', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (13, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (13, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-18', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(12, 'Post by admin', 'someImgLink', 'body for content 1 of post 12', 'snippet for content 1 of post 12', 'PUBLISHED', 'urlPatternContent1Post12', 'POST', 0, '2016-12-18', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (14, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (14, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-19', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(13, 'Post by admin', 'someImgLink', 'body for content 1 of post 13', 'snippet for content 1 of post 13', 'PUBLISHED', 'urlPatternContent1Post13', 'POST', 0, '2016-12-19', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (15, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (15, 1);

INSERT INTO Post (CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES (0, '2016-12-20', null, null, null, null);
INSERT INTO Content 
(PostId, Title, ContentImgLink, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate) 
VALUES
(14, 'Post by admin', 'someImgLink', 'body for content 1 of post 14', 'snippet for content 1 of post 14', 'PUBLISHED', 'urlPatternContent1Post14', 'POST', 0, '2016-12-20', null, null, null, null);
INSERT INTO content_category (ContentId, CategoryId) VALUES (16, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (16, 1);

UPDATE Content 
SET 
   ContentImgAltTxt = 'sample alt text';
   
UPDATE Content 
SET 
   ContentImgLink = 'http://placehold.it/900x300';

insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 2;

UPDATE Content 
SET 
    body = CONCAT(body,
            'Puppy kitty ipsum dolor sit good dog food walk lazy cat collar fleas mittens litter box turtle purr. Furry fluffy cockatiel feeder toys heel treats chew biscuit hamster left paw puppy. Wagging yawn cat cockatiel walk litter brush lick pet gate stripes feathers nap meow. Walk play smooshy bird seed litter box twine yawn fluffy. Furry roll over speak slobber polydactyl pet gate field good boy cat leash twine park Spike cockatiel field pet. Small Animals purr lol catz vitamins collar crate shake.Behavior Mittens Tigger walk swimming house train food ferret walk wagging leash vaccination yawn bird feathers drool chow groom. Water licks Buddy hamster paws lick drool twine leash. Nap cage Mittens window bird brush play whiskers wag tail grooming leash brush throw Buddy smooshy treats swimming. Drool Scooby snacks water dog parrot tabby smooshy shake pet supplies. Good Boy pet food foot house train pet food harness maine coon cat biscuit pet supplies. Roll Over lick vaccine slobbery treats pet supplies cockatiel dog house pet foot roll over stay crate lol catz twine ball litter cage heel water. Heel water tail Fido field critters harness foot aquatic pet gate fish stripes string purr.Pet Gate fish toys ID tag grooming Rover kitten Buddy tabby. Parrot gimme five feathers lol catz slobbery tongue house train pet gate cage chew walk pet supplies behavior fluffy. Dragging fish meow tuxedo cat parakeet fleas pet bird tail roll over. Pet Food hamster ID tag warm ferret shake stay dragging cockatiel play dead warm birds behavior brush carrier gimme five. Lazy Cat run tongue leash carrier polydactyl string. Pet Food kitten chirp tabby stick fleas string purr toy sit gimme five stay sit licks walk nap. Tail throw ID tag polydactyl right paw crate bed tooth dog twine run scratcher field harness toy puppy lazy dog. Small Animals whiskers vaccine wet nose crate throw vaccine throw cage chew Snowball behavior kitty bird food yawn right paw.Stay gimme five parakeet Scooby snacks vaccine kitten brush litter purr. Dinnertime pet supplies kisses chirp toys teeth. Vitamins stripes ferret puppy bird seed bird stick nest cage vaccination head canary Mittens roll over kitty. Walk litter box tail small animals polydactyl barky walk litter box teeth tooth. Fish kibble tuxedo maine coon cat bird cage. Left Paw biscuit lazy dog tongue run fast left paw feeder scratcher fetch hamster kisses whiskers drool pet supplies warm lol catz pet supplies cockatiel. Right Paw dog stick nap sit walk pet supplies polydactyl bedding chew Mittens play dead. Whiskers paws hamster meow vaccine Tigger roll over birds treats Spike lazy dog. Lick canary bird fur wag tail carrier turtle feeder harness aquarium maine coon cat Buddy kibble tooth polydactyl Rover lick food drool collar.Toys catch Fido feathers fluffy behavior water dog wet nose fleas lick warm water dog parrot running barky. Bedding field chow commands food kisses toys dragging wet nose dinnertime carrier. Park pet gate tabby puppy bedding gimme five Snowball Fido run groom Scooby snacks groom good boy throw running canary. Play Dead furry bedding pet food run fast dragging stripes nap food slobber lol catz gimme five. Scooby Snacks catch stick nest treats sit food fluffy fur dinnertime polydactyl lick lazy cat running tongue slobbery. Sit barky slobbery aquarium lazy cat bed groom tabby feathers teeth bird purr run fast. Chow wag tail right paw Mittens lol catz chew throw grooming house train speak shake. Chew food carrier pet gate fur right paw vitamins wagging head slobbery ball.')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 2;


insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 3;

UPDATE Content 
SET 
    body = CONCAT(body,
            'Puppy kitty ipsum dolor sit good dog food walk lazy cat collar fleas mittens litter box turtle purr. Furry fluffy cockatiel feeder toys heel treats chew biscuit hamster left paw puppy. Wagging yawn cat cockatiel walk litter brush lick pet gate stripes feathers nap meow. Walk play smooshy bird seed litter box twine yawn fluffy. Furry roll over speak slobber polydactyl pet gate field good boy cat leash twine park Spike cockatiel field pet. Small Animals purr lol catz vitamins collar crate shake.Behavior Mittens Tigger walk swimming house train food ferret walk wagging leash vaccination yawn bird feathers drool chow groom. Water licks Buddy hamster paws lick drool twine leash. Nap cage Mittens window bird brush play whiskers wag tail grooming leash brush throw Buddy smooshy treats swimming. Drool Scooby snacks water dog parrot tabby smooshy shake pet supplies. Good Boy pet food foot house train pet food harness maine coon cat biscuit pet supplies. Roll Over lick vaccine slobbery treats pet supplies cockatiel dog house pet foot roll over stay crate lol catz twine ball litter cage heel water. Heel water tail Fido field critters harness foot aquatic pet gate fish stripes string purr.Pet Gate fish toys ID tag grooming Rover kitten Buddy tabby. Parrot gimme five feathers lol catz slobbery tongue house train pet gate cage chew walk pet supplies behavior fluffy. Dragging fish meow tuxedo cat parakeet fleas pet bird tail roll over. Pet Food hamster ID tag warm ferret shake stay dragging cockatiel play dead warm birds behavior brush carrier gimme five. Lazy Cat run tongue leash carrier polydactyl string. Pet Food kitten chirp tabby stick fleas string purr toy sit gimme five stay sit licks walk nap. Tail throw ID tag polydactyl right paw crate bed tooth dog twine run scratcher field harness toy puppy lazy dog. Small Animals whiskers vaccine wet nose crate throw vaccine throw cage chew Snowball behavior kitty bird food yawn right paw.Stay gimme five parakeet Scooby snacks vaccine kitten brush litter purr. Dinnertime pet supplies kisses chirp toys teeth. Vitamins stripes ferret puppy bird seed bird stick nest cage vaccination head canary Mittens roll over kitty. Walk litter box tail small animals polydactyl barky walk litter box teeth tooth. Fish kibble tuxedo maine coon cat bird cage. Left Paw biscuit lazy dog tongue run fast left paw feeder scratcher fetch hamster kisses whiskers drool pet supplies warm lol catz pet supplies cockatiel. Right Paw dog stick nap sit walk pet supplies polydactyl bedding chew Mittens play dead. Whiskers paws hamster meow vaccine Tigger roll over birds treats Spike lazy dog. Lick canary bird fur wag tail carrier turtle feeder harness aquarium maine coon cat Buddy kibble tooth polydactyl Rover lick food drool collar.Toys catch Fido feathers fluffy behavior water dog wet nose fleas lick warm water dog parrot running barky. Bedding field chow commands food kisses toys dragging wet nose dinnertime carrier. Park pet gate tabby puppy bedding gimme five Snowball Fido run groom Scooby snacks groom good boy throw running canary. Play Dead furry bedding pet food run fast dragging stripes nap food slobber lol catz gimme five. Scooby Snacks catch stick nest treats sit food fluffy fur dinnertime polydactyl lick lazy cat running tongue slobbery. Sit barky slobbery aquarium lazy cat bed groom tabby feathers teeth bird purr run fast. Chow wag tail right paw Mittens lol catz chew throw grooming house train speak shake. Chew food carrier pet gate fur right paw vitamins wagging head slobbery ball.')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 3;
    

insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 5;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 5;
    
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 6;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 6;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 7;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 7;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 8;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 8;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 9;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 9;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 10;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 10;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 11;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 11;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 12;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 12;
 
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 13;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 13;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 14;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 14;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 15;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 15;
    
insert into Content (PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate)
select PostId, Title, ContentImgLink,ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate, UpdatedByUserId, UpdatedOnDate, ArchivedByUserId, ArchivedOnDate 
	from Content 
    where ContentId = 16;

UPDATE Content 
SET 
    body = CONCAT(body,
            '<p>Puppy kitty ipsum dolor sit good dog running purr yawn grooming. Vaccine scratcher pet supplies nap parakeet fish litter Snowball nap pet supplies yawn collar. Kibble mouse tuxedo stick cage kibble Buddy licks pet gate park cat Tigger fetch lol catz running paws smooshy. Window warm field stay cockatiel dog aquarium stay pet supplies stick kitty fluffy parrot feeder vaccine critters good boy lol catz Buddy. Commands run cat run fast twine pet supplies tooth toy running cockatiel paws run smooshy play walk slobber water dog Scooby snacks. Tongue warm roll over scratch paws mouse.</p><p>Rover slobbery Tigger groom scratch yawn warm vitamins throw water dog commands run fast walk field. Nest bedding mouse nap meow vaccine twine polydactyl carrier Spike critters Fido harness. Bark cage stay birds bed finch vitamins foot running house train warm foot pet run dog house. Ferret bird bird seed swimming speak pet food food kibble. Vaccination left paw nest bird lazy dog pet supplies kisses bird seed vaccine nest pet supplies fleas lick bark chirp. Stay slobbery bedding hamster commands fleas fish sit chirp. Kitty fish barky left paw fluffy lazy cat pet supplies lick shake. Fleas toy meow head pet food fleas shake Spike Fido fetch chirp ferret vaccine park bed wagging. Canary tongue cockatiel cat bed hamster vaccination cockatiel pet supplies catch parrot aquatic head groom paws swimming hamster dragging.</p><p>Cage biscuit left paw aquatic Rover cat wet nose chirp crate litter box paws slobbery heel. Treats Spike kitten water puppy nap lick stripes. Scratcher stick walk food litter box ball fur sit chow Snowball lazy cat kibble bird whiskers drool collar harness. Scooby Snacks parakeet pet food water leash commands ferret ID tag tuxedo house train play aquarium bedding tooth. Drool lazy cat biscuit wet nose running cage field kibble ball toy sit maine coon cat barky walk bird fluffy ferret paws. Biscuit run fast chirp behavior cage brush mouse litter box tuxedo.</p><p>Smooshy tuxedo mouse teeth pet gate stripes Tigger run smooshy play shake tooth bird food tabby barky right paw drool commands cage behavior. String Buddy stay vitamins cockatiel feeder fluffy park brush twine finch. Scooby Snacks Mittens biscuit swimming fetch bedding wagging catch brush birds parrot purr Snowball treats chew bed. Litter food dog toy tabby heel string mouse stick barky. Aquarium ID tag crate scratch food drool small animals chow Scooby snacks harness vaccine window Tigger walk kitten meow furry kibble pet supplies. Hamster cat collar tail wag tail kibble dragging park. Left Paw pet stick gimme five canary heel head food run wagging catch wet nose kitty carrier Scooby snacks bed pet gate speak polydactyl. Whiskers harness treats left paw pet gate toy bark kisses bird kisses meow finch warm treats vitamins shake hamster speak food. Chirp roll over dog house licks tuxedo swimming sit good boy Tigger birds gimme five purr harness chirp cage running polydactyl.</p><p>Run field tuxedo puppy right paw toys food. Puppy sit lol catz parrot toy park lazy cat biscuit Buddy smooshy Buddy. Slobbery maine coon cat dragging collar commands paws purr. Collar barky stick smooshy parrot bed groom litter run. Throw Spike foot carrier ID tag fur Mittens speak polydactyl nest chirp feathers run fast nap. Pet Supplies nap lol catz slobber ferret bird dragging canary furry. Mouse Tigger play vaccine smooshy tabby bird chew park. Leash lazy dog walk vitamins kitty vaccine licks warm tongue Rover litter box bird food purr Rover shake lick. Litter carrier collar dog house litter box toys. Commands biscuit feeder Spike birds cat whiskers shake twine tail throw cockatiel cat chirp tail brush.</p>')
WHERE
    ContentId = (SELECT LAST_INSERT_ID());

UPDATE Content 
SET 
    ContentStatusCode = 'ARCHIVED'
WHERE
    ContentId = 16;

UPDATE Content 
SET 
    archivedbyuserid = 0
WHERE
    ArchivedByUserId IS NULL
        AND ContentStatusCode = 'ARCHIVED';
    
UPDATE Content 
SET 
    archivedondate = '2016-11-26'
WHERE
    archivedondate IS NULL
        AND ContentStatusCode = 'ARCHIVED';

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

INSERT INTO content_tag (ContentId, TagId) VALUES (30, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (30, 2);
INSERT INTO content_category (ContentId, CategoryId) VALUES (30, 1);
INSERT INTO content_category (ContentId, CategoryId) VALUES (30, 2);

INSERT INTO content_tag (ContentId, TagId) VALUES (29, 2);
INSERT INTO content_tag (ContentId, TagId) VALUES (29, 3);
INSERT INTO content_category (ContentId, CategoryId) VALUES (29, 2);
INSERT INTO content_category (ContentId, CategoryId) VALUES (29, 3);

INSERT INTO content_tag (ContentId, TagId) VALUES (28, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (28, 3);
INSERT INTO content_category (ContentId, CategoryId) VALUES (28, 1);
INSERT INTO content_category (ContentId, CategoryId) VALUES (28, 3);

INSERT INTO content_tag (ContentId, TagId) VALUES (27, 1);
INSERT INTO content_category (ContentId, CategoryId) VALUES (27, 1);

INSERT INTO content_tag (ContentId, TagId) VALUES (26, 1);
INSERT INTO content_tag (ContentId, TagId) VALUES (26, 2);
INSERT INTO content_tag (ContentId, TagId) VALUES (26, 3);
INSERT INTO content_category (ContentId, CategoryId) VALUES (26, 1);
INSERT INTO content_category (ContentId, CategoryId) VALUES (26, 2);
INSERT INTO content_category (ContentId, CategoryId) VALUES (26, 3);

INSERT INTO Content (Title, Body, ContentStatusCode,  UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate) 
VALUES ('Sample Static Page 1', 'This is a static page body', 'PUBLISHED', 'about-us', 'STATIC PAGE', 1, '2016-12-01 20:07:00');

INSERT INTO Content (Title, Body, ContentStatusCode,  UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate) 
VALUES ('Sample Static Page 2', 'This is a static page body', 'PUBLISHED', 'contact-us', 'STATIC PAGE', 1, '2016-12-01 20:17:00');
INSERT INTO Content (Title, Body, ContentStatusCode,  UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate) 
VALUES ('Sample Static Page 3', 'This is a static page body', 'PUBLISHED', 'about-puppy-adoption', 'STATIC PAGE', 1, '2016-12-01 20:27:00');
INSERT INTO Content (Title, Body, ContentStatusCode,  UrlPattern, ContentTypeCode, CreatedByUserId, CreatedOnDate) 
VALUES ('Sample Static Page 4', 'This is a static page body', 'PUBLISHED', 'someOtherStaticPage', 'STATIC PAGE', 1, '2016-12-01 20:37:00');

create or replace view nonArchived 
as select c.PostId, count(c.ContentStatusCode) 
from Content c
join Post p on p.PostId = c.PostId
where c.ContentStatusCode != 'ARCHIVED'
group by p.PostId;
