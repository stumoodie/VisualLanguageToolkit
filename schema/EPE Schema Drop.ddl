alter table RootFolder drop constraint Relationship63;
alter table MapDiagram drop constraint Relationship6;
alter table RootFolder drop constraint Relationship58;
alter table SubFolder drop constraint Relationship59;
alter table SubFolder drop constraint Relationship62;
alter table Canvas drop constraint Relationship7;
alter table Shape drop constraint Relationship101;
alter table Link drop constraint Relationship99;
alter table Label drop constraint Relationship100;
alter table Property drop constraint Relationship77;
alter table Model drop constraint Relationship102;
alter table ShapeProperty drop constraint Relationship92;
alter table LinkTerminus drop constraint Relationship85;
alter table BendPoint drop constraint Relationship79;
alter table LinkProperty drop constraint Relationship94;
alter table Label drop constraint Relationship49;
alter table TextProperty drop constraint Relationship23;
alter table RichTextProperty drop constraint Relationship24;
alter table NumberProperty drop constraint Relationship29;
alter table ListProperty drop constraint Relationship52;
alter table ShapeProperty drop constraint Relationship74;
alter table LinkTerminusProperty drop constraint Relationship78;
alter table LinkProperty drop constraint Relationship93;
alter table LinkTerminusDecorator drop constraint Relationship31;
alter table LinkEndDecorator drop constraint Relationship32;
alter table LinkTerminusProperty drop constraint Relationship80;
alter table Canvas drop constraint Relationship50;
alter table ObjectType drop constraint Relationship33;
alter table Shape drop constraint Relationship89;
alter table Link drop constraint Relationship91;
alter table ListValue drop constraint Relationship54;
alter table CompoundNode drop constraint Relationship104;
alter table CompoundEdge drop constraint Relationship108;
alter table Model drop constraint Relationship113;
alter table CompoundEdge drop constraint Relationship109;
alter table CompoundEdge drop constraint Relationship110;
alter table Shape drop constraint Relationship126;
alter table Label drop constraint Relationship1;
alter table Link drop constraint Relationship131;
alter table CompoundNode drop constraint Relationship105;
alter table CompoundEdge drop constraint Relationship3;
drop table CompoundEdge if exists;
drop table CompoundNode if exists;
drop table Model if exists;
drop table LinkProperty if exists;
drop table BendPoint if exists;
drop table LinkTerminusProperty if exists;
drop table ShapeProperty if exists;
drop table SubFolder if exists;
drop table RootFolder if exists;
drop table ListValue if exists;
drop table ListProperty if exists;
drop table ObjectType if exists;
drop table Context if exists;
drop table LinkEndDecorator if exists;
drop table LinkTerminusDecorator if exists;
drop table LinkTerminus if exists;
drop table NumberProperty if exists;
drop table RichTextProperty if exists;
drop table TextProperty if exists;
drop table Property if exists;
drop table Label if exists;
drop table Link if exists;
drop table Shape if exists;
drop table Canvas if exists;
drop table MapDiagram if exists;
drop table Folder if exists;
drop table Repository if exists;

