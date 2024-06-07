-- 코드를 입력하세요
SELECT A.NAME, A.DATETIME
FROM ANIMAL_INS A
WHERE A.ANIMAL_ID NOT IN (
                            SELECT AI.ANIMAL_ID
                            FROM ANIMAL_INS AI INNER JOIN ANIMAL_OUTS AO
                            ON AI.ANIMAL_ID = AO.ANIMAL_ID
                         )
ORDER BY A.DATETIME ASC
LIMIT 3