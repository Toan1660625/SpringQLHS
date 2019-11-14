--Câu 1 :
DECLARE 
   so_chia NUMBER := 0;
   so_bi_chia NUMBER := 10;
BEGIN 
      DBMS_OUTPUT.PUT_LINE (so_bi_chia/so_chia);
EXCEPTION 
   WHEN ZERO_DIVIDE THEN 
      dbms_output.put_line('Error! Chia cho 0');  
END; 

--Câu 2 :
DECLARE 
   n NUMBER := &n;
   ex_error EXCEPTION;
BEGIN 
   IF n > 10 THEN 
      RAISE ex_error; 
   ELSE
      DBMS_OUTPUT.PUT_LINE('so vua nhap: '|| n);
   END IF;
EXCEPTION 
   WHEN ex_error THEN 
      dbms_output.put_line('Error! n > 10');  
END; 

--Câu 3 :
DECLARE 
   n_SV NUMBER;
   ex_error EXCEPTION;
BEGIN 
   SELECT COUNT(*)
   INTO n_SV
   FROM STUDENT;
   IF n_SV > 10 THEN 
      RAISE ex_error; 
   ELSE
      DBMS_OUTPUT.PUT_LINE('So hoc sinh: '|| n_SV);
   END IF;
EXCEPTION 
   WHEN ex_error THEN 
      dbms_output.put_line('Error! So hoc sinh > 10');  
END; 

--Câu 4 :
DECLARE 
   v_Score NUMBER;
   ex_error EXCEPTION;
BEGIN 
  Select MIN(SCORE)
  Into  v_SCORE
  From   SCORE;
   IF v_SCORE < 5 THEN 
      RAISE ex_error; 
   ELSE
      DBMS_OUTPUT.PUT_LINE('Min Score: '|| v_SCORE);
   END IF;
EXCEPTION 
   WHEN ex_error THEN 
      dbms_output.put_line('Error! Min Score < 5');  
END; 

--Câu 5 :
DECLARE
  canh_A NUMBER := &canh_A;
  canh_B NUMBER := &canh_B;
  canh_C NUMBER := &canh_C;
  ex_error EXCEPTION;
BEGIN
 IF (canh_A < 0 OR canh_B < 0 OR canh_C < 0) THEN 
      RAISE ex_error; 
 ELSE
  IF((canh_A + canh_B <= canh_C) OR (canh_B + canh_C) <= canh_A OR (canh_C + canh_A) <= canh_B)THEN
    dbms_output.put_line('Ba canh a, b, c khong phai la ba canh cua mot tam giac');
  ELSE
    IF(canh_A = canh_B AND canh_B = canh_C) THEN
      dbms_output.put_line('Day la tam giac deu');
    ELSIF(canh_A = canh_B OR canh_B = canh_C OR canh_C = canh_A) THEN
      IF (canh_A**2 + canh_B**2 = canh_C**2 OR canh_B**2 + canh_C**2 = canh_A**2 OR canh_C**2 + canh_A**2 = canh_B**2) THEN
        dbms_output.put_line('Day la tam giac vuông can');
      ELSE
        dbms_output.put_line('Day la tam giac can');
      END IF;
    ELSIF(canh_A**2 + canh_B**2 = canh_C**2 OR canh_B**2 + canh_C**2 = canh_A**2 OR canh_C**2 + canh_A**2 = canh_B**2)THEN
      dbms_output.put_line('Day la tam giac vuong');
    ELSE
      dbms_output.put_line('Day la tam giac thuong');
    END IF;
  END IF;
 END IF;
 EXCEPTION 
   WHEN ex_error THEN 
      dbms_output.put_line('Error! Co canh < 0 (do dai khong am)');
END;
