-- 혼합 SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
-- Ans) 107706
select 
	count(*)
from 
	salaries
where 
	to_date = '9999-01-01' and salary > (
	select avg(salary)
	from salaries
	where to_date = '9999-01-01'
);

-- 문제2. (X 생략)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요.
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요
-- Ans) 113154 rows
select 
	a.emp_no as '사번', 
	concat(first_name, ' ', last_name) as '이름', 
	c.salary as '연봉'
from 
	employees a, dept_emp b, salaries c,
(
select 
	dept_no, 
	avg(salary) avg_salary
from 
	employees a, dept_emp b, salaries c
where 
	a.emp_no = b.emp_no 
	and a.emp_no = c.emp_no 
	and b.to_date = '9999-01-01' 
	and c.to_date = '9999-01-01'
group by b.dept_no
) t1
where 
	a.emp_no = b.emp_no 
	and a.emp_no = c.emp_no
	and b.dept_no = t1.dept_no
	and b.to_date = '9999-01-01' 
	and c.to_date = '9999-01-01'
	and c.salary > t1.avg_salary;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
-- Ans) 240124 rows
select
	a.emp_no as '사번', 
	concat(a.first_name, ' ', a.last_name) as '이름', 
	concat(e.first_name, ' ', e.last_name) as '매니저 이름', 
	c.dept_name as '부서 이름'
from 
	employees a, dept_emp b, departments c, dept_manager d, employees e
where 
	a.emp_no = b.emp_no
	and b.dept_no = c.dept_no
	and e.emp_no = d.emp_no
	and d.dept_no = b.dept_no
	and b.to_date = '9999-01-01' 
	and d.to_date = '9999-01-01';

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
-- Ans) 37701 rows
select
	a.emp_no 사번, concat(first_name, ' ', last_name) 이름,
	b.title 직책, c.salary 연봉
from
	employees a, titles b, salaries c, dept_emp d
where
	a.emp_no = b.emp_no
	and a.emp_no = c.emp_no
	and a.emp_no = d.emp_no
	and b.to_date = '9999-01-01'
	and c.to_date = '9999-01-01'
	and d.to_date = '9999-01-01'
	and d.dept_no = (
        select a.dept_no
        from dept_emp a, salaries b
        where a.emp_no = b.emp_no
            and a.to_date = '9999-01-01'
            and b.to_date = '9999-01-01'
        group by a.dept_no
        having round(avg(salary)) = (
            select
                max(avg_salary)
            from
                dept_emp a, salaries b, (
                    select
                        round(avg(c.salary)) as avg_salary
                    from
                        dept_emp a, departments b, salaries c
                    where
                        a.dept_no = b.dept_no
                        and a.emp_no = c.emp_no
                        and a.to_date = '9999-01-01'
                        and c.to_date = '9999-01-01'
                    group by a.dept_no
                ) t1
            where a.emp_no = b.emp_no
                and a.to_date = '9999-01-01'
                and b.to_date = '9999-01-01'
        )
    )
order by c.salary desc;

-- cf) 평균연봉이 가장 높은 부서 번호
select a.dept_no
from dept_emp a, salaries b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
group by a.dept_no
having round(avg(salary)) = (
    select
        max(avg_salary)
    from
        dept_emp a, salaries b, (
            select
                round(avg(c.salary)) as avg_salary
            from
                dept_emp a, departments b, salaries c
            where
                a.dept_no = b.dept_no
                and a.emp_no = c.emp_no
                and a.to_date = '9999-01-01'
                and c.to_date = '9999-01-01'
            group by a.dept_no
        ) t1
    where a.emp_no = b.emp_no
        and a.to_date = '9999-01-01'
        and b.to_date = '9999-01-01'
 );

-- 문제6.
-- 현재, 평균 연봉이 가장 높은 부서는?
-- Ans) Sales, 888853
select 
	b.dept_name 부서, round(avg(c.salary)) 평균연봉
from 
	dept_emp a, departments b, salaries c
where 
	a.emp_no = c.emp_no
	and a.dept_no = b.dept_no
	and a.to_date = '9999-01-01' 
	and c.to_date = '9999-01-01'
group by a.dept_no
having 평균연봉 = (
select max(avg_salary)
from (
select 
	round(avg(b.salary)) avg_salary
from 
	dept_emp a, salaries b
where 
	a.emp_no = b.emp_no
	and a.to_date = '9999-01-01' 
	and b.to_date = '9999-01-01'
group by a.dept_no
) t1
);

-- 문제7.
-- 현재, 평균 연봉이 가장 높은 직책?
-- Ans) Senior Staff, 80706
select 
	a.title 직책, round(avg(b.salary)) 평균연봉
from titles a, salaries b
where 
	a.emp_no = b.emp_no
	and a.to_date = '9999-01-01' 
	and b.to_date = '9999-01-01'
group by title
having 평균연봉 = (
select max(avg_salary)
from (
select
	round(avg(b.salary)) as avg_salary
from
	titles a, salaries b
where
	a.emp_no = b.emp_no
	and a.to_date = '9999-01-01' 
	and b.to_date = '9999-01-01'
group by a.title
) t1
);

-- 문제8. (순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
-- Ans) 97261 rows
select
    c.dept_name as 부서이름,
    concat(a1.first_name, ' ', a1.last_name) as 사원이름,
    d1.salary as 연봉,
    concat(a2.first_name, ' ', a2.last_name) as 매니저_이름,
    d2.salary as 매니저_연봉
from
     employees a1,
     dept_emp b,
     departments c,
     salaries d1,
     employees a2,
     salaries d2,
     dept_manager e
where
    a1.emp_no = b.emp_no
    and b.dept_no = c.dept_no
    and a1.emp_no = d1.emp_no
    and a2.emp_no = d2.emp_no
    and a2.emp_no = e.emp_no
    and e.dept_no = c.dept_no
    and b.to_date = '9999-01-01'
    and d1.to_date = '9999-01-01'
    and d2.to_date = '9999-01-01'
    and e.to_date = '9999-01-01'
    and d1.salary > d2.salary;


