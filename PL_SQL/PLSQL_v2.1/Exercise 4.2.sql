--Câu 1 :
DECLARE
  CURSOR STUDENT_CUR(param_faculty_id STUDENT.FACULTY_ID%TYPE)
  IS
    SELECT first_name,last_name, address FROM STUDENT WHERE FACULTY_ID = param_faculty_id;
  v_student STUDENT_CUR%Rowtype;
  faculty_id CHAR := &MaKhoa;
BEGIN
  OPEN STUDENT_CUR(faculty_id);
  LOOP
    FETCH STUDENT_CUR INTO v_student;
    EXIT
  WHEN STUDENT_CUR%Notfound;
    Dbms_Output.Put_Line( '-Ho tên : ' || v_student.first_name || ' ' || v_student.last_name);
  END LOOP;
  CLOSE STUDENT_CUR;
END;

--Câu 2 :
DECLARE
  CURSOR STUDENT_CUR
  IS
    SELECT ID, FIRST_NAME,LAST_NAME FROM STUDENT;
  CURSOR SUBJECT_CUR(p_student_id STUDENT.ID%TYPE)
  IS
    SELECT sub.Name
    FROM SUBJECTS sub
    INNER JOIN SCORE sc
    ON sc.SUBJECTS_ID   = sub.ID
    WHERE sc.STUDENT_ID = p_student_id;
  v_student STUDENT_CUR%Rowtype;
  v_subject SUBJECT_CUR%Rowtype;
BEGIN
  OPEN STUDENT_CUR;
  LOOP
    FETCH STUDENT_CUR INTO v_student;
    EXIT
  WHEN STUDENT_CUR%Notfound;
    Dbms_Output.Put_Line(STUDENT_CUR%rowcount || '-Ho tên : ' || v_student.first_name || ' ' || v_student.last_name);
    Dbms_Output.Put_Line('Môn theo h?c:');
    OPEN SUBJECT_CUR(v_student.id);
    LOOP
      FETCH SUBJECT_CUR INTO v_subject;
      EXIT
    WHEN SUBJECT_CUR%Notfound;
      Dbms_Output.Put_Line(' ' || v_subject.name);
    END LOOP;
    CLOSE SUBJECT_CUR;
  END LOOP;
  CLOSE STUDENT_CUR;
END;

--Câu 3 :
DECLARE
  CURSOR STUDENT_CUR(param_faculty_id STUDENT.FACULTY_ID%TYPE)
  IS
    SELECT ID,
      first_name,
      last_name
    FROM STUDENT
    WHERE FACULTY_ID = param_faculty_id;
  v_student STUDENT_CUR%Rowtype;
  faculty_id CHAR := &MaKhoa;
BEGIN
  OPEN STUDENT_CUR(faculty_id);
  LOOP
    FETCH STUDENT_CUR INTO v_student;
    EXIT
  WHEN STUDENT_CUR%Notfound;
    Dbms_Output.Put_Line('Delete table core with student id '|| v_student.id);
    DELETE FROM SCORE WHERE STUDENT_ID = v_student.id;
    Dbms_Output.Put_Line('Delete table student with student id ' || v_student.id);
    DELETE FROM STUDENT WHERE ID = v_student.id;
  END LOOP;
  CLOSE STUDENT_CUR;
END;

--Câu 4 :
DECLARE
  CURSOR SCORE_CUR(param_subjects_id SCORE.SUBJECTS_ID%TYPE)
  IS
    SELECT STUDENT_ID,
      SUBJECTS_ID
    FROM SCORE
    WHERE SUBJECTS_ID = param_subjects_id;
  v_score SCORE_CUR%Rowtype;
  subjects_id CHAR := &MaMonHoc;
  diem        CHAR := &Diem;
BEGIN
  OPEN SCORE_CUR(subjects_id);
  LOOP
    FETCH SCORE_CUR INTO v_score;
    EXIT
  WHEN SCORE_CUR%Notfound;
    Dbms_Output.Put_Line('Update SCORE student ' || v_score.student_id);
    UPDATE SCORE
    SET SCORE        = SCORE + diem
    WHERE STUDENT_ID = v_score.student_id AND SUBJECTS_ID  = v_score.subjects_id;
  END LOOP;
  CLOSE SCORE_CUR;
END;
-- co the lam khong can cursor
--UPDATE SCORE
--SET SCORE = SCORE + diem
--WHERE SUBJECTS_ID = subjects_id;
