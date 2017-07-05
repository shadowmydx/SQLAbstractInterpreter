(execute_stmt
  (execute_key_word
      (string
             ';')))
(string
    (NORMAL_STRING
       (string_add_oper string))
    (NORMAL_STRING))

(string_add_oper
   '+')
(execute_key_word
   EXECUTE)

(create_table_stmt
  (create_table
        ('('
           (columns
              (')'
                ';')))))

(create_table
   (CREATE (TABLE table_name)))

(table_name
   (user_name
     ('.' table_schema_name))
   (table_schema_name))

(table_schema_name
  TABLE_NAME)

(user_name
  USER_NAME)

(columns
   (column
       (','
           columns))
   (column))

(column
   (COLUMN_NAME
      (column_type)))

(column_type
  (type_name
       size)
  (type_name))

(size
   ('(' (DIGIT ')')))

(type_name
    VARCHAR
    VARCHAR2
    NUMBER
    DATE)



