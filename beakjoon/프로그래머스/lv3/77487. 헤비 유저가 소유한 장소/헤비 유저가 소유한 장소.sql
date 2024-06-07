-- 코드를 입력하세요
SELECT *
FROM PLACES p
WHERE p.HOST_ID IN (SELECT p1.HOST_ID
                    FROM PLACES p1
                    GROUP BY p1.HOST_ID
                    HAVING COUNT(p1.HOST_ID) >= 2)
ORDER BY p.ID