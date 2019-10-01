package lexicalAnalyzer;

public enum TokenType {
    none,
    ERROR,
    //nuevos
    tk_id,
    tk_eof,
    tk_cadena,
    //Reserved words

    tk_P,
    tk_V,
    tk_af,
    tk_and,
    tk_any,
    tk_begin,
    tk_body,
    tk_bool,
    tk_by,
    tk_call,
    tk_cap,
    tk_char,
    tk_co,
    tk_const,
    tk_create,
    tk_destroy,
    tk_do,
    tk_downto,
    tk_else,
    tk_end,
    tk_enum,
    tk_exit,
    tk_extend,
    tk_external,
    tk_fa,
    tk_false,
    tk_fi,
    tk_file,
    tk_final,
    tk_forward,
    tk_global,
    tk_high,
    tk_if,
    tk_import,
    tk_in,
    tk_initial,
    tk_int,
    tk_low,
    tk_mod,
    tk_new,
    tk_next,
    tk_ni,
    tk_noop,
    tk_not,
    tk_null,
    tk_oc,
    tk_od,
    tk_on,
    tk_op,
    tk_optype,
    tk_or,
    tk_proc,
    tk_procedure,
    tk_process,
    tk_ptr,
    tk_read,
    tk_real,
    tk_rec,
    tk_receive,
    tk_ref,
    tk_reply,
    tk_res,
    tk_resource,
    tk_return,
    tk_returns,
    tk_sem,
    tk_send,
    tk_separate,
    tk_skip,
    tk_st,
    tk_stderr,
    tk_stdin,
    tk_stdout,
    tk_stop,
    tk_string,
    tk_to,
    tk_true,
    tk_type,
    tk_union,
    tk_val,
    tk_var,
    tk_vm,
    tk_write,
    tk_xor,

    //Symbols
    tk_semicolon,
    tk_comma,
    tk_colon,
    tk_eq,
    tk_incr,
    tk_plus,
    tk_decr,
    tk_minus,
    tk_aster,
    tk_expon,
    tk_div,
    tk_remdr,
    tk_lparen,
    tk_rparen,
    tk_arrow,
    tk_square,
    tk_assign,
    tk_swap,
    tk_lbracket,
    tk_rbracket,
    tk_ge,
    tk_le,
    tk_gt,
    tk_lt,
    tk_ne,
    //tk_ne,     (~=)
    //tk_or,      (|)
    //tk_and,     (&)
    tk_period,
    //tk_not,     (!)
    tk_addr,
    tk_hat,
    tk_concat,
    tk_lbrace,
    tk_rbrace,
    tk_qmark,
    tk_parallel,
    tk_rshift,
    tk_lshift,
    tk_aug_plus,
    tk_aug_minus,
    tk_aug_aster,
    tk_aug_expon,
    tk_aug_div,
    tk_aug_remdr,
    tk_aug_or,
    tk_aug_and,
    tk_aug_concat,
    tk_aug_rshift,
    tk_aug_lshift,


    tk_num_real,
    tk_num_int_oct,
    tk_num_int_dec,
    tk_num_int_hex,






}
