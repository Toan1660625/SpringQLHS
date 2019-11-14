--Cau 1 : 
CREATE OR REPLACE PACKAGE Pkg_Cau1 AS
  Function find_sname(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type;
End Pkg_Cau1;

CREATE OR REPLACE PACKAGE BODY Pkg_Cau1 AS

  Function find_sname(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type As
  v_First_Name STUDENT.First_Name%Type;
  v_Last_Name STUDENT.Last_Name%Type;
    Begin
      Select First_Name,Last_Name
      Into  v_First_Name,v_Last_Name
      From   STUDENT
      Where  ID = s_Id;
      Dbms_Output.Put_Line('Ho ten SV : ' || v_Last_Name ||' '|| v_First_Name);
      Return v_First_Name;
      EXCEPTION
      WHEN No_Data_Found THEN
        Dbms_Output.Put_Line('Khong tim thay student id = ' || s_Id);
        Return '';
    End;
END Pkg_Cau1;

--Cau 2 : 
CREATE OR REPLACE PACKAGE Pkg_Cau2 AS
  Function find_sname(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type;
  PROCEDURE print_student_name(s_Id STUDENT.ID%Type);
End Pkg_Cau2;

CREATE OR REPLACE PACKAGE BODY Pkg_Cau2 AS
  Function find_sname(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type As
  v_First_Name STUDENT.First_Name%Type;
    Begin
      Select First_Name
      Into  v_First_Name
      From   STUDENT
      Where  ID = s_Id;
      Return v_First_Name;
      EXCEPTION
      WHEN No_Data_Found THEN
        Dbms_Output.Put_Line('Khong tim thay student id = ' || s_Id);
        Return '';
    End;
    
  PROCEDURE print_student_name(s_Id STUDENT.ID%Type) AS
      a STUDENT.First_Name%Type;
  BEGIN   
      a := find_sname(s_Id);
      Dbms_Output.Put_Line('Ten SV : ' || a);
  END;  
END Pkg_Cau2;


--Cau 3 :
CREATE OR REPLACE PACKAGE Pkg_Cau3 AS
  Function print_student_name(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type;
  
  Function  print_max_score
  Return SCORE.SCORE%Type;
End Pkg_Cau3;

CREATE OR REPLACE PACKAGE BODY Pkg_Cau3 AS
  Function print_student_name(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type As
  v_First_Name STUDENT.First_Name%Type;
    Begin
      Select First_Name
      Into  v_First_Name
      From   STUDENT
      Where  ID = s_Id;
      Return v_First_Name;
      EXCEPTION
      WHEN No_Data_Found THEN
        Dbms_Output.Put_Line('Khong tim thay student id = ' || s_Id);
        Return '';
    End;
    
   Function print_max_score
    Return SCORE.SCORE%Type As
    v_SCORE SCORE.SCORE%Type;
    Begin
      Select MAX(SCORE)
      Into  v_SCORE
      From   SCORE;
      Dbms_Output.Put_Line('MAX SCORE : ' || v_SCORE);
      Return v_SCORE;
      EXCEPTION
      WHEN No_Data_Found THEN
        Return null;
    End;
END Pkg_Cau3;

--Cau 4 : 
CREATE OR REPLACE PACKAGE Pkg_Cau4 AS
  Function print_student_name(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type; 
  Function  print_min_score
  Return SCORE.SCORE%Type;
End Pkg_Cau4;

CREATE OR REPLACE PACKAGE BODY Pkg_Cau4 AS
  Function print_student_name(s_Id STUDENT.ID%Type)
  Return STUDENT.First_Name%Type As
  v_First_Name STUDENT.First_Name%Type;
    Begin
      Select First_Name
      Into  v_First_Name
      From   STUDENT
      Where  ID = s_Id;
      Return v_First_Name;
      EXCEPTION
      WHEN No_Data_Found THEN
        Dbms_Output.Put_Line('Khong tim thay student id = ' || s_Id);
        Return '';
    End;
    
   Function  print_min_score
    Return SCORE.SCORE%Type As
    v_SCORE SCORE.SCORE%Type;
    Begin
      Select MIN(SCORE)
      Into  v_SCORE
      From   SCORE;
      Dbms_Output.Put_Line('MAX SCORE : ' || v_SCORE);
      Return v_SCORE;
      EXCEPTION
      WHEN No_Data_Found THEN
        Return null;
    End;
END Pkg_Cau4;

-------TEST------
--cau 1,2    s_Id STUDENT.ID%Type := &ID; 
DECLARE 
    temp STUDENT.First_Name%Type;
    tempSCORE SCORE.SCORE%Type;
BEGIN   
--   temp := Pkg_Cau1.find_sname(s_Id);  
--   Pkg_Cau2.print_student_name(s_Id);
--   tempSCORE := Pkg_Cau3.print_max_score();
     tempSCORE := Pkg_Cau4. print_min_score();  
END; 

--SELECT * FROM SCORE
--SELECT * FROM STUDENT
--SELECT * FROM Faculty
--SELECT * FROM Subjects
