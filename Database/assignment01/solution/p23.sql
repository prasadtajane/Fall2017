select	il.invoicelineid as invoice_line_id,
			t.trackid as track_id,
			alb.title as album_title,
			a.name as artist_name,
			t.name as track_name,
			m.name as media_type,
			('$' || il.unitprice) as unit_price,
			il.quantity as qty
from Invoice i inner join InvoiceLine il 
					  inner join Track t 
					  inner join album alb 
					  inner join Artist a 
					  inner join MediaType m
		on i.invoiceid = il.invoiceid 
		and il.trackid = t.trackid 
		and t.albumid = alb.albumid 
		and a.artistid = alb.artistid 
		and t.mediatypeid = m.mediatypeid
where total > 25
order by	alb.title asc,
				a.name asc,
				t.name asc