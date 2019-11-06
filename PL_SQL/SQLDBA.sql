-- C�u 1 : t�nh giai th?a
DECLARE 
   giai_thua NUMBER := 1;
   n NUMBER := &n; 
   i NUMBER := 1;
BEGIN  
    WHILE(i <= n)LOOP
        giai_thua := giai_thua * i;
        i := i+1;
    END LOOP;
    dbms_output.put_line('Giai th?a l�: ' || giai_thua); 
END; 

-- C�u 2 : B?ng c?u ch??ng c?a n
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

-- C�u 3 : Gi?i ph??ng trinh b?t ax+b=0
DECLARE 
   x NUMBER := 0;
   a NUMBER := &a; 
   b NUMBER := &b;
BEGIN  
    IF(a = 0) THEN
      IF(b = 0) THEN
        dbms_output.put_line('Ph??ng tr�nh v� s? nghi?m');
      ELSE 
        dbms_output.put_line('Ph??ng tr�nh v� nghi?m');
      END IF;
    ELSE 
      x := (-b)/a;
      dbms_output.put_line('Ph??ng tr�nh c� nghi?m: x = '||x);
    END IF;
END;

-- C�u 4 : Gi?i ph??ng trinh b?t ax+b=c



--C�u 5 : X�t tam gi�c
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
              dbms_output.put_line('Day la tam giac vu�ng can');
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

--C�u 6: Check input date h?p l?


