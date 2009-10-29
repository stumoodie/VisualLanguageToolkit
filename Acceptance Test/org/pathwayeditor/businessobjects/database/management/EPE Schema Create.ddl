create table LinkEdge (id integer generated by default as identity (start with 1), model_id integer not null, edge_index integer not null, out_node_id integer, in_node_id integer, owning_child_model_id integer, is_removed bit not null, link_attribute_id integer, constraint pk_CompoundEdge primary key (id), constraint xak1compoundedge unique (model_id, edge_index));
create table CompoundNode (id integer generated by default as identity (start with 1), model_id integer not null, node_index integer not null, parent_id integer, is_removed bit not null, constraint pk_CompoundNode primary key (id));
create table CompoundGraph (id integer generated by default as identity (start with 1), canvas_id integer not null, root_node_id integer, last_node_index integer not null, last_edge_index integer not null, constraint pk_Model primary key (id), constraint xak1model unique (canvas_id));
create table BendPoint (id integer generated by default as identity (start with 1), creation_serial integer not null, index_pos integer not null, x_position double not null, y_position double not null, link_attribute_id integer, constraint pk_BendPoint primary key (id), constraint xak1BendPoint unique (link_attribute_id, creation_serial));
create table SubFolder (id integer not null, parent_folder_id integer not null, name varchar(128) not null, description varchar(2048) not null, constraint pk_SubFolder primary key (id), constraint xak1subfolder unique (parent_folder_id, name));
create table RootFolder (id integer not null, constraint pk_RootFolder primary key (id));
create table ListValue (id integer generated by default as identity (start with 1), list_property_id integer not null, index_pos integer not null, string_representation varchar(4096) not null, constraint pk_ListValue primary key (id), constraint xak1listproperty unique (list_property_id, index_pos));
create table ListProperty (id integer not null, constraint pk_ListProperty primary key (id));
create table ObjectType (id integer generated by default as identity (start with 1), notation_id integer not null, unique_id integer not null, name varchar(128) not null, description varchar(2048) not null, classification_code integer not null, constraint pk_ObjectType primary key (id), constraint xak1objecttype unique (notation_id, name));
create table Notation (id integer generated by default as identity (start with 1), qualified_name varchar(512) not null, name varchar(128) not null, description varchar(2048) not null, major_version integer not null, minor_version integer not null, patch_version integer not null, constraint pk_Context primary key (id), constraint xak1Context unique (name, major_version, minor_version, patch_version));
create table LinkTerminus (id integer not null, link_term_type smallint not null, offset_val double not null, end_dec_type integer not null, end_dec_width double not null, end_dec_height double not null, owning_link_id integer, x_position double default 0 not null, y_position double default 0 not null, constraint pk_LinkTerminus primary key (id));
create table NumberProperty (id integer not null, number_value decimal(30, 6), constraint pk_NumberProperty primary key (id));
create table RichTextProperty (id integer not null, rich_text_value longvarchar, constraint pk_RichTextProperty primary key (id));
create table TextProperty (id integer not null, text_value varchar(4096), constraint pk_TextProperty primary key (id));
create table Property (id integer generated by default as identity (start with 1), canvas_attribute_id integer not null, displayed_label_id integer, name varchar(255) not null, display_name varchar(1024) default '"A Display"' not null, constraint pk_Property primary key (id));
create table LabelAttribute (id integer not null, background_red integer not null, background_green integer not null, background_blue integer not null, y_position double not null, x_position double not null, width double not null, height double not null, foreground_red integer default 255 not null, foreground_blue integer default 255 not null, foreground_green integer default 255 not null, line_width double default 1.0 not null, line_style integer default 1 not null, no_border bit default 'true' not null, no_fill bit default 'true' not null, shape_defn longvarchar, current_node_id integer, constraint pk_Label primary key (id));
create table LinkAttribute (id integer not null, last_bendpoint_serial integer not null, object_type_id integer not null, line_red integer not null, line_green integer not null, line_blue integer not null, line_style integer not null, line_width integer not null, current_edge_id integer, constraint pk_Link primary key (id));
create table ShapeAttribute (id integer not null, object_type_id integer not null, fill_red integer not null, fill_green integer not null, fill_blue integer not null, line_red integer not null, line_green integer not null, line_blue integer not null, line_style integer not null, line_width double not null, shape_defn longvarchar not null, x_position double not null, y_position double not null, height double not null, width double not null, current_node_id integer, constraint pkShapeAttribute primary key (id));
create table Canvas (id integer generated by default as identity (start with 1), name varchar(255) not null, repository_name varchar(255) not null, map_inode integer not null, last_creation_serial integer not null, notation_id integer not null, grid_x double not null, grid_y double not null, grid_enabled bit not null, snap_to_grid_enabled bit not null, background_red integer not null, background_green integer not null, background_blue integer not null, canvas_width double not null, canvas_height double not null, constraint pk_Canvas primary key (id), constraint xak1canvas unique (repository_name, map_inode));
create table MapDiagram (id integer generated by default as identity (start with 1), repository_id integer, inode integer not null, folder_id integer not null, name varchar(128) not null, description varchar(2048) not null, constraint pk_MapDiagram primary key (id), constraint xak1mapdiagram unique (folder_id, name));
create table Folder (id integer generated by default as identity (start with 1), inode integer not null, repository_id integer, constraint pk_Folder primary key (id), constraint xak1Folder unique (repository_id, inode));
create table Repository (id integer generated by default as identity (start with 1), name varchar(128) not null, description varchar(2048) not null, build_num integer not null, last_inode integer not null, root_folder_id integer, constraint pk_Repository primary key (id), constraint xak1Repository unique (name));
create table RootObjectNode (id integer not null, root_attribute_id integer not null, constraint xpkcompoundrootnode primary key (id));
create table ShapeNode (id integer not null, shape_attribute_id integer, constraint xpkcompoundchildnode primary key (id));
create table LabelNode (id integer not null, label_attribute_id integer, primary key (id));
create table ChildCompoundGraph (id integer generated by default as identity (start with 1), root_node_id integer, constraint xpk1childmodel primary key (id), constraint xak1childmodel unique (root_node_id));
create table CanvasAttribute (id integer generated by default as identity (start with 1), canvas_id integer, creation_serial integer not null, primary key (id), constraint xak1CanvasAttribute unique (canvas_id, creation_serial));
create table AnnotatedCanvasAttribute (id integer not null, constraint xpkCanvasAnnotatedProperty primary key (id));
create table RootAttribute (id integer not null, object_type_id integer not null, constraint xpk1RootAttribute primary key (id));
create table BooleanProperty (id integer not null, boolean_value bit, primary key (id));
create table IntegerProperty (id integer not null, integer_value integer, primary key (id));
alter table MapDiagram add constraint R6 foreign key (folder_id) references Folder (id);
alter table RootFolder add constraint R58 foreign key (id) references Folder (id);
alter table SubFolder add constraint R59 foreign key (id) references Folder (id);
alter table SubFolder add constraint R62 foreign key (parent_folder_id) references Folder (id);
alter table CompoundGraph add constraint R102 foreign key (canvas_id) references Canvas (id);
alter table BendPoint add constraint R79 foreign key (link_attribute_id) references LinkAttribute (id);
alter table TextProperty add constraint R23 foreign key (id) references Property (id);
alter table RichTextProperty add constraint R24 foreign key (id) references Property (id);
alter table NumberProperty add constraint R29 foreign key (id) references Property (id);
alter table ListProperty add constraint R52 foreign key (id) references Property (id);
alter table Canvas add constraint R50 foreign key (notation_id) references Notation (id);
alter table ObjectType add constraint R33 foreign key (notation_id) references Notation (id);
alter table ShapeAttribute add constraint R89 foreign key (object_type_id) references ObjectType (id);
alter table LinkAttribute add constraint R91 foreign key (object_type_id) references ObjectType (id);
alter table ListValue add constraint R54 foreign key (list_property_id) references ListProperty (id);
alter table CompoundNode add constraint R104 foreign key (model_id) references CompoundGraph (id);
alter table LinkEdge add constraint R108 foreign key (model_id) references CompoundGraph (id);
alter table RootObjectNode add constraint R4 foreign key (id) references CompoundNode (id);
alter table ShapeNode add constraint R5 foreign key (id) references CompoundNode (id);
alter table Folder add constraint R2 foreign key (repository_id) references Repository (id);
alter table MapDiagram add constraint R64 foreign key (repository_id) references Repository (id);
alter table LabelNode add constraint R243 foreign key (id) references CompoundNode (id);
alter table LinkEdge add constraint R120 foreign key (out_node_id) references CompoundNode (id);
alter table LinkEdge add constraint R121 foreign key (in_node_id) references CompoundNode (id);
alter table CompoundGraph add constraint R148 foreign key (root_node_id) references RootObjectNode (id);
alter table CompoundNode add constraint R149 foreign key (parent_id) references CompoundNode (id);
alter table ShapeNode add constraint R150 foreign key (shape_attribute_id) references ShapeAttribute (id);
alter table LabelNode add constraint R151 foreign key (label_attribute_id) references LabelAttribute (id);
alter table LinkEdge add constraint R11 foreign key (link_attribute_id) references LinkAttribute (id);
alter table ChildCompoundGraph add constraint R502 foreign key (root_node_id) references CompoundNode (id);
alter table LinkEdge add constraint R3 foreign key (owning_child_model_id) references ChildCompoundGraph (id);
alter table LinkTerminus add constraint R147 foreign key (owning_link_id) references LinkAttribute (id);
alter table Repository add constraint R63 foreign key (root_folder_id) references RootFolder (id);
alter table CanvasAttribute add constraint R10 foreign key (canvas_id) references Canvas (id);
alter table LinkAttribute add constraint R9 foreign key (id) references CanvasAttribute (id);
alter table LinkTerminus add constraint R12 foreign key (id) references CanvasAttribute (id);
alter table AnnotatedCanvasAttribute add constraint R13 foreign key (id) references CanvasAttribute (id);
alter table ShapeAttribute add constraint R14 foreign key (id) references AnnotatedCanvasAttribute (id);
alter table Property add constraint R1 foreign key (canvas_attribute_id) references AnnotatedCanvasAttribute (id);
alter table LabelAttribute add constraint R8 foreign key (id) references CanvasAttribute (id);
alter table RootAttribute add constraint FKRootAttrib985728 foreign key (id) references CanvasAttribute (id);
alter table RootObjectNode add constraint R15 foreign key (root_attribute_id) references RootAttribute (id);
alter table RootAttribute add constraint R16 foreign key (object_type_id) references ObjectType (id);
alter table Property add constraint R25 foreign key (displayed_label_id) references LabelAttribute (id);
alter table BooleanProperty add constraint R130 foreign key (id) references Property (id);
alter table IntegerProperty add constraint R131 foreign key (id) references Property (id);
alter table ShapeAttribute add constraint FKShapeAttri651535 foreign key (current_node_id) references ShapeNode (id);
alter table LinkAttribute add constraint R19 foreign key (current_edge_id) references LinkEdge (id);
alter table LabelAttribute add constraint R26 foreign key (current_node_id) references LabelNode (id);
