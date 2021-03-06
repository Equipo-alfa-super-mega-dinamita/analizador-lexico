component:
	    ¿
	|   spec_component     {tk_semicolon}
	|   combined_component {tk_semicolon}
	|   separate_body      {tk_semicolon}
	|   error
	;

spec_component:
	    comp_label spec_stmt_ls spec_body
	;

combined_component:
	    combined_specpart body_stmt_ls end_id
	;

combined_specpart:
	    comp_label comp_params
	;

comp_label:
	    comp_kwd {tk_id}
	;

comp_kwd:
	    {tk_global}
	|   {tk_resource}
	;

spec_body:
	    end_id
	|   {tk_body}   {tk_id} maybe_params spec_body2
	;

spec_body2:
		body_stmt_ls end_id
	|	{tk_separate}
	;

maybe_params:
	    ¿
	|   comp_params
	;

comp_params:
	    parameters
	;

separate_body:
	    {tk_body}   {tk_id} body_stmt_ls end_id;

//===================    spec body contents  ==================================
spec_stmt_ls:
	    spec_stmt spec_stmt_ls2
	;

spec_stmt_ls2:
		{tk_semicolon} spec_stmt spec_stmt_ls2
	;


spec_stmt:
	    common_stmt
	|   extend_clause
	|   body_only
	;


body_stmt_ls:
	    body_stmt
	|   body_stmt_ls {tk_semicolon} body_stmt
	;

body_stmt:
	    common_stmt
	|   expr
	|   body_only
	|   extend_clause
	;

body_only:
	    stmt
	|   proc
	|   process
	|   procedure
	|   initial_block
	|   final_block
	;


common_stmt:
	    ¿
	|   decl
	|   import_clause
	;


import_clause:
	    {tk_import} import_list
	;

extend_clause:
	    {tk_extend} import_list
	;

import_list:
	    import_name import_list2
	;

import_list2:
		{tk_comma} import_name import_list2
	|	¿
	;

import_name:
	    {tk_id}
	;

//===================  Top level body statements=======================
op_decl:
	    op_or_ext oper_def_lp
	;

op_or_ext:
	    {tk_op}
	|   {tk_external}
	;

oper_def_lp:
	    oper_def oper_def_lp2
	;

oper_def_lp2:
	  	{tk_comma} oper_def oper_def_lp2
	;

oper_def:
	    id_subs_lp oper_def2
	;

oper_def2:
		op_prototype
	|	colon_opt qualified_id
	;
colon_opt:
	    ¿
	|   {tk_colon}
	;


sem_decl:
	    {tk_sem} sem_def_lp
	;

sem_def_lp:
	    sem_def sem_def_lp2
	;

sem_def_lp2:
	    {tk_comma} sem_def sem_def_lp2
	|	¿
	;


sem_def:
	    id_subs sem_proto sem_init
	;

sem_proto:
	    return_spec_null
	;

sem_init:
	    ¿
	|   {tk_assign} expr
	;



proc:
	    {tk_proc} {tk_id} param_names block end_id
	;


procedure:
	    {tk_procedure} {tk_id} prototype block end_id
	;


process:
	    {tk_process} {tk_id} return_spec_null quantifiers_opt block end_id
	;


initial_block:
	    {tk_initial} block {tk_end} initial_opt
	;

initial_opt:
	    ¿
	|   {tk_initial}
	;


final_block:
	    {tk_final} block {tk_end} final_opt
	;

final_opt:
	    ¿
	|   {tk_final}
	;

//=============== Parameters  ==============================
prototype:
	    parameters return_spec_opt
	;

parameters:
	    {tk_lparen} param_spec_ls {tk_rparen}
	    ;

param_spec_ls:
	    ¿
	|   param_spec_lp
	;

param_spec_lp:
	    param_spec param_spec_lp2
	;

param_spec_lp2:
	{tk_semicolon} param_spec_lp3
	| ¿
	;

param_spec_lp3:
	{tk_semicolon} param_spec_lp
	| ¿
	;

param_spec:
	    param_kind_opt param_spec2
	;

param_spec2:
	    type
	|   id_subs_lp {tk_colon} type
	;

param_kind_opt:
	    ¿
	|   {tk_val}
	|   {tk_var}
	|   {tk_res}
	|   {tk_ref}
	;

return_spec_opt:
	    return_spec_null
	|   {tk_returns} return_spec_opt2
	;

return_spec_opt2:
	    type
	|   id_subs {tk_colon} type
	|   {tk_id} {tk_bogus}
	;


return_spec_null:
	    ¿
	;

param_names:
	    {tk_lparen} id_ls {tk_rparen} return_name_opt
	;

return_name_opt:
	    ¿
	|   {tk_returns} {tk_id}
	;
//======== Declaration ========

decl:
	    error {tk_semicolon}
	|   type_decl
	|   obj_decl
	|   optype_decl
	|   sem_decl
	|   op_decl
	;


type_decl:
	    {tk_type} {tk_id} {tk_eq} type type_restriction
	;

type_restriction:
	    ¿
	|   {tk_lbrace} {tk_id} {tk_rbrace}
	;

obj_decl:
	    var_or_const var_def_lp
	;

var_or_const:
	    {tk_var}
	|   {tk_const}
	;

var_def_lp:
	    var_def var_def_lp2
	;

var_def_lp2:
	    {tk_comma} var_def var_def_lp2
	|	¿
	;

var_def:
	    id_subs_lp var_att
	;

var_att:
	    {tk_colon} type var_att2
	|   {tk_assign} expr
	|   {tk_semicolon}
	;

var_att2:
	    ¿
	|   {tk_assign} expr
	;

//============= Type especification =================
type:
	    subscripts unsub_type
	|   unsub_type
	;

unsub_type:
	    basic_type
	|   string_def
	|   enum_def
	|   pointer_def
	|   record_def
	|   union_def
	|   capability_def
	|   qualified_id
	;

basic_type:
	    {tk_bool}
	|   {tk_char}
	|   {tk_int}
	|   {tk_file}
	|   {tk_real}
	;

string_def:
	    {tk_string} string_def_p
	;
string_def_p:
	{tk_lbracket} string_lim {tk_rbracket}
        |{tk_lparen} string_lim {tk_rparen};
string_lim:
	    expr
	|   {tk_aster}
	;

enum_def:
	    {tk_enum}   {tk_lparen} id_lp {tk_rparen}
	;

pointer_def:
	    {tk_ptr}   pointer_def_p
	;
pointer_def_p:
	type
        | {tk_any};
record_def:
	    {tk_rec} {tk_lparen} field_lp {tk_rparen}
	;

union_def:
	    {tk_union} {tk_lparen} field_lp {tk_rparen}
	;

field_lp:
	    field field_lp_p
	;
field_lp_p: {tk_semicolon} field_lp_p_p
          | ¿;
field_lp_p_p: field_lp
            | ¿;
field:
	    var_def_lp
	;

capability_def:
	   {tk_cap} cap_for
	;

cap_for:
	    qualified_id
	|   op_prototype
	|   {tk_sem} sem_proto
	|   {tk_vm}
	;

//===========  Optype  ==============
optype_decl:
	    {tk_optype}   {tk_id} eq_opt op_prototype
	;

op_prototype:
	    prototype op_restriction_opt
	;

eq_opt:
	    ¿
	|   {tk_eq}
	;

op_restriction_opt:
	 {tk_lbrace} op_restriction {tk_rbrace}
	;

op_restriction:
	    {tk_call} op_restriction_p
	|   {tk_send} op_restriction_p1
	;
op_restriction_p: {tk_comma} {tk_send}
                | ¿;
op_restriction_p1: {tk_comma} {tk_call}
                | ¿;

//================ Blocks and Statements  ==================
block:
	    block_items
	;

block_items:
	    block_item block_items_p
	;
block_items_p: {tk_semicolon} block_item block_items_p
             |¿;
block_item:
	    ¿
	|   decl
	|   stmt
	|   expr
	|   import_clause
	;

stmt:
	    skip_stmt
	|   stop_stmt
	|   exit_stmt
	|   next_stmt
	|   return_stmt
	|   reply_stmt
	|   forward_stmt
	|   send_stmt
	|   explicit_call
	|   destroy_stmt
	|   begin_end
	|   if_stmt
	|   do_stmt
	|   for_all_stmt
	|   V_stmt
	|   input_stmt
	|   receive_stmt
	|   P_stmt
	|   concurrent_stmt
	;

skip_stmt:
	    {tk_skip}
	;

stop_stmt:
	    {tk_stop} exit_code_opt
	;

exit_code_opt:
	    ¿
	|   {tk_lparen} expr {tk_rparen}
	;

exit_stmt:
	    {tk_exit}
	;

next_stmt:
	    {tk_next}
	;

return_stmt:
	    {tk_return}
	;

reply_stmt:
	    {tk_reply}
	;

forward_stmt:
	    {tk_forward} invocation
	;

send_stmt:
	    {tk_send} invocation
	;

receive_stmt:
	    {tk_receive} expr paren_list
	;

V_stmt:
	    {tk_V}   {tk_lparen} expr {tk_rparen}
	;

P_stmt:
	    {tk_P}   {tk_lparen} expr {tk_rparen}
	;

explicit_call:
	    {tk_call} invocation
	;

destroy_stmt:
	    {tk_destroy} expr
	;

begin_end:
	    {tk_begin} block {tk_end}
	;

if_stmt:
	    {tk_if} guarded_cmd_lp else_cmd_opt {tk_fi}
	;

do_stmt:
	    {tk_do} guarded_cmd_lp else_cmd_opt {tk_od}
	;

guarded_cmd_lp:
	    guarded_cmd guarded_cmd_lp_p
	;
guarded_cmd_lp_p: {tk_square} guarded_cmd guarded_cmd_lp_p
                | ¿ ;
guarded_cmd:
	    expr {tk_arrow} block
	;

else_cmd_opt:
	    ¿
	|   {tk_square} {tk_else} {tk_arrow} block
	;

for_all_stmt:
	    {tk_fa}   quantifier_lp {tk_arrow} block {tk_af}
	;

//===== input statement =========
input_stmt:
	    {tk_in}   in_cmd_lp else_cmd_opt {tk_ni}
	;

in_cmd_lp:
	    in_cmd in_cmd_lp_p
	;
in_cmd_lp_p: {tk_square} in_cmd in_cmd_lp_p
|¿;

in_cmd:
	    quantifiers_opt in_spec sync_expr_opt sched_expr_opt {tk_arrow} block
	;

in_spec:
	    in_op param_names
	;

in_op:
	    qualified_id
	|   qualified_id subscripts
	;

sync_expr_opt:
	    ¿
	|   {tk_and} expr
	|   {tk_suchthat} expr
	;

sched_expr_opt:
	    ¿
	|   {tk_by} expr
	;

//======== co statement =========
concurrent_stmt:
	    {tk_co}   concurrent_cmd_lp {tk_oc}
	;

concurrent_cmd_lp:
	    concurrent_cmd concurrent_cmd_lp_p
	;
concurrent_cmd_lp_p: {tk_parallel} concurrent_cmd concurrent_cmd_lp_p
                   | ¿;
concurrent_cmd:
	quantifiers_opt separator_opt concurrent_invocation post_processing_opt
	;

separator_opt:
	    separator_opt_p
	;
separator_opt_p: {tk_semicolon} separator_opt_p
               |¿ ;

concurrent_invocation:
	    explicit_call
	|   send_stmt
	|   expr
	;

post_processing_opt:
	    ¿
	|   {tk_arrow} block
	;
//========== quantifier ===========
quantifiers_opt:
	    ¿
	|   {tk_lparen} quantifier_lp {tk_rparen}
	;

quantifier_lp:
	    quantifier quantifier_lp_p
	;
quantifier_lp_p: {tk_comma} quantifier quantifier_lp_p
               |¿;
quantifier:
	    {tk_id} {tk_assign} expr direction expr step_opt such_that_opt
	;

direction:
	    {tk_to}
	|   {tk_downto}
	;

step_opt:
	    ¿
	|   {tk_by} expr
	;

such_that_opt:
	    ¿
	|   {tk_suchthat} expr
	;
//======== expression =============
expr:
	    {tk_id}
	|   literal
	|   invocation
	|   constructor
	|   binary_expr
	|   prefix_expr
	|   suffix_expr
	|   create_expr
	;

literal:
	    {tk_num_int_dec}
	|   {tk_num_real}
	|   {tk_bool}
	|   {tk_char}
	|   {tk_cadena}
	|   {tk_null}
	|   {tk_file}
	;

binary_expr: expr binary_expr_p;

binary_expr_p: {tk_expon} expr
	|    {tk_aster}	expr
	|    {tk_div} expr
	|    {tk_mod} expr
	|    {tk_remdr}	expr
	|    {tk_plus}	expr
	|    {tk_minus}	expr
	|    {tk_concat} expr
	|    {tk_eq} expr
	|    {tk_ne} expr
	|    {tk_ge} expr
	|    {tk_le} expr
	|    {tk_gt} expr
	|    {tk_lt} expr
	|    {tk_and} expr
	|    {tk_or} expr
	|    {tk_xor} expr
	|    {tk_rshift} expr
	|    {tk_lshift} expr
	|    {tk_swap}	expr
	|    {tk_assign} expr
	|    {tk_aug_plus}	expr
	|    {tk_aug_minus}	expr
	|    {tk_aug_aster}	expr
	|    {tk_aug_div} expr
	|    {tk_aug_remdr}	expr
	|    {tk_aug_expon}	expr
	|    {tk_aug_or} expr
	|    {tk_aug_and} expr
	|    {tk_aug_concat} expr
	|    {tk_aug_rshift} expr
	|    {tk_aug_lshift} expr
	;

prefix_expr:
	    {tk_not}	expr
	|   {tk_plus}	expr
	|   {tk_minus}	expr
	|   {tk_addr}	expr
	|   {tk_qmark}	expr
	|   {tk_incr} expr
	|   {tk_decr} expr
	|   basic_type paren_expr
	|   {tk_string} paren_expr
	|   {tk_low}    {tk_lparen} type {tk_rparen}
	|   {tk_high}   {tk_lparen} type {tk_rparen}
	|   {tk_new}   {tk_lparen} subscripts_opt new_item {tk_rparen}
	;

new_item:
	    unsub_type
	|   {tk_sem} sem_proto
	|   {tk_op} op_prototype
	;

paren_expr:
	    {tk_lparen} expr {tk_rparen}
	;

suffix_expr: expr suffix_expr_P;
suffix_expr_P:
        {tk_incr}
	|    {tk_decr}
	|    {tk_hat}
	|    {tk_period} {tk_id}
	|    {tk_lbracket} bound_lp {tk_rbracket}
	;
invocation:
	    expr paren_list
	;

paren_list:
	    {tk_lparen} paren_item_ls {tk_rparen}
	;

paren_item_ls:
	    ¿
	|   expr_lp
	;

expr_lp:
	    expr expr_lp_p
	;
expr_lp_p: {tk_comma} expr expr_lp_p
         | ¿;

constructor:
	    {tk_lparen} constr_item_lp {tk_rparen}
	;

constr_item_lp:
	    constr_item constr_item_lp_p
	;
constr_item_lp_p: {tk_comma} constr_item constr_item_lp_p
                |¿ ;

constr_item:
	    expr
	|   {tk_lbracket} expr {tk_rbracket} expr
	;


create_expr:
	    {tk_create}   create_call location_opt
	;

create_call:
	    rsrc_name paren_list
	;

rsrc_name:
	    {tk_id}
	|   {tk_vm}
	;

location_opt:
	    ¿
	|   {tk_on} expr
	;
//====== miscelaneous=========
qualified_id: {tk_id} qualified_id_P;

qualified_id_P: ¿
        | {tk_period} {tk_id};

end_id:
        {tk_end}   id_opt
    ;

id_opt:
        ¿
    |   {tk_id}
    ;

id_ls:
        ¿
    |   id_lp
    ;

id_lp:
        {tk_id}
    |   id_lp {tk_comma} {tk_id}
    ;

id_subs_lp:
	    id_subs	id_subs_lp2
	;

id_subs_lp2:
		{tk_comma} id_subs id_subs_lp2
		| ¿
	;

id_subs:
	    {tk_id} id_subs2
	;

id_subs2:
 	   ¿
	|   subscripts
	;

subscripts:
        bracketed_list subscripts_P
    	;
subscripts_P:
	¿
        | subscripts
        ;


subscripts_opt:
        ¿
    |   subscripts
    ;

bracketed_list:
        {tk_lbracket} bound_lp {tk_rbracket}
    ;

bound_lp:
        bounds bound_lp_p
    ;
bound_lp_p:
 {tk_comma} bounds bound_lp_p
  	| ¿;
bounds:
     bound bounds_P
    ;
bounds_P:
    ¿
    | {tk_colon} bound;

bound:
        expr
    |   {tk_aster}
    ;