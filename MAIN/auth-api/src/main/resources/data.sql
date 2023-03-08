INSERT INTO uob.roles(name) VALUES('ROLE_PREPARER');
INSERT INTO uob.roles(name) VALUES('ROLE_APPROVER');
INSERT INTO uob.roles(name) VALUES('ROLE_ADMIN');
INSERT INTO uob.ability(action,subject) VALUES('MANAGE','ALL');
INSERT INTO uob.ability(action,subject) VALUES('READ','AUTH');
INSERT INTO uob.ability(action,subject) VALUES('READ','ACL');
