create table LinkEdge (id integer generated by default as identity (start with 1), model_id integer not null, edge_index integer not null, out_node_id integer not null, in_node_id integer not null, link_attribute_id integer not null, owning_child_model_id integer, constraint pk_CompoundEdge primary key (id), constraint xak1compoundedge unique (model_id, edge_index));
create table CompoundNode (id integer generated by default as identity (start with 1), model_id integer, node_index integer not null, parent_id integer, constraint pk_CompoundNode primary key (id), constraint xak1compoundnode unique (model_id, node_index));
create table CompoundGraph (id integer generated by default as identity (start with 1), canvas_id integer not null, root_node_id integer not null, last_node_index integer not null, last_edge_index integer not null, constraint pk_Model primary key (id), constraint xak1model unique (canvas_id));
create table BendPoint (id integer generated by default as identity (start with 1), link_attribute_id integer, index_pos integer not null, x_position integer not null, y_position integer not null, constraint pk_BendPoint primary key (id) );
create table LinkTerminusProperty (link_terminus_id integer not null, property_id integer not null, name varchar(128) not null, constraint pk_LinkTerminusProperty primary key (link_terminus_id, property_id), constraint Alter_Key34 unique (link_terminus_id, property_id), constraint Alter_Key33 unique (link_terminus_id, name), unique (name));
create table ShapeAttributeProperty (shape_attribute_id integer not null, property_id integer not null, name varchar(128) not null, constraint pk_ShapeProperty primary key (shape_attribute_id, property_id), constraint xax1canvasattributeproperty unique (property_id, name));
create table SubFolder (id integer generated by default as identity (start with 1), parent_folder_id integer not null, name varchar(128) not null, description varchar(2048) not null, constraint pk_SubFolder primary key (id), constraint xak1subfolder unique (parent_folder_id, name));
create table RootFolder (id integer generated by default as identity (start with 1), constraint pk_RootFolder primary key (id));
create table ListValue (id integer generated by default as identity (start with 1), list_property_id integer not null, index_pos integer not null, string_representation varchar(4096) not null, constraint pk_ListValue primary key (id), constraint xak1listproperty unique (list_property_id, index_pos));
create table ListProperty (id integer generated by default as identity (start with 1), constraint pk_ListProperty primary key (id));
create table ObjectType (id integer generated by default as identity (start with 1), context_id integer not null, unique_id integer not null, name varchar(128) not null, description varchar(2048) not null, classification_code integer not null, constraint pk_ObjectType primary key (id), constraint xak1objecttype unique (context_id, name));
create table Context (id integer generated by default as identity (start with 1), global_id varchar(256) not null, name varchar(128) not null, description varchar(2048) not null, major_version integer not null, minor_version integer not null, patch_version integer not null, constraint pk_Context primary key (id), constraint xak1Context unique (name, major_version, minor_version, patch_version), unique (global_id), unique (name), unique (major_version), unique (minor_version), unique (patch_version));
create table LinkTerminus (id integer generated by default as identity (start with 1), owning_link_id integer not null, link_term_type smallint not null, offset_val smallint not null, end_dec_type integer not null, end_dec_width integer not null, end_dec_height integer not null, term_dec_type integer not null, term_dec_red integer not null, term_dec_green integer not null, term_dec_blue integer not null, term_dec_height integer not null, term_dec_width integer not null, constraint pk_LinkTerminus primary key (id), constraint xak1LinkTerminus unique (owning_link_id, link_term_type));
create table NumberProperty (id integer generated by default as identity (start with 1), number_value decimal(30, 6) not null, constraint pk_NumberProperty primary key (id));
create table RichTextProperty (id integer generated by default as identity (start with 1), rich_text_value longvarchar not null, constraint pk_RichTextProperty primary key (id));
create table TextProperty (id integer generated by default as identity (start with 1), text_value varchar(4096) not null, constraint pk_TextProperty primary key (id));
create table Property (id integer generated by default as identity (start with 1), canvas_id integer not null, creation_serial integer not null, constraint pk_Property primary key (id), constraint xak1property unique (canvas_id, creation_serial));
create table LabelAttribute (id integer generated by default as identity (start with 1), canvas_id integer, creation_serial integer not null, displayed_property_id integer not null, is_displayed bit not null, background_red integer not null, background_green integer not null, background_blue integer not null, no_fill_set bit not null, x_position integer not null, y_position integer not null, width integer not null, height integer not null, constraint pk_Label primary key (id), constraint xak1labelattribute unique (displayed_property_id));
create table LinkAttribute (id integer generated by default as identity (start with 1), canvas_id integer, creation_serial integer not null, object_type_id integer not null, source_terminus_id integer, target_terminus_id integer, name varchar(128) not null, detailed_description longvarchar not null, description varchar(2048) not null, line_red integer not null, line_green integer not null, line_blue integer not null, line_style integer not null, line_width integer not null, router_type smallint not null, url varchar(2048) not null, constraint pk_Link primary key (id), constraint xak1LinkAttribute unique (canvas_id, creation_serial));
create table ShapeAttribute (id integer generated by default as identity (start with 1), canvas_id integer, creation_serial integer not null, name varchar(128) not null, object_type_id integer not null, description varchar(2048) not null, detailed_description longvarchar not null, fill_red integer not null, fill_green integer not null, fill_blue integer not null, line_red integer not null, line_green integer not null, line_blue integer not null, line_style integer not null, line_width integer not null, padding integer not null, shape_type smallint not null, x_position integer not null, y_position integer not null, height integer not null, width integer not null, url varchar(2048) not null, constraint pk_Shape primary key (id), constraint XAK1ShapeAttribute unique (canvas_id, creation_serial));
create table Canvas (id integer generated by default as identity (start with 1), repository_name varchar(255) not null, map_inode integer not null, last_creation_serial integer not null, context_id integer not null, grid_x integer not null, grid_y integer not null, grid_enabled bit not null, snap_to_grid_enabled bit not null, background_red integer not null, background_green integer not null, background_blue integer not null, canvas_width integer not null, canvas_height integer not null, created date not null, last_modified date not null, constraint pk_Canvas primary key (id), constraint xak1canvas unique (repository_name, map_inode));
create table MapDiagram (id integer generated by default as identity (start with 1), repository_id integer, inode integer not null, folder_id integer not null, name varchar(128) not null, description varchar(2048) not null, constraint pk_MapDiagram primary key (id), constraint xak1mapdiagram unique (folder_id, name));
create table Folder (id integer generated by default as identity (start with 1), inode integer not null, repository_id integer, constraint pk_Folder primary key (id), constraint xak1Folder unique (repository_id, inode));
create table Repository (id integer generated by default as identity (start with 1), name varchar(128) not null, description varchar(2048) not null, build_num integer not null, last_inode integer not null, root_folder_id integer, constraint pk_Repository primary key (id), constraint xak1Repository unique (name));
create table RootObjectNode (id integer generated by default as identity (start with 1), constraint xpkcompoundrootnode primary key (id));
create table ShapeNode (id integer generated by default as identity (start with 1), shape_attribute_id integer not null, constraint xpkcompoundchildnode primary key (id));
create table LabelNode (id integer generated by default as identity (start with 1), label_attribute_id integer not null, primary key (id));
create table ChildCompoundGraph (id integer generated by default as identity (start with 1), root_node_id integer not null, constraint xpk1childmodel primary key (id), constraint xak1childmodel unique (root_node_id));
create table LinkAttributeProperty (link_attribute_id integer not null, property_id integer not null, name varchar(128) not null, primary key (link_attribute_id, property_id), constraint xak1LinkAttributeProperty unique (property_id, name));
alter table MapDiagram add constraint Relationship6 foreign key (folder_id) references Folder;
alter table RootFolder add constraint Relationship58 foreign key (id) references Folder;
alter table SubFolder add constraint Relationship59 foreign key (id) references Folder;
alter table SubFolder add constraint Relationship62 foreign key (parent_folder_id) references Folder;
alter table Property add constraint Relationship77 foreign key (canvas_id) references Canvas;
alter table CompoundGraph add constraint Relationship102 foreign key (canvas_id) references Canvas;
alter table BendPoint add constraint Relationship79 foreign key (link_attribute_id) references LinkAttribute;
alter table LabelAttribute add constraint Relationship49 foreign key (displayed_property_id) references Property;
alter table TextProperty add constraint Relationship23 foreign key (id) references Property;
alter table RichTextProperty add constraint Relationship24 foreign key (id) references Property;
alter table NumberProperty add constraint Relationship29 foreign key (id) references Property;
alter table ListProperty add constraint Relationship52 foreign key (id) references Property;
alter table ShapeAttributeProperty add constraint Relationship74 foreign key (property_id) references Property;
alter table LinkTerminusProperty add constraint Relationship78 foreign key (property_id) references Property;
alter table LinkTerminusProperty add constraint Relationship80 foreign key (link_terminus_id) references LinkTerminus;
alter table Canvas add constraint Relationship50 foreign key (context_id) references Context;
alter table ObjectType add constraint Relationship33 foreign key (context_id) references Context;
alter table ShapeAttribute add constraint Relationship89 foreign key (object_type_id) references ObjectType;
alter table LinkAttribute add constraint Relationship91 foreign key (object_type_id) references ObjectType;
alter table ListValue add constraint Relationship54 foreign key (list_property_id) references ListProperty;
alter table CompoundNode add constraint Relationship104 foreign key (model_id) references CompoundGraph;
alter table LinkEdge add constraint Relationship108 foreign key (model_id) references CompoundGraph;
alter table RootObjectNode add constraint Relationship4 foreign key (id) references CompoundNode;
alter table ShapeNode add constraint Relationship5 foreign key (id) references CompoundNode;
alter table Folder add constraint Relationship2 foreign key (repository_id) references Repository;
alter table MapDiagram add constraint Relationship64 foreign key (repository_id) references Repository;
alter table LabelNode add constraint Relationship243 foreign key (id) references CompoundNode;
alter table LinkEdge add constraint Relationship120 foreign key (out_node_id) references CompoundNode;
alter table LinkEdge add constraint Relationship121 foreign key (in_node_id) references CompoundNode;
alter table CompoundGraph add constraint Relationship148 foreign key (root_node_id) references RootObjectNode;
alter table CompoundNode add constraint Relationship149 foreign key (parent_id) references CompoundNode;
alter table ShapeNode add constraint Relationship150 foreign key (shape_attribute_id) references ShapeAttribute;
alter table LabelNode add constraint Relationship151 foreign key (label_attribute_id) references LabelAttribute;
alter table LinkEdge add constraint Relationship152 foreign key (link_attribute_id) references LinkAttribute;
alter table ChildCompoundGraph add constraint Relationship502 foreign key (root_node_id) references CompoundNode;
alter table LinkEdge add constraint Relationship3 foreign key (owning_child_model_id) references ChildCompoundGraph;
alter table ShapeAttribute add constraint Relationship146 foreign key (canvas_id) references Canvas;
alter table LinkAttribute add constraint Relationship301 foreign key (canvas_id) references Canvas;
alter table ShapeAttributeProperty add constraint Relationship141 foreign key (shape_attribute_id) references ShapeAttribute;
alter table LinkAttributeProperty add constraint Relationship143 foreign key (link_attribute_id) references LinkAttribute;
alter table LinkAttributeProperty add constraint Relationship142 foreign key (property_id) references Property;
alter table LinkAttribute add constraint Relationship144 foreign key (source_terminus_id) references LinkTerminus;
alter table LinkAttribute add constraint Relationship145 foreign key (target_terminus_id) references LinkTerminus;
alter table LinkTerminus add constraint Relationship147 foreign key (owning_link_id) references LinkAttribute;
alter table Repository add constraint Relationship63 foreign key (root_folder_id) references RootFolder;
alter table LabelAttribute add constraint Relationship44 foreign key (canvas_id) references Canvas;

