-- Câu 1 : Tinh giai thua
DECLARE
  giai_thua NUMBER := 1;
  n         NUMBER := &n;
  i         NUMBER := 1;
BEGIN
  WHILE(i <= n)
  LOOP
    giai_thua := giai_thua * i;
    i         := i         +1;
  END LOOP;
  dbms_output.put_line('Giai thua là: ' || giai_thua);
END;

-- Câu 2 : Bang cuu chuong cua n
DECLARE
  tich NUMBER := 0;
  n    NUMBER := &n;
  i    NUMBER := 1;
BEGIN
  WHILE(i <= 10)
  LOOP
    tich := n * i;
    dbms_output.put_line(n||'x'||i||'=' ||tich);
    i := i+1;
  END LOOP;
END;

-- Câu 3 : Giai phuong trinh bat ax+b=0
DECLARE
  x NUMBER := 0;
  a NUMBER := &a;
  b NUMBER := &b;
BEGIN
  IF(a   = 0) THEN
    IF(b = 0) THEN
      dbms_output.put_line('Phuong trình vô so nghiem');
    ELSE
      dbms_output.put_line('Phuong trinh vô nghiem');
    END IF;
  ELSE
    x := (-b)/a;
    dbms_output.put_line('Phuong trình có nghiem: x = '||x);
  END IF;
END;

-- Câu 4 : Giai phuong trinh bat hai a^2+bx+c=0 
DECLARE
  a FLOAT := &a;
  b FLOAT := &b;
  c FLOAT := &c;
  x1 FLOAT;
  x2 FLOAT;
  delta FLOAT;
BEGIN
    IF (a = 0)THEN
        IF (b = 0) THEN
           IF (c = 0) THEN
                  dbms_output.put_line('Phuong trinh vo so nghiem');
           ELSE
                  dbms_output.put_line('Phuong trinh vo nghiem');
           END IF;
        ELSE
          
           dbms_output.put_line('Phuong trinh co nghiem duy nhat:'|| -c/b );
        END IF;
    ELSE
        delta := b**2 - 4*a*c;
        if(delta > 0) THEN
            x1 := (-b+sqrt(delta))/(2*a);
            x2 := (-b-sqrt(delta))/(2*a);
            dbms_output.put_line('Nghiem thu nhat x1 = '|| x1 );
            dbms_output.put_line('Nghiem thu hai x2 ='|| x2 );
        ELSIF ( delta = 0) THEN
          dbms_output.put_line('Phuong trinh co nghiem kep: x1 = x2 = '||  -b/2*a );
        ELSE
          dbms_output.put_line('Phuong trinh vo nghiem');
        END IF;
    END IF;
    
END;

--Câu 5 : Xét tam giác
DECLARE
  canh_A NUMBER := &canh_A;
  canh_B NUMBER := &canh_B;
  canh_C NUMBER := &canh_C;
BEGIN
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
END;

--Câu 6: Check input date hop le
DECLARE
  check_date NUMBER := &date;
  check_month NUMBER := &month;
  check_year NUMBER := &year;
  max_date NUMBER;
BEGIN
  IF(check_date < 0 OR check_month < 0 OR check_year < 0) THEN
      dbms_output.put_line('Thoi gian khong âm');
  ELSE
    IF(check_month in (1,3,5,7,8,10,12)) THEN
      max_date := 31;
    ELSIF(check_month = 2)THEN
     IF((MOD(check_year,400) = 0) OR (MOD(check_year,4) = 0 AND MOD(check_year,100) != 0)) THEN
        max_date := 29;
      ELSE
        max_date := 29;
      END IF;
    ELSE
      max_date :=30;
    END IF;

    IF(check_date > max_date)THEN
      dbms_output.put_line('Thoi gian khong hop le');
    ELSE
      dbms_output.put_line('Thoi gian hop le');
    END IF;
  END IF;
END;

--Câu 7: Cau 5 dung case when dang có selector
DECLARE
  canh_A NUMBER := &canh_A;
  canh_B NUMBER := &canh_B;
  canh_C NUMBER := &canh_C;
BEGIN
   CASE
      WHEN (canh_A + canh_B <= canh_C) OR (canh_B + canh_C) <= canh_A OR (canh_C + canh_A <= canh_B) THEN
        dbms_output.put_line('Ba canh a, b, c khong phai la ba canh cua mot tam giac');
      ELSE
        CASE
        WHEN (canh_A = canh_B AND canh_B = canh_C) THEN
         dbms_output.put_line('Day la tam giac deu');
        WHEN(canh_A = canh_B OR canh_B = canh_C OR canh_C = canh_A) THEN
          CASE
          WHEN (canh_A**2 + canh_B**2 = canh_C**2 OR canh_B**2 + canh_C**2 = canh_A**2 OR canh_C**2 + canh_A**2 = canh_B**2) THEN
            dbms_output.put_line('Day la tam giac vuông can');
          ELSE
            dbms_output.put_line('Day la tam giac can');
          END CASE;
        WHEN(canh_A**2 + canh_B**2 = canh_C**2 OR canh_B**2 + canh_C**2 = canh_A**2 OR canh_C**2 + canh_A**2 = canh_B**2)THEN
          dbms_output.put_line('Day la tam giac vuong');
        ELSE
        dbms_output.put_line('Day la tam giac thuong');
     END CASE;
  END CASE;
END;  
  

