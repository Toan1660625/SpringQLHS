--Câu 1:
CREATE OR REPLACE FUNCTION FACT(
    NBER IN NUMBER )
  RETURN NUMBER
IS
  giai_thua NUMBER := 1;
  i         NUMBER := 1;
BEGIN
 WHILE(i <= NBER)
  LOOP
    giai_thua := giai_thua * i;
    i := i + 1;
  END LOOP;
RETURN giai_thua;
END FACT;

--Câu 2:
CREATE OR REPLACE PROCEDURE FIND_NAME(
    in_student_id IN STUDENT.ID%TYPE,
    out_first_name OUT STUDENT.FIRST_NAME%TYPE,
    out_last_name OUT STUDENT.LAST_NAME%TYPE)
AS
BEGIN
  SELECT FIRST_NAME,LAST_NAME
  INTO out_first_name,out_last_name
  FROM STUDENT
  WHERE ID = in_student_id;
EXCEPTION
WHEN No_Data_Found THEN
  Dbms_Output.Put_Line('Khong tim thay student id = ' || in_student_id);
END FIND_NAME;

--Câu 3:
CREATE OR REPLACE PROCEDURE PRINT_STUDENT_NAME(
    in_student_id IN STUDENT.ID%TYPE)
IS
 first_name STUDENT.FIRST_NAME%TYPE;
BEGIN
  SELECT FIRST_NAME
  INTO first_name
  FROM STUDENT
  WHERE ID = in_student_id;
  
  Dbms_Output.Put_Line('Câu 3: Ten : ' || first_name);
EXCEPTION
WHEN No_Data_Found THEN
  Dbms_Output.Put_Line('Khong tim thay student id = ' || in_student_id);
  
END;

--Câu 4:
CREATE OR REPLACE PROCEDURE UPDATE_SCORE(
    in_address IN STUDENT.ADDRESS%TYPE,
    in_sex IN STUDENT.SEX%TYPE,
    in_faculty_name IN STUDENT.FACULTY_ID%TYPE)
AS
BEGIN
  UPDATE SCORE
  SET SCORE = SCORE + 1
  WHERE STUDENT_ID IN ( SELECT ID
                        FROM STUDENT
                        WHERE SEX = in_sex and ADDRESS = in_address and FACULTY_ID = in_faculty_name);
  Dbms_Output.Put_Line('Update  !!'||in_sex||in_address||in_faculty_name);
EXCEPTION
WHEN No_Data_Found THEN
  Dbms_Output.Put_Line('Update that bai !!');
END;

--TEST
DECLARE
  v_first_name STUDENT.FIRST_NAME%TYPE;
  v_last_name STUDENT.LAST_NAME%TYPE;
BEGIN
  Dbms_Output.Put_Line('Cau 1 :'||FACT(7));
  FIND_NAME(1, v_first_name, v_last_name);
  Dbms_Output.Put_Line('Cau 2 :'||'H? tên h?c sinh tìm th?y:' || v_first_name || ' ' ||v_last_name);
  PRINT_STUDENT_NAME(2);
  UPDATE_SCORE('C?n Th?','female','1');
END;

