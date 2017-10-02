select	e.employeeId as e_id, 
			e.firstName as e_first_name, 
			e.lastName as e_last_name, 
			e.title as e_title, 
			('$' || printf ('%.2f', sum(ct.customer_total))) as total_invoices
from	Employee e
				left outer join
			(select	i.customerId, sum(i.total) as customer_total,  c.supportRepId
			from	Invoice	i
							inner join
						Customer c
						on c.customerId = i.customerId
			group by	c.customerId) ct
			on e.employeeId = ct.supportRepId
group by	e.employeeId
order by sum(ct.customer_total) desc, e.lastName asc, e.firstName asc