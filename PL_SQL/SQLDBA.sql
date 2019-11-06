-- Câu 1 : tính giai th?a
DECLARE 
   giai_thua NUMBER := 1;
   n NUMBER := &n; 
   i NUMBER := 1;
BEGIN  
    WHILE(i <= n)LOOP
        giai_thua := giai_thua * i;
        i := i+1;
    END LOOP;
    dbms_output.put_line('Giai th?a là: ' || giai_thua); 
END; 

-- Câu 2 : B?ng c?u ch??ng c?a n
DECLARE 
   tich NUMBER := 0;
   n NUMBER := &n; 
   i NUMBER := 1;
BEGIN  
    WHILE(i <= 10)LOOP
        tich := n * i;
        dbms_output.put_line(n||'x'||i||'=' ||tich); 
        i := i+1;
    END LOOP;
  
END;

-- Câu 3 : Gi?i ph??ng trinh b?t ax+b=0
DECLARE 
   x NUMBER := 0;
   a NUMBER := &a; 
   b NUMBER := &b;
BEGIN  
    IF(a = 0) THEN
      IF(b = 0) THEN
        dbms_output.put_line('Ph??ng trình vô s? nghi?m');
      ELSE 
        dbms_output.put_line('Ph??ng trình vô nghi?m');
      END IF;
    ELSE 
      x := (-b)/a;
      dbms_output.put_line('Ph??ng trình có nghi?m: x = '||x);
    END IF;
END;

-- Câu 4 : Gi?i ph??ng trinh b?t ax+b=c



--Câu 5 : Xét tam giác
DECLARE 
   canh_A NUMBER := &canh_A;
   canh_B NUMBER := &canh_B; 
   canh_C NUMBER := &canh_C;
BEGIN  
    IF((canh_A + canh_B <= canh_C) or (canh_B + canh_C) <= canh_A or (canh_C + canh_A) <= canh_B)THEN
        dbms_output.put_line('Ba canh a, b, c khong phai la ba canh cua mot tam giac');
    ELSE 
        IF(canh_A = canh_B AND canh_B = canh_C) THEN
           dbms_output.put_line('Day la tam giac deu');
        ELSIF(canh_A = canh_B OR canh_B = canh_C OR canh_C = canh_A) THEN
            IF  (canh_A*canh_A + canh_B*canh_B = canh_C*canh_C OR canh_B*canh_B + canh_C*canh_C = canh_A*canh_A OR canh_C*canh_C + canh_A*canh_A = canh_B*canh_B) THEN
              dbms_output.put_line('Day la tam giac vuông can');
            ELSE
               dbms_output.put_line('Day la tam giac can');
            END IF;
        ELSIF(canh_A*canh_A + canh_B*canh_B = canh_C*canh_C OR canh_B*canh_B + canh_C*canh_C = canh_A*canh_A OR canh_C*canh_C + canh_A*canh_A = canh_B*canh_B)THEN
           dbms_output.put_line('Day la tam giac vuong');       
        ELSE
           dbms_output.put_line('Day la tam giac th??ng');
        END IF;    
    END IF;     
END;

--Câu 6: Check input date h?p l?


