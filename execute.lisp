(table_schema_name
   (_modify_current_table (_create_table (_index_terminate 1))))

(column
   (_modify_current_column (_create_column (_index_terminate 1))))

(type_name
   (_modify_current_type (_create_type (_index_terminate 1))))

(size
   (_create_size (_index_terminate 2)))

(string
   (_modify_current_string (_index_terminate 1)))

(string_add_oper
   (_modify_current_string (_add_string (_index_non_terminate 1))))

(execute_key_word
   (_execute_current_string (_execute_non_terminate (_index_non_terminate 1))))
