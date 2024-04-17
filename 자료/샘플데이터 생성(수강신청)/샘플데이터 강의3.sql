-- 샘플데이터 생성쿼리 3 -> lecture 3일때
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
 
CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 3;
        
    WHILE i <= 3 DO
          
		-- 파일
        INSERT INTO File(frealnm,foriginnm)
          VALUES(concat('첨부파일실제이름',i),concat('첨부파일원래이름',i));
         
		-- 학생 사진파일 브릿지
        INSERT INTO studentbridgefile(sno,fno) VALUES(i,i);
         
		-- 교수 사진파일 브릿지
        INSERT INTO profBridgeFile(pno,fno) VALUES(i,i);
          
		-- 공지사항 파일 브릿지
          INSERT INTO boardbridgefile(fno,bno) VALUES(i,i);
          
        -- 공지사항  
		INSERT INTO board(btitle,bcontent,bhit,ano)
          VALUES(concat('공지사항제목입니다.',i),concat('공지사항내용입니다.',i),i,i);
		
        -- 강의정보
		INSERT INTO lecture(lname,lyear,lsemester,lcredit,ltime, lroom,lmaxpeople,
			lintro, lfocus,lstatus,pno,lschedule)
          VALUES(concat('강의',i),'2024','1','3','3',concat('공대 40',i),30, '..', '..',2,i,
             JSON_ARRAY('104','304','305')
             );
             
		-- 수강정보 브릿지
          INSERT INTO course(cyn,cgrade,lno,sno,cdelyn) VALUES( '1', 'A', i, i,'0');
          
		-- 출석정보
        INSERT INTO attendment(attendrdate,attendyn,cno) 
			VALUES(current_date(), 1, i);
            
		-- 휴복학정보
        -- INSERT INTO absense(abseinfo,abserdate,sno)
		-- VALUES(0,current_date(),i);
		
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER $$


CALL loopInsert;
