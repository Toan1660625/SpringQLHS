--Cau 1:
CREATE OR REPLACE TRIGGER TRIG_AFTER_INS_STUDENT 
AFTER INSERT ON STUDENT
FOR EACH ROW
BEGIN 
  Dbms_Output.Put_Line('Da them hoc sinh moi:'|| :NEW.LAST_NAME ||' '|| :NEW.FIRST_NAME);
END;

--Cau 2:
CREATE OR REPLACE TRIGGER TRIG_DEL_STUDENT 
BEFORE DELETE ON STUDENT
FOR EACH ROW 
BEGIN
  DELETE FROM SCORE
  WHERE STUDENT_ID = :OLD.ID;
  Dbms_Output.Put_Line('ID hoc sinh vua xoa:' || :OLD.ID);
END;

--Cau 3:
CREATE OR REPLACE TRIGGER TRIG_AFTER_INS_STUDENT_UPDATE_FACULTY 
FOR INSERT OR UPDATE ON STUDENT
COMPOUND TRIGGER

  v_faculty_id STUDENT.FACULTY_ID%TYPE;
  count_a NUMBER;
  
AFTER EACH ROW IS
BEGIN
  v_faculty_id := :NEW.FACULTY_ID;
END AFTER EACH ROW;
   
AFTER STATEMENT IS
BEGIN
  SELECT COUNT(*)
  INTO count_a
  FROM STUDENT
  WHERE FACULTY_ID = v_faculty_id;  
  
  UPDATE FACULTY 
  SET QUALITY = count_a
  WHERE ID = v_faculty_id;
  Dbms_Output.Put_Line('So hoc sinh cua khoa vua duoc cap nhat:');
  
END AFTER STATEMENT;
END;


--Cau 4:
CREATE OR REPLACE TRIGGER TRIG_INS_UP_DEL_SCORE 
FOR INSERT OR UPDATE OR DELETE ON SCORE
COMPOUND TRIGGER
  v_student_id STUDENT.ID%TYPE;
AFTER EACH ROW IS
BEGIN
  v_student_id := :NEW.STUDENT_ID;
  IF DELETING THEN
    v_student_id := :OLD.STUDENT_ID;
  END IF;
END AFTER EACH ROW;
   
 AFTER STATEMENT IS
 BEGIN 
  DECLARE
   sum_Tin_Chi FLOAT :=0;
   sum_Diem_TC FLOAT :=0;
   dtb FLOAT :=0;
    CURSOR cur_list IS
      SELECT SCORE.STUDENT_ID, SCORE.SCORE , SUBJECTS.ID,  SUBJECTS.CREDIT_NUM, SUBJECTS.NAME
      FROM SCORE, SUBJECTS
      WHERE SCORE.STUDENT_ID = v_student_id AND SCORE.SUBJECTS_ID = SUBJECTS.ID;
    curSCORE cur_list%ROWTYPE;
        BEGIN
         dbms_output.put_line('Hoc Sinh ID : '||v_student_id);
         OPEN cur_list;
            LOOP
                FETCH cur_list INTO curSCORE;
                EXIT WHEN cur_list%NOTFOUND;
                dbms_output.put_line('Mon hoc : '||curSCORE.NAME);
                dbms_output.put_line('Diem : '||curSCORE.SCORE);
                dbms_output.put_line('------------------------------------');
                sum_Diem_TC :=  sum_Diem_TC + (curSCORE.SCORE*curSCORE.CREDIT_NUM);
                sum_Tin_Chi := sum_Tin_Chi + curSCORE.CREDIT_NUM;
            END LOOP;
            dtb := sum_Diem_TC/sum_Tin_Chi;
            dbms_output.put_line('Diem TB tren TC: '|| dtb);
            CLOSE cur_list;
          END;  
 END AFTER STATEMENT;
END;



--Cau 5:
CREATE OR REPLACE TRIGGER TRIG_INS_SCORE 
BEFORE INSERT ON SCORE
FOR EACH ROW 
DECLARE
  resultcount NUMBER;
  number_sub_exist NUMBER;
BEGIN
  SELECT COUNT(DISTINCT SUBJECTS_ID)
  INTO resultcount
  FROM SCORE
  WHERE STUDENT_ID = :NEW.STUDENT_ID;
  
  SELECT COUNT(*)
    INTO number_sub_exist
    FROM SCORE
    WHERE STUDENT_ID = :NEW.STUDENT_ID
    AND SUBJECTS_ID  = :NEW.SUBJECTS_ID;
  
  IF (resultcount >= 5 AND number_sub_exist = 0) THEN
  RAISE_APPLICATION_ERROR(-20000, 'Hoc sinh chi duoc hoc toi da 5 mon');
  END IF;
END;


-----------------------TEST--------------------------
--NOTE: Neu chay cau 4 thi cau 3 se khong chay duoc, vi khi hoc sinh se xoa diem va chay cau 4 nhung cau 4 khong lay duoc data vao
--SELECT * FROM SCORE
--SELECT * FROM STUDENT
--SELECT * FROM Faculty
--SELECT * FROM Subjects
--
--
--INSERT INTO "STUDENT" (ID, FIRST_NAME, LAST_NAME, SEX, DOB, ADDRESS, FACULTY_ID) VALUES ('11', N'AHIHI', N'Nguyen', N'female', TO_TIMESTAMP('1996-11-06 10:36:34.743000000', 'YYYY-MM-DD HH24:MI:SS.FF'), N'Hau Giang', '1');
--
--INSERT INTO "SCORE" (STUDENT_ID, SUBJECTS_ID, SCORE, TEST_TIME) VALUES ('2', '6', '3', '55');
--
--DELETE FROM SCORE
--WHERE STUDENT_ID = 2 AND SUBJECTS_ID = 1;
--	
--DELETE FROM STUDENT
--WHERE ID = '3';
--
--UPDATE SCORE 
--SET SCORE = 9
--WHERE STUDENT_ID = 2;
--
--DROP TRIGGER TRIG_INS_UP_DEL_SCORE;
