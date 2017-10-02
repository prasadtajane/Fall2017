select 	a.name as long_artist,
			t.name as long_song,
			cast(round(t.milliseconds/60000) AS INTEGER) as full_minutes
from	Track t 
				inner join
			MediaType m
				inner join
			Album alb 
				inner join
			Artist a
			on 	t.albumId = alb.albumId
			and 	a.artistId = alb.artistId
			and	t.mediaTypeId = m.mediaTypeId
where	t.milliseconds > 601000
and		m.name like '%audio%'
group by	a.name
having	max(t.milliseconds)
order by t.milliseconds desc