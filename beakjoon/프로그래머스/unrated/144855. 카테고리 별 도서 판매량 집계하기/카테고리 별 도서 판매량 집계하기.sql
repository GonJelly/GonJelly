-- 코드를 입력하세요
SELECT B.category, SUM(BS.sales) as TOTAL_SALES
FROM BOOK B JOIN BOOK_SALES BS ON B.BOOK_ID = BS.BOOK_ID
WHERE DATE_FORMAT(BS.sales_date,'%Y-%m') = '2022-01'
GROUP BY B.category
ORDER BY B.category ASC

# SELECT *
# FROM BOOK_SALES
# WHERE DATE_FORMAT(sales_date,'%Y-%m') = '2022-01'