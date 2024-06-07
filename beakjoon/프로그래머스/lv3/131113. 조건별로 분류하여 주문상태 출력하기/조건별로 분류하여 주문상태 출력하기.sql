-- 코드를 입력하세요
SELECT FO.ORDER_ID, FO.PRODUCT_ID, DATE_FORMAT(FO.OUT_DATE, '%Y-%m-%d') OUT_DATE,
        CASE WHEN FO.OUT_DATE <= '2022-05-01' THEN '출고완료'
             WHEN FO.OUT_DATE > '2022-05-01' THEN '출고대기' 
             ELSE '출고미정' END as 출고여부
FROM FOOD_ORDER FO
ORDER BY FO.ORDER_ID ASC