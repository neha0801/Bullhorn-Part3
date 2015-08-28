--------------------------------------------------------
--  File created - Friday-August-28-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BLOGUSER
--------------------------------------------------------

  CREATE TABLE "TESTDB"."BLOGUSER" 
   (	"USER_ID" NUMBER(10,0), 
	"EMAIL" VARCHAR2(255 BYTE), 
	"USER_PASSWORD" VARCHAR2(255 BYTE), 
	"MOTTO" VARCHAR2(255 BYTE), 
	"NAME" VARCHAR2(255 BYTE), 
	"JOINDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BULLHORN
--------------------------------------------------------

  CREATE TABLE "TESTDB"."BULLHORN" 
   (	"POST_ID" NUMBER(10,0), 
	"POST" VARCHAR2(255 BYTE), 
	"USER_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into TESTDB.BLOGUSER
SET DEFINE OFF;
Insert into TESTDB.BLOGUSER (USER_ID,EMAIL,USER_PASSWORD,MOTTO,NAME,JOINDATE) values (2,'mena@infosys.com','password','fdsfcdsh','Mena',to_date('28-AUG-15','DD-MON-RR'));
Insert into TESTDB.BLOGUSER (USER_ID,EMAIL,USER_PASSWORD,MOTTO,NAME,JOINDATE) values (1,'neha@infosys.com','password','gshdfghdgf','neha',to_date('28-AUG-15','DD-MON-RR'));
Insert into TESTDB.BLOGUSER (USER_ID,EMAIL,USER_PASSWORD,MOTTO,NAME,JOINDATE) values (3,'sunny@infosys.com','sunny','klklklk','Sunny Rathore',to_date('28-AUG-15','DD-MON-RR'));
Insert into TESTDB.BLOGUSER (USER_ID,EMAIL,USER_PASSWORD,MOTTO,NAME,JOINDATE) values (4,'nishainfosys.com','password','fdjksfhdsjk','nisha',to_date('28-AUG-15','DD-MON-RR'));
REM INSERTING into TESTDB.BULLHORN
SET DEFINE OFF;
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (16,'Mena''s Blog',2);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (20,'Hello..... whats up',1);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (12,'blog22',1);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (19,'Hello',3);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (10,'blog1',1);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (11,'blog2',1);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (13,null,1);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (15,'blog11',1);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (17,null,2);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (18,'Sunny''s blog',3);
Insert into TESTDB.BULLHORN (POST_ID,POST,USER_ID) values (14,'my blog',1);
--------------------------------------------------------
--  DDL for Index PK_USERID
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."PK_USERID" ON "TESTDB"."BLOGUSER" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_POSTID
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."PK_POSTID" ON "TESTDB"."BULLHORN" ("POST_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table BLOGUSER
--------------------------------------------------------

  ALTER TABLE "TESTDB"."BLOGUSER" ADD CONSTRAINT "PK_USERID" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BULLHORN
--------------------------------------------------------

  ALTER TABLE "TESTDB"."BULLHORN" ADD CONSTRAINT "PK_POSTID" PRIMARY KEY ("POST_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BULLHORN
--------------------------------------------------------

  ALTER TABLE "TESTDB"."BULLHORN" ADD CONSTRAINT "FK_USERID" FOREIGN KEY ("USER_ID")
	  REFERENCES "TESTDB"."BLOGUSER" ("USER_ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger BLOGUSER_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."BLOGUSER_TRIGGER" 
 BEFORE INSERT ON BlogUser
  FOR EACH ROW
BEGIN
  :new.user_id := seq_bullhornUser.nextval;
  :new.JoinDate := sysdate;
END;
/
ALTER TRIGGER "TESTDB"."BLOGUSER_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BULLHORN_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."BULLHORN_TRIGGER" 
 BEFORE INSERT ON Bullhorn
  FOR EACH ROW
BEGIN
  :new.post_id := seq_bullhorn.nextval;
END;
/
ALTER TRIGGER "TESTDB"."BULLHORN_TRIGGER" ENABLE;
