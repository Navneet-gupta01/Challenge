INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Turner', 'Tom', 'tom.turner@provider.de', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Doe', 'John', 'jon.doe@test-mailing.com', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Smith', 'Karen', 'k-smith@something.de', 'female', false);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Turner', 'Maria', 'maTur@somewhere.com', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Murphy', 'Donald', 'DoMu@provider.de', 'male', false);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Cooper', 'Anne', 'a.cooper@test-mail.com', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Goldsmith', 'Peter', 'p-goldsmith@provider.de', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Smith', 'Collin', 'collin-smith@test-mailing.com', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Walker', 'Karen', 'karen-walker@something.de', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Coco', 'Scrapy', 'coco-scrapy@somewhere.com', 'female', false);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Doe', 'Craig', 'DoeCraig@provider.de', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Kilbegan', 'Caroline', 'a.cooper@test-mail.com', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('McGillan', 'Johnathan', 'jmc@test-mailing.com', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Wright', 'Samy', 's.wright@something.de', 'female', false);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('McCallan', 'Maria', 'marmccal@somewhere.com', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Allen', 'Joe', 'joe-allen@provider.de', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Davis', 'Caroline', 'c-davis@test-mail.com', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Kerry', 'Craig', 'craigKerryh@provider.de', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Williams', 'Gerard', 'williams-gerard@test-mailing.com', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Miller', 'Kirsten', 'kmiller@something.de', 'female', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Brown', 'Kathy', 'brown.kathy@somewhere.com', 'female', false);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Jones', 'Thomas', 'tom-jones@provider.de', 'male', true);
INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES ('Wright', 'Carolina', 'caro-wright@test-mail.com', 'female', true);



INSERT INTO templates (template , key) VALUES ('\n Hello dear {{user.salutation}} {{user.name}},\n this are our latest news...\n\nIn case you donot want to receive any further newsletters in the future please unsubscribe here:\n https://domain-of-product.de/unsubscribe-newsletter/{{user.identifier}}\nBest Regards,\nYour Customer Support Team', 'newsletter');

INSERT INTO templates (template , key) VALUES ('\n Hello dear {{user.salutation}} {{user.name}},\n we are very happy to welcome you to our newsletter.\n\nIn case you donot want to receive any further newsletters in the future please unsubscribe here:\n https://domain-of-product.de/unsubscribe-newsletter/{{user.identifier}}\nBest Regards,\nYour Customer Support Team', 'welcome');

