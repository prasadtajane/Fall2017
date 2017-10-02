select	alb.title as album_title,
			a.name as artist_name,
			tr.name as track_name,
			mt.name as media_type,
			('$' || tr.unitPrice) as unit_price
from	Track tr
				inner join
			MediaType mt
				inner join
			Album alb
				inner join
			Artist a
			on 	tr.mediaTypeId = mt.mediaTypeId
			and	tr.albumId = alb.albumId
			and	alb.artistId = a.artistId
where	tr.albumId in (
			select 	t.albumId
			from	Track t
							inner join
						MediaType m
						on	t.mediaTypeId = m.mediaTypeId
			where	((	m.name like '%audio%' 
								and 
							t.unitPrice <> 0.99)
						or
						(	m.name like '%video%' 
								and 
							t.unitPrice <> 1.99))
)
order by tr.trackId